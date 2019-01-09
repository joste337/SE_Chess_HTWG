package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.movement.{Move, MovementHelper}
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

case class King(override val isWhite: Boolean, override var row: Int, override var col: Int, override var hasMoved: Boolean = false) extends Piece {
  override def toString: String = "K"

  def executeMove(grid: GridInterface, move: Move): MovementResult = {
    if (getPossibleSquares(grid) contains move.getToCell) move.doMove() else MovementResult.ERROR
  }

  def getPossibleSquares(grid: GridInterface): List[Cell] = {
    val possibleSquares: List[(Int, Int)] = List((row + 1, col + 1), (row + 1, col - 1), (row + 1, col),
      (row - 1, col - 1), (row - 1, col + 1), (row - 1, col), (row, col + 1), (row, col - 1))
    MovementHelper.getSquaresInGrid(grid, possibleSquares, isWhite)
  }
}
