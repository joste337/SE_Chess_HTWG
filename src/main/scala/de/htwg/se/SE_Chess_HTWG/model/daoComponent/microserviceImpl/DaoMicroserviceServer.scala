package de.htwg.se.SE_Chess_HTWG.model.daoComponent.microserviceImpl

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContextExecutor, Future}

class DaoMicroserviceServer(daoMicroservice: DaoMicroservice) {
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  implicit val timeout: Timeout = 5.seconds

  val dbMicroserviceHost: String = ConfigFactory.load().getString("dbMicroserviceHost")
  val dbMicroservicePort: Int = ConfigFactory.load().getInt("dbMicroservicePort")
  val daoActor: ActorRef = system.actorOf(Props(new DaoActor(daoMicroservice.database)), "daoActor")

  val route: Route = {
    path("db" / "create") {
      put {
        parameter("gridJson".as[String]) { gridJson =>
          daoActor ! (Create, gridJson)
          complete((StatusCodes.Accepted, "Created"))
        }
      }
    }~
    path("db" / "read") {
      get {
        parameter("id".as[Int]) { id =>
          val gridJson: Future[String] = (daoActor ? (Read, id)).mapTo[String]
          complete((StatusCodes.Accepted, gridJson))
        }
      }
    }~
    path("db" / "update") {
      put {
        parameter("id".as[Int], "gridJson".as[String] ) { (id, gridJson) =>
          val result: Future[Boolean] = (daoActor ? (Update, id, gridJson)).mapTo[Boolean]
          complete((StatusCodes.Accepted, "Updating: " + result))
        }
      }
    }~
    path("db" / "delete") {
      put {
        parameter("id".as[Int]) { id =>
          val result: Future[Boolean] = (daoActor ? (Delete, id)).mapTo[Boolean]
          complete((StatusCodes.Accepted, "Deleting: " + result))
        }
      }
    }
  }

  val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(route, dbMicroserviceHost, dbMicroservicePort)

  def unbind(): Unit = {
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}
