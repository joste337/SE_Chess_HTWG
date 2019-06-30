package de.htwg.se.SE_Chess_HTWG.model.moveComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridInterface, Square}
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor


object MovementHelper {
  def getSquaresInGrid(grid: GridInterface, squareList: List[(Int, Int)], pieceColor: PieceColor): List[Square] = {
    squareList.filter(squareIsInGrid).map(square => grid.getCell(square._1, square._2))
  }

  def getSquaresUntilColliding(grid: GridInterface, squareList: List[(Int, Int)], pieceColor: PieceColor): List[Square] = {
    removeAfterColliding(getSquaresInGrid(grid, squareList, pieceColor), grid).filter(square => squareIsOfOwnColor(grid, square, pieceColor))
  }

  def squareIsOfOwnColor(grid: GridInterface, square: Square, pieceColor: PieceColor): Boolean = {
    !grid.getCell(square.row, square.col).isSet ||
      grid.getCell(square.row, square.col).isSet && grid.getCell(square.row, square.col).value.get.color != pieceColor
  }

  def squareIsInGrid(square: (Int, Int)): Boolean = square._1 >= 0 && square._1 <= 7 && square._2 >= 0 && square._2 <= 7

  def removeAfterColliding(squareList: List[Square], grid: GridInterface): List[Square] = {
    var possibleMovementOptionsList: List[Square]= Nil
    var foundPiece: Boolean = false
    var i: Int = 0

    while(i < squareList.size && !foundPiece) {
      val cell = grid.getCell(squareList(i).row, squareList(i).col)
      possibleMovementOptionsList = cell::possibleMovementOptionsList
      if (cell.isSet) foundPiece = true
      i = i + 1
    }
    possibleMovementOptionsList
  }

//  def isChessForColor(grid: GridInterface, pieceColor: PieceColor): Boolean = {
//    grid.cells.getSetCells
//      .map(square => if (square.isSet) Nil else square.value.get.getPossibleMoves(grid, grid.turnStatus))
//      .map(moveLists => moveLists.flatMap(moveList => moveList.value).filter(piece => piece match {
//        case King.getClass => if (piece.color == pieceColor) true else false
//        case _ => false
//      }).contains(true))
//      .contains(true)
//  }
}
