package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridInterface, Square, TurnStatus}
import de.htwg.se.SE_Chess_HTWG.model.moveComponent.MovementHelper
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor

private[pieceComponent] case class Bishop(color: PieceColor, hasMoved: Boolean = false, square: Square) extends Piece {
  override def copy(color: PieceColor, hasMoved: Boolean, square: Square): Bishop = Bishop(color, hasMoved, square)
  override def toString: String = if (color == PieceColor.WHITE) "\u2657" else "\u265D"
  override def toShortcut: String = "B"
  override def getImageName: String = if (color == PieceColor.WHITE) "bishop_w" else "bishop_b"

  override def getPossibleMoves(grid: GridInterface, turnStatus: TurnStatus): List[Square] = {
    val rowsToEighthRow = ((this.square.row + 1) until 8).toList
    val colsToEightCol = ((this.square.col + 1) until 8).toList
    val rowsToFirstRow = (0 until this.square.row).reverse.toList
    val colsToFirstRow = (0 until this.square.col).reverse.toList

    MovementHelper.getSquaresUntilColliding(grid, (rowsToEighthRow zip colsToEightCol), this.color):::
      MovementHelper.getSquaresUntilColliding(grid, (rowsToEighthRow zip colsToFirstRow), this.color):::
      MovementHelper.getSquaresUntilColliding(grid, (rowsToFirstRow zip colsToEightCol), this.color):::
      MovementHelper.getSquaresUntilColliding(grid, (rowsToFirstRow zip colsToFirstRow), this.color)
  }
}
