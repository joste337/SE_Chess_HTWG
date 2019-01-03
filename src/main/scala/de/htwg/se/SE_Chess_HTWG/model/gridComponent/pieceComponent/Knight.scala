package de.htwg.se.SE_Chess_HTWG.model.gridComponent.pieceComponent

case class Knight(override val isWhite: Boolean) extends Piece{
  override def toString: String = if (isWhite) { "\u2658" } else { "\u265E"}

  override def isValidMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    false
  }
}
