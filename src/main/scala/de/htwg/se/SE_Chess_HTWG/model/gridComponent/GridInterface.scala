package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

trait GridInterface {
  var enPassantSquare: Option[Cell]
  var promotionSquare: Option[Cell]
  def createNewGrid: GridInterface
  def getCell(row: Int, col: Int): Cell
  def replaceValue(row: Int, col: Int, value: Option[Piece]): Matrix[Cell]
  def setCells(cells: Matrix[Cell]): Unit
  def movePiece(move: Move): MovementResult
  def promotePiece(row: Int, col: Int, pieceShortcut: String): MovementResult
}

trait CellInterface {
  def value: Option[Piece]
  def isHighlighted: Boolean
  def isWhite: Boolean
}