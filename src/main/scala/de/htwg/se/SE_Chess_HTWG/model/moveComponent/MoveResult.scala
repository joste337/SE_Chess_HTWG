package de.htwg.se.SE_Chess_HTWG.model.moveComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridImpl, GridInterface, TurnStatus}
import de.htwg.se.SE_Chess_HTWG.model.moveComponent.Result.Result

case class MoveResult(grid: GridInterface, turnStatus: TurnStatus, result: Result) {

}

object Result extends Enumeration {
  type Result = Value
  val SUCCESS, PROMOTION, ERROR = Value
}