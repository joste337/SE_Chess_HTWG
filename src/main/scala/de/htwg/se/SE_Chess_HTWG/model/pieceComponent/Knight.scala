package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridInterface, Square, TurnStatus}
import de.htwg.se.SE_Chess_HTWG.model.moveComponent.MovementHelper
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor

private[pieceComponent] case class Knight(color: PieceColor, hasMoved: Boolean = false, square: Square) extends Piece {
  override def copy(color: PieceColor, hasMoved: Boolean, square: Square): Knight = Knight(color, hasMoved, square)
  override def toString: String = if (color == PieceColor.WHITE) "\u2658" else "\u265E"
  override def toShortcut: String = "N"
  override def getImageName: String = if (color == PieceColor.WHITE) "knight_w" else "knight_b"

  def getPossibleMoves(grid: GridInterface, turnStatus: TurnStatus): List[Square] = {
    val row = this.square.row
    val col = this.square.col

    val possibleSquares: List[(Int, Int)] = List((row + 2, col + 1), (row + 2, col - 1), (row - 2, col + 1),
      (row - 2, col - 1), (row + 1, col + 2), (row + 1, col - 2), (row - 1, col + 2), (row - 1, col - 2))

    MovementHelper.getSquaresInGrid(grid, possibleSquares, this.color).filter(square => MovementHelper.squareIsOfOwnColor(grid, square, this.color))
  }
}
