package de.htwg.se.SE_Chess_HTWG.aView.web

import akka.http.scaladsl.model._
import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import de.htwg.se.SE_Chess_HTWG.controller.ControllerInterface

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.swing.Reactor


class ControllerActor(controller: ControllerInterface) extends Actor with ActorLogging with Reactor {
  listenTo(controller)

  def receive = {
    case (row, col) => controller.selectSquare(row.asInstanceOf[Int], col.asInstanceOf[Int])
    case GetGrid => sender() ! controller.gridString
    case NewGrid => sender() ! controller.createNewGrid
    case _ => log.info("Invalid message")
  }
}

case object GetGrid
case object NewGrid

class WebServer(controller: ControllerInterface) {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val controllerActor = system.actorOf(Props(new ControllerActor(controller)), "auction")

  val route = {
    path("chess" / "view") {
      get {
        implicit val timeout: Timeout = 5.seconds
        val grid: Future[String] = (controllerActor ? GetGrid).mapTo[String]
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<pre>" + Await.result(grid, Duration.Inf) +"</pre>"))
      }
    }~
    path ("chess" / "select") {
      get {
        parameter("row".as[Int], "col".as[Int]) { (row, col) =>
          controllerActor ! (row, col)
          complete((StatusCodes.Accepted, "<pre>" + controller.gridString +"</pre>"))
        }
      }
    }~
    path("chess" / "new") {
      get {
        controllerActor ! NewGrid
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<pre>" + controller.gridString +"</pre>"))
      }
    }~
    path("chess" / "save") {
      get {
        controller.save()
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<pre>" + controller.gridString +"</pre>"))
      }
    }~
    path("chess" / "load") {
      get {
        controller.load()
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<pre>" + controller.gridString +"</pre>"))
      }
    }
  }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  def unbind = {
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
