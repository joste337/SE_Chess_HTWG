package de.htwg.se.SE_Chess_HTWG.controller

import de.htwg.se.SE_Chess_HTWG.controller.GameStatus.GameStatus
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

import scala.swing.Publisher
import scala.swing.event.Event

trait ControllerInterface extends Publisher {
  var clickedCell: Option[(Int, Int)]
  def gameStatus: GameStatus
  def gridToString: String
  def createNewGrid: Unit
  def selectSquare(row: Int, col: Int): Unit
  def deselectSquare: Unit
  def getSelectedSquare: Option[(Int, Int)]
  def movePiece(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): MovementResult
  def promotePiece(row: Int, col: Int, pieceShortcut: String): MovementResult
  var grid: GridInterface
  def undo: Unit
  def redo: Unit
  def save: Unit
  def load: Unit
}

class CellChanged extends Event
