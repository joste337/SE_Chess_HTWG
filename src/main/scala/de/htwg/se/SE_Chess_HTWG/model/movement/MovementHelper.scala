package de.htwg.se.SE_Chess_HTWG.model.movement

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}

object MovementHelper {
  def getSquaresInGrid(grid: GridInterface, squareList: List[(Int, Int)], sourceCellIsWhite: Boolean): List[Cell] = {
    squareList.filter(squareIsInGrid).filter(square => squareIsOfOwnColor(grid, square, sourceCellIsWhite)).map(square => grid.getCell(square._1, square._2))
  }

  def getSquaresUntilColliding(grid: GridInterface, squareList: List[(Int, Int)], sourceCellIsWhite: Boolean): List[Cell] = {
    removeAfterColliding(getSquaresInGrid(grid, squareList, sourceCellIsWhite), sourceCellIsWhite)
  }

  def squareIsOfOwnColor(grid: GridInterface, square: (Int, Int), sourceCellIsWhite: Boolean): Boolean = {
    !grid.getCell(square._1, square._2).isSet ||
      grid.getCell(square._1, square._2).isSet && grid.getCell(square._1, square._2).value.get.isWhite != sourceCellIsWhite
  }

  def squareIsInGrid(square: (Int, Int)): Boolean = square._1 > 0 && square._1 < 7 && square._2 > 0 && square._2 < 7

  def removeAfterColliding(squareList: List[Cell], sourceCellIsWhite: Boolean): List[Cell] = {
    var possibleMovementOptionsList: List[Cell]= Nil
    var foundPiece: Boolean = false
    var i: Int = 0
    while(i < squareList.size && !foundPiece) {
      val cell = squareList(i)
      possibleMovementOptionsList = cell::possibleMovementOptionsList
      if (cell.isSet) foundPiece = true
      i = i + 1
    }
    possibleMovementOptionsList
  }
}
