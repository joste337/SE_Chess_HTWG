package de.htwg.se.SE_Chess_HTWG.model.movement

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

class Move(val grid: GridInterface, val fromRow:Int, val fromCol: Int, val toRow: Int, val toCol: Int) {
  def executeMove: MovementResult = getFromCell.value.get.executeMove(grid, this)
  def getFromCell: Cell = grid.getCell(fromRow, fromCol)
  def getToCell: Cell = grid.getCell(toRow, toCol)

  def isInGrid: Boolean = {
    0 <= fromRow && fromRow <= 7 && 0 <= fromCol && fromCol <= 7 &&
      0 <= toRow && toRow <= 7 && 0 <= toCol && toCol <= 7
  }

  def doMove(): MovementResult = {
      val fromCell = getFromCell
      grid.setCells(grid.replaceValue(toRow, toCol, fromCell.value))
      grid.setCells(grid.replaceValue(fromRow, fromCol, None))
      fromCell.value.get.hasMoved = true
      fromCell.value.get.row = toRow
      fromCell.value.get.col = toCol
      grid.enPassantSquare = None
      MovementResult.SUCCESS
  }
}
