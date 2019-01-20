package de.htwg.se.SE_Chess_HTWG.controller

import com.google.inject.{Guice, Inject}
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.controller.GameStatus._
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

import scala.swing.Publisher

class ControllerImpl @Inject() (var grid: GridInterface) extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new ChessModule)
  var clickedCell: Option[(Int, Int)] = None
  var gameStatus: GameStatus = IDLE
  var currentPlayerTurn: GameStatus = IDLE

  override def gridToString: String = grid.toString

  override def createNewGrid: Unit = {
    grid = grid.createNewGrid
    gameStatus = GameStatus.PLAYER1TURN
    currentPlayerTurn = GameStatus.PLAYER1TURN
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

  def cell(row:Int, col:Int) = grid.getCell(row, col)
}
