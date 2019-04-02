package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.movement.{Move, MovementHelper}
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

private[pieceComponent] case class Knight(isWhite: Boolean, var row: Int, var col: Int, var hasMoved: Boolean = false) extends Piece{
  override def toString: String = if (isWhite) "\u2658" else "\u265E"
  override def toSimpleString: String = "N"
  override def getImageName: String = if (isWhite) "knight_w" else "knight_b"

  def executeMove(grid: GridInterface, move: Move): MovementResult = {
    if (getPossibleSquares(grid) contains move.getToCell) move.doMove() else MovementResult.ERROR
  }

  def getPossibleSquares(grid: GridInterface): List[Cell] = {
    val possibleSquares: List[(Int, Int)] = List((row + 2, col + 1), (row + 2, col - 1), (row - 2, col + 1),
      (row - 2, col - 1), (row + 1, col + 2), (row + 1, col - 2), (row - 1, col + 2), (row - 1, col - 2))
    MovementHelper.getSquaresInGrid(grid, possibleSquares, isWhite)
  }
}
