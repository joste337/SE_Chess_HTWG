package de.htwg.se.SE_Chess_HTWG.controller

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, PLAYER1TURN, PLAYER2TURN = Value

  val gameStatusToString = Map[GameStatus, String](
    IDLE -> "",
    PLAYER1TURN -> "First(white) player's turn",
    PLAYER2TURN -> "Second(black) player's turn"
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
}
