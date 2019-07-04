package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.CellColor.CellColor
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor

trait GridInterface {
  val cells: Matrix
  val specialSquares: SpecialSquares
  val turnStatus: TurnStatus

  def createNewGrid: GridInterface

  def getSquare(square: (Int, Int)): Square

  def replacePiece(row: Int, col: Int, value: Option[Piece]): GridInterface
  def replacePiece(square: (Int, Int), value: Option[Piece]): GridInterface

  def moveFromSelectedSquare(destSquare: (Int, Int)): GridInterface
  def executeMove(row: Int, col: Int): GridInterface
  def replaceSelectedSquare(square: (Int, Int)): GridInterface
  def resetSelectedSquare(): GridInterface
  def squareIsSelected(): Boolean

  def setTurnStatus(turnStatus: TurnStatus): GridInterface
  def nextTurn(): GridInterface

  def unhighlightAll(): GridInterface
  def highlightSquares(squares: List[Square]): GridInterface
  
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