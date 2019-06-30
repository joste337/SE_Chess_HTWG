package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.Square
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceType.PieceType


trait PieceFactory {
  def getPiece(pieceType: PieceType, pieceColor: PieceColor, square: Square, hasMoved: Boolean = false): Piece
  def getPieceTypeFromString(simpleString: String): PieceType
}

class PieceFactoryImpl extends PieceFactory {
  def getPiece(pieceType: PieceType, pieceColor: PieceColor, square: Square, hasMoved: Boolean = false): Piece = {
    pieceType match {
      case PieceType.PAWN => Pawn(pieceColor, hasMoved, square)
      case PieceType.ROOK => Rook(pieceColor, hasMoved, square)
      case PieceType.KNIGHT => Knight(pieceColor, hasMoved, square)
      case PieceType.BISHOP => Bishop(pieceColor, hasMoved, square)
      case PieceType.QUEEN => Queen(pieceColor, hasMoved, square)
      case PieceType.KING => King(pieceColor, hasMoved, square)
    }
  }

  def getPieceTypeFromString(simpleString: String): PieceType = {
    simpleString match {
      case "P" => PieceType.PAWN
      case "R" => PieceType.ROOK
      case "N" => PieceType.KNIGHT
      case "B" => PieceType.BISHOP
      case "K" => PieceType.KING
      case "Q" => PieceType.QUEEN
    }
  }
}
