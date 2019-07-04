package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridInterface, Square, TurnStatus}
import de.htwg.se.SE_Chess_HTWG.model.moveComponent.MovementHelper
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor

case class King(color: PieceColor, hasMoved: Boolean = false, square: Square) extends Piece {
  override def copy(color: PieceColor, hasMoved: Boolean, square: Square): Piece = King(color, hasMoved, square)
  override def toString: String = if (color == PieceColor.WHITE) "\u2654" else "\u265A"
  override def toShortcut: String = "K"
  override def getImageName: String = if (color == PieceColor.WHITE) "king_w" else "king_b"

  override def getPossibleMoves(grid: GridInterface, turnStatus: TurnStatus): List[Square] = {
    val row = this.square.row
    val col = this.square.col

    val possibleSquares: List[(Int, Int)] = List((row + 1, col + 1), (row + 1, col - 1), (row + 1, col),
      (row - 1, col - 1), (row - 1, col + 1), (row - 1, col), (row, col + 1), (row, col - 1))

    MovementHelper.getSquaresInGrid(grid, possibleSquares, this.color).filter(square => MovementHelper.squareIsOfOwnColor(grid, square, this.color))
  }
}
