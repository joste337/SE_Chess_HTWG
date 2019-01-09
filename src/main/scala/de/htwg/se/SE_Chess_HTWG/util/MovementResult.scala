package de.htwg.se.SE_Chess_HTWG.util

object MovementResult extends Enumeration {
  type MovementResult = Value
  val ERROR, SUCCESS = Value

  val movementResultToString = Map[MovementResult, String](
    ERROR -> "You can't move to that square.",
    SUCCESS -> ""
  )

  def message(movementResult: MovementResult): String = {
    movementResultToString(movementResult)
  }
}
