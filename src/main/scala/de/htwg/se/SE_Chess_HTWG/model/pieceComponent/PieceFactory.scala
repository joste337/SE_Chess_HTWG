package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceType.PieceType

trait PieceFactory {
  def getPiece(pieceType: PieceType, isWhite: Boolean, row: Int, col: Int, hasMoved: Boolean = false): Piece
}

class PieceFactoryImpl extends PieceFactory {
  def getPiece(pieceType: PieceType, isWhite: Boolean, row: Int, col: Int, hasMoved: Boolean = false): Piece = {
    pieceType match {
      case PieceType.PAWN => Pawn(Piece.getPieceColor(isWhite), row, col, hasMoved)
      case PieceType.ROOK => Rook(Piece.getPieceColor(isWhite), row, col, hasMoved)
      case PieceType.KNIGHT => Knight(Piece.getPieceColor(isWhite), row, col, hasMoved)
      case PieceType.BISHOP => Bishop(Piece.getPieceColor(isWhite), row, col, hasMoved)
      case PieceType.QUEEN => Queen(Piece.getPieceColor(isWhite), row, col, hasMoved)
      case PieceType.KING => King(Piece.getPieceColor(isWhite), row, col, hasMoved)
    }
  }
}
