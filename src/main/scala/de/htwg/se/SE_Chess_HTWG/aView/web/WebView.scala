package de.htwg.se.SE_Chess_HTWG.aView.web

import akka.http.scaladsl.model._
import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import de.htwg.se.SE_Chess_HTWG.controller.{ControllerActor, ControllerInterface, GetGrid, NewGrid}
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridGenerator
import play.api.libs.json.Json

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

class WebView(controller: ControllerInterface) {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val dbMicroserviceUrl: String = ConfigFactory.load().getString("dockerDbMicroServiceUrl")
  val mainServiceHost: String = ConfigFactory.load().getString("mainServiceHost")
  val mainServicePort: Int = ConfigFactory.load().getInt("mainServicePort")
  val controllerActor = system.actorOf(Props(new ControllerActor(controller)), "controllerActor")

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
    }~
    path("chess" / "create") {
      post {
        controller.dbCreate()
        complete((StatusCodes.Accepted, "Created new entry.\n"))
      }
    }~
    path("chess" / "read") {
      get {
        parameter("id".as[Int]) { id =>
          controller.dbRead(id)
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<pre>" + controller.gridString + "</pre>"))
        }
      }
    }~
    path("chess" / "update") {
      put {
        parameter("id".as[Int]) { id =>
          val success: Boolean = Await.result(controller.dbUpdate(id), 1 second)
          if (success) {
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Database updated successfully."))
          } else {
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Database couldn't be updated."))
          }
        }
      }
    }~
    path("chess" / "delete") {
      delete {
        parameter("id".as[Int]) { id =>
          controller.dbDelete(id)
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<pre>" + controller.gridString  + "</pre>"))
        }
      }
    }~
    path("chess" / "dbMicroservice" / "create") {
      post {
        val result = scalaj.http.Http(dbMicroserviceUrl + "/db/create").method("post")
          .param("gridJson", Json.prettyPrint(GridGenerator.gridToJson(controller.grid))).asString.body
        complete((StatusCodes.Accepted, "<pre>" + result + "</pre>"))
      }
    }~
    path("chess" / "dbMicroservice" / "read") {
      get {
        parameter("id".as[Int]) { id =>
          controller.grid = GridGenerator.gridFromJson(scalaj.http.Http(dbMicroserviceUrl + "/db/read").method("get")
            .param("id", id.toString).asString.body)
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<pre>" + controller.gridString + "</pre>"))
        }
      }
    }~
    path("chess" / "dbMicroservice" / "update") {
      put {
        parameter("id".as[Int]) { id =>
          val result = scalaj.http.Http(dbMicroserviceUrl + "/db/update").method("put").param("gridJson", Json.prettyPrint(GridGenerator.gridToJson(controller.grid))).param("id", id.toString).asString.body
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<pre>" + result + "</pre>"))
        }
      }
    }~
    path("chess" / "dbMicroservice" / "delete") {
      delete {
        parameter("id".as[Int]) { id =>
          val result = scalaj.http.Http(dbMicroserviceUrl + "/db/delete").method("delete").param("id", id.toString).asString.body
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<pre>" + result  + "</pre>"))
        }
      }
    }
  }

  val bindingFuture = Http().bindAndHandle(route, mainServiceHost, mainServicePort)

  def unbind = {
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
