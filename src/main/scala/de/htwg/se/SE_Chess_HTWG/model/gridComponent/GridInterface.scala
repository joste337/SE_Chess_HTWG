package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

trait GridInterface {
  var enPassantSquare: Option[Cell]
  var promotionSquare: Option[Cell]
  def getCell(row: Int, col: Int): Cell
  def setCells(cells: Matrix): Unit
  def replaceColor(row: Int, col: Int, isWhite: Boolean): Matrix
  def replaceValue(row: Int, col: Int, value: Option[Piece]): Matrix
  def movePiece(move: Move): MovementResult
  def createNewGrid: GridInterface
  def promotePiece(row: Int, col: Int, pieceShortcut: String): MovementResult
}

trait CellInterface {
  def value: Option[Piece]
  def isHighlighted: Boolean
  def isWhite: Boolean
}