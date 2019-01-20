package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece.Piece

trait PieceFactory {
  def getPiece(pieceSimpleString: Piece, isWhite: Boolean, row: Int, col: Int, hasMoved: Boolean = false): PieceInterface
}

class PieceFactoryImpl extends PieceFactory {
  def getPiece(pieceSimpleString: Piece, isWhite: Boolean, row: Int, col: Int, hasMoved: Boolean = false): PieceInterface = {
    pieceSimpleString match {
      case Piece.PAWN => new Pawn(isWhite, row, col, hasMoved)
      case Piece.ROOK => new Rook(isWhite, row, col, hasMoved)
      case Piece.KNIGHT => new Knight(isWhite, row, col, hasMoved)
      case Piece.BISHOP => new Bishop(isWhite, row, col, hasMoved)
      case Piece.QUEEN => new Queen(isWhite, row, col, hasMoved)
      case Piece.KING => new King(isWhite, row, col, hasMoved)
    }
  }
}
