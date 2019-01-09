package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.movement.{Move, MovementHelper}
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

case class Pawn(override val isWhite: Boolean, override var row: Int, override var col: Int, override var hasMoved: Boolean = false) extends Piece {
  override def toString: String = "P"

  def executeMove(grid: GridInterface, move: Move): MovementResult = {
    if (getPossibleSquares(grid) contains move.getToCell) move.doMove() else MovementResult.ERROR
  }

  def getPossibleSquares(grid: GridInterface): List[Cell] = {
    val nextRow = if (isWhite) row + 1 else row -1
    grid.getCell(nextRow, col)::getDiagonalCaptureMoves(grid, nextRow, col)
  }

  def getDiagonalCaptureMoves(grid: GridInterface, nextRow: Int, col: Int): List[Cell] = {
    var possibleSquares: List[Cell] = Nil
    if (col - 1 > 0 && grid.getCell(nextRow, col - 1).isWhite != isWhite) possibleSquares = grid.getCell(nextRow, col - 1)::possibleSquares
    if (col - 1 > 0 && grid.getCell(nextRow, col + 1).isWhite != isWhite) possibleSquares = grid.getCell(nextRow, col + 1)::possibleSquares
    possibleSquares
  }
}
