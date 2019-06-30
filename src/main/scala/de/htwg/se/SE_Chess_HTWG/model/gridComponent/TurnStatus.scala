package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.Turn.Turn
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor
import play.api.libs.json.Json

case class TurnStatus(turn: Turn) {
  def this() = this(Turn.IDLE)

  def pieceColorMatchesTurnColor(square: Square): Boolean = Turn.pieceColorMatchesTurnColor(this.turn, square)
  def nextTurn(): TurnStatus = TurnStatus(Turn.nextTurn(turn))
}

object TurnStatus {
  def toOutputString(turnStatus: TurnStatus): String = {
    turnStatus.turn match {
      case Turn.P1 => "p1"
      case Turn.P1PROMO => "p2"
      case Turn.P2 => "p1prom"
      case Turn.P2PROMO => "p2prom"
      case _ => "idle"
    }
  }

  def fromInputString(turn: String): TurnStatus = {
    turn match {
      case "p1" => TurnStatus(Turn.P1)
      case "p2" => TurnStatus(Turn.P2)
      case "p1prom" => TurnStatus(Turn.P1PROMO)
      case "p2prom" => TurnStatus(Turn.P2PROMO)
      case "idle" => TurnStatus(Turn.IDLE)
    }
  }
}

object Turn extends Enumeration {
  type Turn = Value
  val IDLE, P1, P2, P1PROMO, P2PROMO = Value

  def pieceColorMatchesTurnColor(turnStatus: Turn, square: Square): Boolean = {
    turnStatus match {
      case P1 => square.isSet && square.value.get.color == PieceColor.WHITE
      case P2 => square.isSet && square.value.get.color == PieceColor.BLACK
      case _ => false
    }
  }

  def nextTurn(turnStatus: Turn): Turn = {
    turnStatus match {
      case P1 => P2
      case P1PROMO => P2
      case P2 => P1
      case P2PROMO => P1
      case IDLE => P1
      case _ => IDLE
    }
  }
}
