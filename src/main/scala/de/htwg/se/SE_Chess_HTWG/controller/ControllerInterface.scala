package de.htwg.se.SE_Chess_HTWG.controller

import de.htwg.se.SE_Chess_HTWG.controller.GameStatus.GameStatus

import scala.swing.Publisher
import scala.swing.event.Event

trait ControllerInterface extends Publisher {

  def gridToString: String
  def gameStatus: GameStatus
  def createNewGrid: Unit
  def movePiece(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean
}

class CellChanged extends Event
