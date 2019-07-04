package de.htwg.se.SE_Chess_HTWG.model.moveComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.Square
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.{King, PieceColor}

class Move(val sourceSquare: Square, val destSquqares: List[Square]) {
  def whiteInChess: Boolean = inChess(PieceColor.WHITE)
  def blackInChess: Boolean = inChess(PieceColor.BLACK)

  private def inChess(pieceColor: PieceColor): Boolean = {
    destSquqares
      .map(square => if (square.isSet && square.value.get.isInstanceOf[King] && square.value.get.color == pieceColor) true else false)
      .reduce(_ || _)
  }
}
