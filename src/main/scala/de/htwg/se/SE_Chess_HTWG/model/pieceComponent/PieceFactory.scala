package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceType.PieceType

trait PieceFactory {
  def getPiece(pieceType: PieceType, isWhite: Boolean, row: Int, col: Int, hasMoved: Boolean = false): Piece
}

class PieceFactoryImpl extends PieceFactory {
  def getPiece(pieceType: PieceType, isWhite: Boolean, row: Int, col: Int, hasMoved: Boolean = false): Piece = {
    pieceType match {
      case PieceType.PAWN => Pawn(isWhite, row, col, hasMoved)
      case PieceType.ROOK => Rook(isWhite, row, col, hasMoved)
      case PieceType.KNIGHT => Knight(isWhite, row, col, hasMoved)
      case PieceType.BISHOP => Bishop(isWhite, row, col, hasMoved)
      case PieceType.QUEEN => Queen(isWhite, row, col, hasMoved)
      case PieceType.KING => King(isWhite, row, col, hasMoved)
    }
  }
}
