package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.CellColor.CellColor
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece
import play.api.libs.json.{Json, Writes}

case class Square(value: Option[Piece], row: Int, col: Int, color: CellColor, highlighhted: Boolean = false) extends SquareInterface {
  def isSet: Boolean = value.isDefined

  def replaceValue(value: Option[Piece]): Square = if (value.isEmpty) copy(value, row, col, color) else copy(value.get.replaceSquare(this), row, col, color)
  def replaceColor(color: CellColor): Square = copy(value, row, col, color)

  def isOpposingPiece(piece: Piece): Boolean = isSet && piece.color != value.get.color

  def highlight(): Square = copy(value, row, col, color, true)
  def unHighlight(): Square = copy(value, row, col, color, false)

  override def toString: String = if(isSet) value.get.toString else if (color == CellColor.WHITE) "\u25af" else "\u25ae"
}

object Square {
  implicit val squareWrites = new Writes[Square] {
    def writes(square: Square) = Json.obj(
      "row" -> Json.toJson(square.row),
      "col" -> Json.toJson(square.col),
      "highlighhted" -> square.highlighhted
    )
  }
}
