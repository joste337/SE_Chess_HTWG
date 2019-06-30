package de.htwg.se.SE_Chess_HTWG.model.moveComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridImpl, Square}
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece

class MoveExecutor(var grid: GridImpl) {
  var promotionMove: Boolean = false
  var selectedSquare = grid.specialSquares.selectedSquare
  var turnStatus = grid.turnStatus

  def turnColorMatches(row: Int, col: Int) = grid.turnStatus.pieceColorMatchesTurnColor(grid.getCell((row, col)))
  def moveIsValid(row: Int, col: Int) = getPossibleMoves(grid.getCell(selectedSquare.get).value.get).contains(grid.getCell(row, col))

  def executeMove(row: Int, col: Int): GridImpl = {
    moveToSquare(row, col)
      .orElse(selectSquare(row, col))
      .getOrElse(grid.resetSelectedSquare().unhighlightAll())
  }

  def selectSquare(row: Int, col: Int): Option[GridImpl] = {
    if (!grid.squareIsSelected && turnColorMatches(row, col)) {
      Option{grid.replaceSelectedSquare((row, col)).highlightSquares(getPossibleMoves(grid.getCell((row, col)).value.get))}
    } else None
  }

  def moveToSquare(row: Int, col: Int): Option[GridImpl] = {
    if (grid.squareIsSelected() && moveIsValid(row, col)) {
      Option{grid.moveFromSelectedSquare(row, col).resetSelectedSquare().unhighlightAll().nextTurn()}
    } else None
  }

  def executePromotion(piece: String): MoveResult = ???

  def getPossibleMoves(piece: Piece): List[Square] = piece.getPossibleMoves(grid, turnStatus)
}
