package de.htwg.se.SE_Chess_HTWG.util

object MovementResult extends Enumeration {
  type MovementResult = Value
  val ERROR, SUCCESS, PROMOTION, WHITEKINGTAKEN, BLACKKINGTAKEN = Value

  val movementResultToString = Map[MovementResult, String](
    PROMOTION -> "Pawn can be promoted. Please enter promotion piece by it's shortcut.",
    ERROR -> "You can't move to that square.",
    SUCCESS -> "",
    WHITEKINGTAKEN -> "Black won the game.",
    BLACKKINGTAKEN -> "White won the game."
  )

  def message(movementResult: MovementResult): String = {
    movementResultToString(movementResult)
  }
}
