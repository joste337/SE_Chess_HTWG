package de.htwg.se.SE_Chess_HTWG.controller

import de.htwg.se.SE_Chess_HTWG.controller.GameStatus.GameStatus
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

import scala.swing.Publisher

class Controller (var grid: GridInterface) extends ControllerInterface with Publisher {

  var gameStatus: GameStatus = GameStatus.IDLE
  var currentPlayerTurn: GameStatus = GameStatus.IDLE
  override def gridToString: String = grid.toString

  override def createNewGrid: Unit = {
    grid = grid.createNewGrid
    gameStatus = GameStatus.PLAYER1TURN
    publish(new CellChanged)
  }

  override def movePiece(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): MovementResult = {
    val move: Move = new Move(grid, fromRow, fromCol, toRow, toCol)

    val movementResult: MovementResult =
      if (move.isInGrid && pieceColorMatchesTurnColor(fromRow, fromCol)) {
        grid.movePiece(move)
      } else {
        MovementResult.ERROR
      }

    movementResult match {
      case MovementResult.SUCCESS => {
        gameStatus = GameStatus.nextPlayer(currentPlayerTurn)
        currentPlayerTurn = gameStatus
        publish(new CellChanged)
      }
      case MovementResult.PROMOTION => {
        gameStatus = GameStatus.PROMOTION
        publish(new CellChanged)
      }
      case _ =>
    }
    movementResult
  }

  def promotePiece(row: Int, col: Int, pieceShortcut: String): MovementResult = {
    val movementResult: MovementResult =
      if (gameStatus == GameStatus.PROMOTION) {
        grid.promotePiece(row, col, pieceShortcut)
      } else  {
        MovementResult.ERROR
      }

    movementResult match {
      case MovementResult.SUCCESS => {
        gameStatus = GameStatus.nextPlayer(currentPlayerTurn)
        currentPlayerTurn = gameStatus
        publish(new CellChanged)
      }
      case _ =>
    }
    movementResult
  }

  def pieceColorMatchesTurnColor(fromRow: Int, fromCol: Int): Boolean = {
    val fromCell = grid.getCell(fromRow, fromCol)
    gameStatus match {
      case GameStatus.PLAYER1TURN => if (fromCell.isSet && fromCell.value.get.isWhite) true else false
      case GameStatus.PLAYER2TURN => if (fromCell.isSet && !fromCell.value.get.isWhite) true else false
      case _ => false
    }
  }
}
