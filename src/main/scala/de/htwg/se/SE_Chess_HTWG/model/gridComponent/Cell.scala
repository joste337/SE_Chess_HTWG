package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.pieceComponent.Piece

case class Cell(var value: Option[Piece], isWhite:Boolean, isHighlighted: Boolean = false) extends CellInterface {
  def isSet: Boolean = value.isDefined

  override def toString: String = if(isSet) value.get.toString else if (isWhite) "w" else "b"
}
