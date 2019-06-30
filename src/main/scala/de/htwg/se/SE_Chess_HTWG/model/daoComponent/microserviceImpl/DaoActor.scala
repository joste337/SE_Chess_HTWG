package de.htwg.se.SE_Chess_HTWG.model.daoComponent.microserviceImpl

import akka.actor.{Actor, ActorLogging}
import de.htwg.se.SE_Chess_HTWG.model.daoComponent.DAOInterface

import scala.swing.Reactor

class DaoActor(daoInterface: DAOInterface) extends Actor with ActorLogging with Reactor {
  override def receive: PartialFunction[Any, Unit] = {
    case (Create, gridJson) => sender() ! daoInterface.create(gridJson.asInstanceOf[String])
    case (Read, id) => sender() ! daoInterface.read(id.asInstanceOf[Int])
    case (Update, id, gridJson) => sender() ! daoInterface.update(id.asInstanceOf[Int], gridJson.asInstanceOf[String])
    case (Delete, id) => sender() ! daoInterface.delete(id.asInstanceOf[Int])
    case _ => log.info("Invalid message")
  }
}

case object Create
case object Read
case object Update
case object Delete
