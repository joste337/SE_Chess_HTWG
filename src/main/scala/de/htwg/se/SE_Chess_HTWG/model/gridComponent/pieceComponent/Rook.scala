package de.htwg.se.SE_Chess_HTWG.model.gridComponent.pieceComponent

case class Rook(override val isWhite: Boolean) extends Piece {
  override def toString: String = "R"

  override def isValidMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    fromRow == toRow || fromCol == toCol
  }
}
