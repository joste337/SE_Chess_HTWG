package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.movement.{Move, MovementHelper}
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

case class Queen(override val isWhite: Boolean, override var row: Int, override var col: Int, override var hasMoved: Boolean = false) extends Piece {
  override def toString: String = if (isWhite) "\u2655" else "\u265B"

  def executeMove(grid: GridInterface, move: Move): MovementResult = {
    if (getPossibleSquares(grid) contains move.getToCell) move.doMove() else MovementResult.ERROR
  }

  def getPossibleSquares(grid: GridInterface): List[Cell] = {
    val rowsToEighthRow = ((row + 1) until 8).toList
    val colsToEightCol = ((col + 1) until 8).toList
    val rowsToFirstRow = (0 until (row - 1)).reverse.toList
    val colsToFirstRow = (0 until (col - 1)).reverse.toList

    MovementHelper.getSquaresUntilColliding(grid, (rowsToEighthRow zip colsToEightCol), isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, (rowsToEighthRow zip colsToFirstRow), isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, (rowsToFirstRow zip colsToEightCol), isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, (rowsToFirstRow zip colsToFirstRow), isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, (rowsToEighthRow zip List.fill(8)(col)), isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, (List.fill(8)(row) zip colsToFirstRow), isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, (rowsToFirstRow zip List.fill(8)(col)), isWhite):::
      MovementHelper.getSquaresUntilColliding(grid, (List.fill(8)(row) zip colsToEightCol), isWhite)
  }
}
