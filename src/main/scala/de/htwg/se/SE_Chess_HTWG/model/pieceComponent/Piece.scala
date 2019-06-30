package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridInterface, Square, TurnStatus}
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor
import play.api.libs.json.{Json, Writes}

trait Piece {
  val color: PieceColor
  val square: Square
  val hasMoved: Boolean
  def isWhite: Boolean = if (this.color == PieceColor.WHITE) true else false
  def matchesColor(color: PieceColor): Boolean = this.color == color
  def replaceSquare(square: Square): Option[Piece] = Some(move(square))
  def move(square: Square): Piece = copy(color, true, square)
  def toShortcut: String
  def getImageName: String
  def getPossibleMoves(grid: GridInterface, turnStatus: TurnStatus): List[Square]
  def copy(color: PieceColor, hasMoved: Boolean = false, square: Square): Piece
}

object Piece {
  implicit val pieceWrites = new Writes[Piece] {
    def writes(piece: Piece) = Json.obj(
      "row" -> piece.square.row,
      "col" -> piece.square.col,
      "value" -> piece.toShortcut,
      "isWhite" -> piece.isWhite,
      "hasMoved" -> piece.hasMoved
    )
  }
}

object PieceColor extends Enumeration {
  type PieceColor = Value
  val WHITE, BLACK = Value
}

object PieceType extends Enumeration {
  type PieceType = Value
  val PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING = Value
}