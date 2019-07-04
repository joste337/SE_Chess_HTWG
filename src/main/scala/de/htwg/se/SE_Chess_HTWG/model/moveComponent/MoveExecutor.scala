package de.htwg.se.SE_Chess_HTWG.model.moveComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridInterface, Square}
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece

class MoveExecutor(var grid: GridInterface) {
  var promotionMove: Boolean = false
  var selectedSquare = grid.specialSquares.selectedSquare
  var turnStatus = grid.turnStatus

  def turnColorMatches(row: Int, col: Int) = grid.turnStatus.pieceColorMatchesTurnColor(grid.getSquare((row, col)))
  def moveIsValid(row: Int, col: Int) = getPossibleMoves(grid.getSquare(selectedSquare.get).value.get).contains(grid.getSquare(row, col))

  def executeMove(row: Int, col: Int): GridInterface = {
    moveToSquare(row, col)
      .orElse(selectSquare(row, col))
      .getOrElse(grid.resetSelectedSquare().unhighlightAll())
  }

  def selectSquare(row: Int, col: Int): Option[GridInterface] = {
    if (!grid.squareIsSelected && turnColorMatches(row, col)) {
      Option{grid.replaceSelectedSquare((row, col)).highlightSquares(getPossibleMoves(grid.getSquare((row, col)).value.get))}
    } else {
      None
    }
  }

  def moveToSquare(row: Int, col: Int): Option[GridInterface] = {
    if (grid.squareIsSelected() && moveIsValid(row, col)) {
      Option{grid.moveFromSelectedSquare(row, col).resetSelectedSquare().unhighlightAll().nextTurn()}
    } else {
      None
    }
  }

  def executePromotion(piece: String): MoveResult = ???

  def getPossibleMoves(piece: Piece): List[Square] = piece.getPossibleMoves(grid, turnStatus)
}
