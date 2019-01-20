package de.htwg.se.SE_Chess_HTWG.model.movement

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

class Move (var grid: GridInterface, val fromRow:Int, val fromCol: Int, val toRow: Int, val toCol: Int) {
  def executeMove: MovementResult = getFromCell.value.get.executeMove(grid, this)
  def getFromCell: Cell = grid.getCell(fromRow, fromCol)
  def getToCell: Cell = grid.getCell(toRow, toCol)

  def isInGrid: Boolean = {
    0 <= fromRow && fromRow <= 7 && 0 <= fromCol && fromCol <= 7 &&
      0 <= toRow && toRow <= 7 && 0 <= toCol && toCol <= 7
  }

  def doMove(): MovementResult = {
    val fromCell = getFromCell
    val toCell = getToCell
    grid.setCells(grid.replaceValue(toRow, toCol, fromCell.value))
    grid.setCells(grid.replaceValue(fromRow, fromCol, None))
    fromCell.value.get.hasMoved = true
    fromCell.value.get.row = toRow
    fromCell.value.get.col = toCol
    grid.enPassantSquare = None
    if (toCell.isSet && toCell.value.get.toSimpleString == "K") {
      if (toCell.value.get.isWhite) MovementResult.WHITEKINGTAKEN else MovementResult.BLACKKINGTAKEN
    } else {
      MovementResult.SUCCESS
    }
  }
}
