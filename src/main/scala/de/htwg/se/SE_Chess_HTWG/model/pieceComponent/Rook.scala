package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridInterface, Square, TurnStatus}
import de.htwg.se.SE_Chess_HTWG.model.moveComponent.MovementHelper
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor

private[pieceComponent] case class Rook(color: PieceColor, hasMoved: Boolean = false, square: Square) extends Piece {
  override def copy(color: PieceColor, hasMoved: Boolean, square: Square): Rook = Rook(color, hasMoved, square)
  override def toString: String = if (color == PieceColor.WHITE) "\u2656" else "\u265C"
  override def toShortcut: String = "R"
  override def getImageName: String = if (color == PieceColor.WHITE) "rook_w" else "rook_b"

  def getPossibleMoves(grid: GridInterface, turnStatus: TurnStatus): List[Square] = {
    val row = this.square.row
    val col = this.square.col
    val rowsToEighthRow = ((row + 1) until 8).toList
    val colsToEightCol = ((col + 1) until 8).toList
    val rowsToFirstRow = (0 until row).reverse.toList
    val colsToFirstRow = (0 until col).reverse.toList

    MovementHelper.getSquaresUntilColliding(grid, (rowsToEighthRow zip List.fill(8)(col)), this.color):::
      MovementHelper.getSquaresUntilColliding(grid, (List.fill(8)(row) zip colsToFirstRow), this.color):::
      MovementHelper.getSquaresUntilColliding(grid, (rowsToFirstRow zip List.fill(8)(col)), this.color):::
      MovementHelper.getSquaresUntilColliding(grid, (List.fill(8)(row) zip colsToEightCol), this.color)
  }
}
