package de.htwg.se.SE_Chess_HTWG.model.gridComponent.pieceComponent

case class Pawn(override val isWhite: Boolean) extends Piece {
  override def toString: String = "P"

  override def isValidMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    false
  }
}
