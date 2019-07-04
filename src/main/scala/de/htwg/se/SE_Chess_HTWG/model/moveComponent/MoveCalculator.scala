package de.htwg.se.SE_Chess_HTWG.model.moveComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridInterface, Square}

class MoveCalculator {
  def calculateMoves(grid: GridInterface): Map[Square, Move] = {
    Map(grid.cells.getSetCells.map(square => {
      (square, square.value.get.getPossibleMoves(grid, grid.turnStatus))
    }) map { s => (s._1, new Move(s._1, s._2)) } : _*)
  }
}
