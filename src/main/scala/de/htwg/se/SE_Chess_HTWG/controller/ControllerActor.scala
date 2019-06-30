package de.htwg.se.SE_Chess_HTWG.controller

import akka.actor.{Actor, ActorLogging}

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