package de.htwg.se.SE_Chess_HTWG.model.gridComponent.pieceComponent

trait Piece {
  val isWhite: Boolean
  def isValidMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean
}
