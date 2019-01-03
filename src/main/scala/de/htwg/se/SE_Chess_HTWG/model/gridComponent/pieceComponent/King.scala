package de.htwg.se.SE_Chess_HTWG.model.gridComponent.pieceComponent

import math.abs

case class King(override val isWhite: Boolean) extends Piece {
  override def toString: String = if (isWhite) { "\u2654" } else { "\u265A"}

  override def isValidMove(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    abs(fromRow - fromRow) <= 1 && abs(fromCol - toCol) <= 1
  }
}
