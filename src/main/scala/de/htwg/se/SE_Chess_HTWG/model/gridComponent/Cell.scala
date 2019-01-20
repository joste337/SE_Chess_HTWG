package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceInterface

case class Cell(var value: Option[PieceInterface], isWhite:Boolean, isHighlighted: Boolean = false) extends CellInterface {
  def isSet: Boolean = value.isDefined

  override def toString: String = if(isSet) value.get.toString else if (isWhite) "\u25af" else "\u25ae" //"w" else "b"
}