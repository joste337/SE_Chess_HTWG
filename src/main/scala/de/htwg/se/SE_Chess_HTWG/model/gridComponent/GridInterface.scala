package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.pieceComponent.Piece

trait GridInterface {
  def createNewGrid: GridInterface
  def getCell(row: Int, col: Int): Cell
  def replaceValue(row: Int, col: Int, value: Option[Piece]): Matrix[Cell]
  def setCells(cells: Matrix[Cell]): Unit
  def movePiece(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean
}

trait CellInterface {
  def value: Option[Piece]
  def isHighlighted: Boolean
  def isWhite: Boolean
}