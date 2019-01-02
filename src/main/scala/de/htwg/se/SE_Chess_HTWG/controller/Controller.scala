package de.htwg.se.SE_Chess_HTWG.controller

import de.htwg.se.SE_Chess_HTWG.controller.GameStatus.GameStatus
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface

import scala.swing.Publisher

class Controller (var grid: GridInterface) extends ControllerInterface with Publisher {

  var gameStatus: GameStatus = GameStatus.IDLE
  override def gridToString: String = grid.toString

  override def createNewGrid: Unit = {
    grid = grid.createNewGrid
    gameStatus = GameStatus.PLAYER1TURN
    publish(new CellChanged)
  }

  override def movePiece(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    val fromCell = grid.getCell(fromRow, fromCol)
    gameStatus match {
      case GameStatus.PLAYER1TURN => if (!(fromCell.isSet && fromCell.value.get.isWhite)) return false
      case GameStatus.PLAYER2TURN => if (!(fromCell.isSet && !fromCell.value.get.isWhite)) return false
    }

    if (grid.movePiece(fromRow, fromCol, toRow, toCol)) {
      gameStatus = GameStatus.nextPlayer(gameStatus)
      publish(new CellChanged)
      true
    } else {
      println("move false")
      false
    }
  }
}
