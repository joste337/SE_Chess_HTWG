package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.movement.MovementHelper
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor

private[pieceComponent] case class King(color: PieceColor, var row: Int, var col: Int, var hasMoved: Boolean = false) extends Piece {
  override def toString: String = if (isWhite) "\u2654" else "\u265A"
  override def toShortcut: String = "K"
  override def getImageName: String = if (isWhite) "king_w" else "king_b"

  def getPossibleSquares(grid: GridInterface): List[Cell] = {
    val possibleSquares: List[(Int, Int)] = List((row + 1, col + 1), (row + 1, col - 1), (row + 1, col),
      (row - 1, col - 1), (row - 1, col + 1), (row - 1, col), (row, col + 1), (row, col - 1))
    MovementHelper.getSquaresInGrid(grid, possibleSquares, isWhite)
  }
}
