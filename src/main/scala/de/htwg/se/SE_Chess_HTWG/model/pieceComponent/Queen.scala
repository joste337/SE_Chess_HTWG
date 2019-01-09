package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.movement.{Move, MovementHelper}
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

case class Queen(override val isWhite: Boolean, override var row: Int, override var col: Int, override var hasMoved: Boolean = false) extends Piece {
  override def toString: String = "Q"

  def executeMove(grid: GridInterface, move: Move): MovementResult = {
    if (getPossibleSquares(grid) contains move.getToCell) move.doMove() else MovementResult.ERROR
  }

  def getPossibleSquares(grid: GridInterface): List[Cell] = {
    MovementHelper.getSquaresUntilColliding(grid, (row + 1 until 8).toList zip List.fill(8)(col), isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, List.fill(8)(row) zip (0 until (col - 1)), isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, (0 until (row - 1)).reverse.toList zip List.fill(8)(col), isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, List.fill(8)(row) zip (0 until (col - 1)).reverse.toList, isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, ((row + 1) until 8).toList zip ((col + 1) until 8).toList, isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, ((row + 1) until 8).toList zip (0 until (col - 1)).reverse.toList, isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, (0 until (row - 1)).reverse.toList zip ((col + 1) until 8).toList, isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, (0 until (row - 1)).reverse.toList zip (0 until (col - 1)).reverse.toList, isWhite)
  }
}
