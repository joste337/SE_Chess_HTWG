package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.CellColor.CellColor
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor

trait GridInterface {
  val cells: Matrix
  val specialSquares: SpecialSquares
  val turnStatus: TurnStatus

  def createNewGrid: GridImpl

  def getCell(square: (Int, Int)): Square

  def replacePiece(row: Int, col: Int, value: Option[Piece]): GridImpl
  def replacePiece(square: (Int, Int), value: Option[Piece]): GridImpl

  def moveFromSelectedSquare(destSquare: (Int, Int)): GridImpl
  def executeMove(row: Int, col: Int): GridImpl
  def replaceSelectedSquare(square: (Int, Int)): GridImpl
  def resetSelectedSquare(): GridImpl
  def squareIsSelected(): Boolean

  def setTurnStatus(turnStatus: TurnStatus): GridImpl
  def nextTurn(): GridImpl

  def unhighlightAll(): GridImpl
  def highlightSquares(squares: List[Square]): GridImpl
  
  def getPieceForColumn(row: Int, col: Int, pieceColor: PieceColor): Piece
}

trait SquareInterface {
  def isSet: Boolean

  def replaceValue(value: Option[Piece]): Square
  def replaceColor(color: CellColor): Square

  def isOpposingPiece(piece: Piece): Boolean

  def highlight(): Square
  def unHighlight(): Square
}