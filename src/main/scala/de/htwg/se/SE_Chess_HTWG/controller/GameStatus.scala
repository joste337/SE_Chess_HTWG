package de.htwg.se.SE_Chess_HTWG.controller

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, PLAYER1TURN, PLAYER2TURN, PROMOTION = Value

  val gameStatusToString = Map[GameStatus, String](
    IDLE -> "",
    PLAYER1TURN -> "First player's turn",
    PLAYER2TURN -> "Second player's turn",
    PROMOTION -> "Promotion needs to be done"
  )

  def message(gameStatus: GameStatus): String = {
    gameStatusToString(gameStatus)
  }

  def nextPlayer(gameStatus: GameStatus): GameStatus = {
    gameStatus match {
      case PLAYER1TURN => PLAYER2TURN
      case PLAYER2TURN => PLAYER1TURN
      case _ => IDLE
    }
  }

  def toOutputString(gameStatus: GameStatus): String = {
    gameStatus match {
      case PLAYER1TURN => "p1"
      case PLAYER2TURN => "p2"
      case IDLE => "idle"
      case PROMOTION => "promo"
    }
  }

  def fromInputString(gameStatus: String): GameStatus = {
    gameStatus match {
      case "p1" => PLAYER1TURN
      case "p2" => PLAYER2TURN
      case "idle" => IDLE
      case "promo" => PROMOTION
      //case _ => IDLE
    }
  }
}
