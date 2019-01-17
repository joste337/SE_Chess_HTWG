package de.htwg.se.SE_Chess_HTWG.controller

import de.htwg.se.SE_Chess_HTWG.controller.GameStatus.GameStatus
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

import scala.swing.Publisher
import scala.swing.event.Event

trait ControllerInterface extends Publisher {

  def gridToString: String
  def gameStatus: GameStatus
  def createNewGrid: Unit
  def movePiece(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): MovementResult
  def promotePiece(row: Int, col: Int, pieceShortcut: String): MovementResult
}

class CellChanged extends Event
