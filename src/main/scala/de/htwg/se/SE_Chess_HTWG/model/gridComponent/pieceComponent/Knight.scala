package de.htwg.se.SE_Chess_HTWG.model.gridComponent.pieceComponent

case class Knight(override val isWhite: Boolean) extends Piece{
  override def toString: String = "N"

  override def isValidMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    false
  }
}
