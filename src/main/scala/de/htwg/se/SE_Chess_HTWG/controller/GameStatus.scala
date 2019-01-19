package de.htwg.se.SE_Chess_HTWG.controller

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, PLAYER1TURN, PLAYER2TURN, PROMOTIONPLAYER1, PROMOTIONPLAYER2 = Value

  val gameStatusToString = Map[GameStatus, String](
    IDLE -> "",
    PLAYER1TURN -> "First player's turn",
    PLAYER2TURN -> "Second player's turn",
    PROMOTIONPLAYER1 -> "Promotion for player 1 can be done.",
    PROMOTIONPLAYER2 -> "Promotion for player 2 can be done."
  )

  def message(gameStatus: GameStatus): String = {
    gameStatusToString(gameStatus)
  }

  def nextPlayer(gameStatus: GameStatus): GameStatus = {
    gameStatus match {
      case PLAYER1TURN => PLAYER2TURN
      case PLAYER2TURN => PLAYER1TURN
      case PROMOTIONPLAYER1 => PLAYER2TURN
      case PROMOTIONPLAYER2 => PLAYER1TURN
      case _ => IDLE
    }
  }

  def toOutputString(gameStatus: GameStatus): String = {
    gameStatus match {
      case PLAYER1TURN => "p1"
      case PLAYER2TURN => "p2"
      case PROMOTIONPLAYER1 => "p1prom"
      case PROMOTIONPLAYER2 => "p2prom"
      case IDLE => "idle"
    }
  }

  def fromInputString(gameStatus: String): GameStatus = {
    gameStatus match {
      case "p1" => PLAYER1TURN
      case "p2" => PLAYER2TURN
      case "p1prom" => PROMOTIONPLAYER1
      case "p2prom" => PROMOTIONPLAYER2
      case "idle" => IDLE
    }
  }

  def getPromotion(gameStatus: GameStatus): GameStatus = {
    gameStatus match {
      case PLAYER1TURN => PROMOTIONPLAYER1
      case PLAYER2TURN => PROMOTIONPLAYER2
      case _ => IDLE
    }
  }
}
