package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridInterface, Square, TurnStatus}
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor

private[pieceComponent] case class Pawn(color: PieceColor, hasMoved: Boolean = false, square: Square) extends Piece {
  override def copy(color: PieceColor, hasMoved: Boolean, square: Square): Pawn = Pawn(color, hasMoved, square)
  override def toString: String = if (color == PieceColor.WHITE) "\u2659" else "\u265F"
  override def toShortcut: String = "P"
  override def getImageName: String = if (color == PieceColor.WHITE) "pawn_w" else "pawn_b"

  var isPromotionMove = false
  val row = this.square.row
  val col = this.square.col


  def getPossibleMoves(grid: GridInterface, turnStatus: TurnStatus): List[Square] = {
    val nextRow = if (this.color == PieceColor.WHITE) this.square.row + 1 else this.square.row - 1
    getBaseSquares(grid)::getFirstTwoSquareMove(grid):::getEnPassantMove(grid):::getDiagonalCaptureMoves(grid, nextRow)
  }

  def getBaseSquares(grid: GridInterface): Square = {
    val nextRow = if (isWhite) row + 1 else row -1
    grid.getCell(nextRow, col)
  }

  def getDiagonalCaptureMoves(grid: GridInterface, nextRow: Int): List[Square] = {
    var possibleSquares: List[Square] = Nil
    if (col - 1 >= 0 && grid.getCell(nextRow, col - 1).isOpposingPiece(this)) possibleSquares = grid.getCell(nextRow, col - 1)::possibleSquares
    if (col + 1 <= 7 && grid.getCell(nextRow, col + 1).isOpposingPiece(this)) possibleSquares = grid.getCell(nextRow, col + 1)::possibleSquares
    possibleSquares
  }

  //  def getPromotionMove(grid: GridImpl): Square = {
  //    val endRow = if (isWhite) 7 else 0
  //    if (getBaseSquares(grid).value.getOrElse(10) == endRow) getBaseSquares(grid) else null
  //  }

  def getEnPassantMove(grid: GridInterface): List[Square] = {
    var possibleSquares: List[Square] = Nil
    if (grid.specialSquares.enPassantSquare.isDefined) grid.getCell(grid.specialSquares.enPassantSquare.get._1, grid.specialSquares.enPassantSquare.get._2)::possibleSquares else Nil
  }

  def getFirstTwoSquareMove(grid: GridInterface): List[Square] = {
    var possibleSquares: List[Square] = Nil
    val nextNextRow = if (isWhite) row + 2 else row -2
    val nextRow = if (isWhite) row + 1 else row -1

    if (!this.hasMoved && !grid.getCell(nextRow, col).isSet && !grid.getCell(nextNextRow, col).isSet) grid.getCell(nextNextRow, col)::possibleSquares else Nil
  }
}
