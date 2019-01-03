package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.pieceComponent._

class Mover(grid: GridInterface) {

  def movePiece(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    if (!grid.getCell(fromRow, fromCol).isSet) false else {
      val fromCell = grid.getCell(fromRow, fromCol)
      val toCell = grid.getCell(toRow, toCol)

      if (getPossibleMovementOptions(fromRow, fromCol).contains(toCell)) {
        grid.setCells(grid.replaceValue(toRow, toCol, fromCell.value))
        grid.setCells(grid.replaceValue(fromRow, fromCol, None))
        true
      } else {
        false
      }
    }
  }

  def getPossibleMovementOptions(row: Int, col: Int): List[Cell] = {
    var possibleMovementOptionsList: List[Cell]= Nil
    if (grid.getCell(row, col).isSet) {
      var piece = grid.getCell(row, col).value.get
      piece match {
        case Pawn(_) => possibleMovementOptionsList = getPossibleMovementOptionsForPawn(row, col, piece.isWhite)
        case Rook(_) => possibleMovementOptionsList = getPossibleMovementOptionsForRook(row, col, piece.isWhite)
        case Knight(_) => possibleMovementOptionsList = getPossibleMovementOptionsForKnight(row, col, piece.isWhite)
        case Bishop(_) => possibleMovementOptionsList = getPossibleMovementOptionsForBishop(row, col, piece.isWhite)
        case Queen(_) => possibleMovementOptionsList = getPossibleMovementOptionsForQueen(row, col, piece.isWhite)
        case King(_) => possibleMovementOptionsList = getPossibleMovementOptionsForKing(row, col, piece.isWhite)
      }
    }
    possibleMovementOptionsList
  }

  def getPossibleMovementOptionsForPawn(row: Int, col: Int, isWhite: Boolean): List[Cell] = {
    var possibleMovementOptionsList: List[Cell]= Nil
    val nextRow = if (isWhite) row + 1 else row -1
    val leftDirectionalCell: Cell = if (col - 1 > 0 ) grid.getCell(nextRow, col - 1) else Cell(None, true)
    val rightDirectionalCell: Cell = if (col + 1 < 8) grid.getCell(nextRow, col + 1) else Cell(None, true)

    if (!grid.getCell(nextRow, col).isSet) possibleMovementOptionsList = grid.getCell(nextRow, col)::possibleMovementOptionsList
    if (leftDirectionalCell.isSet && leftDirectionalCell.isWhite != isWhite) possibleMovementOptionsList = leftDirectionalCell::possibleMovementOptionsList
    if (rightDirectionalCell.isSet && rightDirectionalCell.isWhite != isWhite) possibleMovementOptionsList = rightDirectionalCell::possibleMovementOptionsList

    possibleMovementOptionsList
  }

  def getPossibleMovementOptionsForRook(row: Int, col: Int, isWhite: Boolean): List[Cell] = {
    getMovementOptionsForRange(((row + 1) until 8).toList, List.fill(8)(col), isWhite):::getMovementOptionsForRange(List.fill(8)(row), (0 until (col - 1)).reverse.toList, isWhite):::
      getMovementOptionsForRange((0 until (row - 1)).reverse.toList, List.fill(8)(col), isWhite):::getMovementOptionsForRange(List.fill(8)(row), (0 until (col - 1)).reverse.toList, isWhite)
  }

  def getPossibleMovementOptionsForKnight(row: Int, col: Int, isWhite: Boolean): List[Cell] = {
    var possibleMovementOptionsList: List[Cell]= Nil
    val targetRows: List[Int] = List(row + 2, row + 2, row - 2, row - 2, row + 1, row + 1, row - 1, row - 1)
    val targetCols: List[Int] = List(col + 1, col - 1, col + 1, col - 1, col + 2, col - 2, col + 2, col - 2)

    for(i <- 0 until targetRows.size) {
      val targetRow = targetRows(i)
      val targetCol = targetCols(i)
      if (!(targetRow < 0 || targetRow > 7 || targetCol < 0 || targetCol > 7)) possibleMovementOptionsList = grid.getCell(targetRow, targetCol)::possibleMovementOptionsList
    }

    possibleMovementOptionsList.toStream.filter(cell => cell.isSet && cell.isWhite == isWhite)
    possibleMovementOptionsList
  }

  def getPossibleMovementOptionsForBishop(row: Int, col: Int, isWhite: Boolean): List[Cell] = {
    getMovementOptionsForRange(((row + 1) until 8).toList, ((col + 1) until 8).toList, isWhite):::getMovementOptionsForRange(((row + 1) until 8).toList, (0 until (col - 1)).reverse.toList, isWhite):::
      getMovementOptionsForRange((0 until (row - 1)).reverse.toList, ((col + 1) until 8).toList, isWhite):::getMovementOptionsForRange((0 until (row - 1)).reverse.toList, (0 until (col - 1)).reverse.toList, isWhite)
  }

  def getPossibleMovementOptionsForQueen(row: Int, col: Int, isWhite: Boolean): List[Cell] = {
    getPossibleMovementOptionsForBishop(row, col, isWhite):::getPossibleMovementOptionsForRook(row, col, isWhite)
  }

  def getPossibleMovementOptionsForKing(row: Int, col: Int, isWhite: Boolean): List[Cell] = {
    var possibleMovementOptionsList: List[Cell]= Nil
    val targetRows: List[Int] = List(row + 1, row + 1, row + 1, row - 1, row - 1, row - 1, row, row)
    val targetCols: List[Int] = List(col + 1, col - 1, col, col - 1, col + 1, col, col + 1, col - 1)

    for(i <- 0 until targetRows.size) {
      val targetRow = targetRows(i)
      val targetCol = targetCols(i)
      if (!(targetRow < 0 || targetRow > 7 || targetCol < 0 || targetCol > 7)) possibleMovementOptionsList = grid.getCell(targetRow, targetCol)::possibleMovementOptionsList
    }

    possibleMovementOptionsList.toStream.filter(cell => cell.isSet && cell.isWhite == isWhite)
    possibleMovementOptionsList
  }

  def getMovementOptionsForRange(rowList: List[Int], colList: List[Int], isWhite: Boolean): List[Cell] = {
    var possibleMovementOptionsList: List[Cell]= Nil
    val size: Int = if (rowList.size < colList.size) rowList.size else colList.size

    var foundPiece: Boolean = false
    val i: Int = 0
    while(i < size && foundPiece) {
      val cell = grid.getCell(rowList(i), colList(i))
      if (cell.isSet) {
        foundPiece = true
        if (cell.value.get.isWhite != isWhite) possibleMovementOptionsList = cell::possibleMovementOptionsList
      }
    }
    possibleMovementOptionsList
  }
}
