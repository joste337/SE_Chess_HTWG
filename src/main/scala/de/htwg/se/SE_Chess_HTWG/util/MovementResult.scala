package de.htwg.se.SE_Chess_HTWG.util

object MovementResult extends Enumeration {
  type MovementResult = Value
  val ERROR, SUCCESS, PROMOTION = Value

  val movementResultToString = Map[MovementResult, String](
    PROMOTION -> "Pawn can be promoted. Please enter promotion piece by it's shortcut.",
    ERROR -> "You can't move to that square.",
    SUCCESS -> ""
  )

  def message(movementResult: MovementResult): String = {
    movementResultToString(movementResult)
  }
}
