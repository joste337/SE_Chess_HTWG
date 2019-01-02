package de.htwg.se.SE_Chess_HTWG.util

object MovementError extends Enumeration {
  type MovementError = Value
  val SOURCE_SQUARE_NOT_OWN_PIECE, DEStINATION_SQUARE_OWN_PIECE, ANY, NONE = Value

  val gameStatusToString = Map[MovementError, String](
    SOURCE_SQUARE_NOT_OWN_PIECE -> "The source square is not occupied by any of your pieces.",
    DEStINATION_SQUARE_OWN_PIECE -> "The destination square is occupied by one of your pieces.",
    ANY -> "You can't move to that square.",
    NONE -> ""
  )

  def message(movementError: MovementError): String = {
    gameStatusToString(movementError)
  }
}
