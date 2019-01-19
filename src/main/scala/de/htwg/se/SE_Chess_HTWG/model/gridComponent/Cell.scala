package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece
import play.api.libs.json.{Format, Json, Writes}

case class Cell(var value: Option[Piece], isWhite:Boolean, isHighlighted: Boolean = false) extends CellInterface {
  def isSet: Boolean = value.isDefined

  override def toString: String = if(isSet) value.get.toString else if (isWhite) "\u25af" else "\u25ae" //"w" else "b"
}

object Cell {
  implicit val cellWrites = new Writes[Cell] {
    def writes(cell: Cell) = Json.obj(
      "value" -> (if (!cell.isSet) "" else Json.toJson(cell.value.get)),
      "isWhite" -> cell.isWhite
    )
  }
}