package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

trait GridInterface {
  var enPassantSquare: Option[Cell]
  var promotionSquare: Option[Cell]
  var selectedSquare: Option[(Int, Int)]
  def getCell(row: Int, col: Int): Cell
  def setCells(cells: Matrix): Unit
  def getSetCells(): List[Cell]
  def replaceColor(row: Int, col: Int, isWhite: Boolean): Matrix
  def replaceValue(row: Int, col: Int, value: Option[Piece]): Matrix
  def movePiece(move: Move): MovementResult
  def createNewGridWithPieces: GridInterface
  def createNewGridWithoutPieces: GridInterface
  def promotePiece(row: Int, col: Int, pieceShortcut: String): MovementResult
  def getPieceForColumn(row: Int, col: Int, isWhite: Boolean): Piece
}

trait CellInterface {
  def value: Option[Piece]
  def isWhite: Boolean
  def isSet: Boolean
}