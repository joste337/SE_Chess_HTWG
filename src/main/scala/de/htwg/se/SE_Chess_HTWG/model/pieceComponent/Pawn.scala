package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

case class Pawn(override val isWhite: Boolean, override var row: Int, override var col: Int, override var hasMoved: Boolean = false) extends Piece {
  override def toString: String = if (isWhite) "\u2659" else "\u265F"

  def executeMove(grid: GridInterface, move: Move): MovementResult = {
    if (getEnPassantMove(grid) == move.getToCell) {
      doEnPassantMove(grid, move)
    } else if (getFirstTwoSquareMove(grid) == move.getToCell) {
      doFirstTwoSquaresMove(grid, move)
    } else if (getPossibleSquares(grid) contains move.getToCell) {
      grid.promotionSquare = if (getPromotionMove(grid) == grid.getCell(move.toRow, move.toCol)) Some(grid.getCell(move.toRow, move.toCol)) else None
      move.doMove()
    } else {
      MovementResult.ERROR
    }
  }

  def getPossibleSquares(grid: GridInterface): List[Cell] = {
    val nextRow = if (isWhite) row + 1 else row -1
    getBaseSquares(grid)::getFirstTwoSquareMove(grid)::getEnPassantMove(grid)::getDiagonalCaptureMoves(grid, nextRow)
  }

  def getBaseSquares(grid: GridInterface): Cell = {
    val nextRow = if (isWhite) row + 1 else row -1
    grid.getCell(nextRow, col)
  }

  def getDiagonalCaptureMoves(grid: GridInterface, nextRow: Int): List[Cell] = {
    var possibleSquares: List[Cell] = Nil
    if (col - 1 > 0 && grid.getCell(nextRow, col - 1).isWhite != isWhite) possibleSquares = grid.getCell(nextRow, col - 1)::possibleSquares
    if (col - 1 > 0 && grid.getCell(nextRow, col + 1).isWhite != isWhite) possibleSquares = grid.getCell(nextRow, col + 1)::possibleSquares
    possibleSquares
  }

  def getPromotionMove(grid: GridInterface): Cell = {
    val endRow = if (isWhite) 7 else 0
    if (getBaseSquares(grid).value.getOrElse(10) == endRow) getBaseSquares(grid) else null
  }

  def getEnPassantMove(grid: GridInterface): Cell = {
    if (grid.enPassantSquare.isDefined && grid.enPassantSquare.get.isSet) grid.enPassantSquare.get else null
  }

  def getFirstTwoSquareMove(grid: GridInterface): Cell = {
    val nextNextRow = if (isWhite) row + 2 else row -2
    val nextRow = if (isWhite) row + 1 else row -1
    if (!hasMoved && !grid.getCell(nextRow, col).isSet && !grid.getCell(nextNextRow, col).isSet) grid.getCell(nextNextRow, col) else null
  }

  def doEnPassantMove(grid: GridInterface, move: Move): MovementResult = {
    grid.replaceValue(grid.enPassantSquare.get.value.get.row, grid.enPassantSquare.get.value.get.col, None)
    move.doMove()
  }

  def doFirstTwoSquaresMove(grid: GridInterface, move: Move): MovementResult = {
    move.doMove()
    grid.enPassantSquare = Some(grid.getCell(col, if (isWhite) row - 1 else row + 1))
    MovementResult.SUCCESS
  }
}
