package de.htwg.se.SE_Chess_HTWG.controller

import com.google.inject.{Guice, Inject}
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.controller.GameStatus._
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.fileIOComponent.FileIOInterface
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

import scala.swing.Publisher

class ControllerImpl @Inject() (var grid: GridInterface) extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new ChessModule)
  var clickedCell: Option[(Int, Int)] = None
  val fileIo: FileIOInterface = injector.getInstance(classOf[FileIOInterface])
  val undoManager: UndoManager = new UndoManagerImpl(grid)
  var gameStatus: GameStatus = IDLE

  override def gridToString: String = grid.toString

  override def createNewGrid: Unit = {
    grid = grid.createNewGridWithPieces
    gameStatus = PLAYER1TURN
    publish(new CellChanged)
  }

  override def movePiece(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): MovementResult = {
    val move: Move = new Move(grid, fromRow, fromCol, toRow, toCol)

    val movementResult: MovementResult =
      if (move.isInGrid && pieceColorMatchesTurnColor(fromRow, fromCol)) {
        undoManager.undoStack =
          ((fromRow, fromCol, grid.getCell(fromRow, fromCol).value), (toRow, toCol, grid.getCell(toRow, toCol).value))::undoManager.undoStack
        grid.movePiece(move)
      } else {
        MovementResult.ERROR
      }

    gameStatus = movementResult match {
      case MovementResult.SUCCESS => GameStatus.nextPlayer(gameStatus)
      case MovementResult.PROMOTION => GameStatus.getPromotion(gameStatus)
      case MovementResult.BLACKKINGTAKEN => IDLE
      case MovementResult.WHITEKINGTAKEN => IDLE
      case _ => gameStatus
    }

    publish(new CellChanged)
    movementResult
  }

  def promotePiece(row: Int, col: Int, pieceShortcut: String): MovementResult = {
    val movementResult: MovementResult =
      if (gameStatus == PROMOTIONPLAYER1 || gameStatus == PROMOTIONPLAYER2) {
        grid.promotePiece(row, col, pieceShortcut)
      } else  {
        MovementResult.ERROR
      }

    movementResult match {
      case MovementResult.SUCCESS => gameStatus = GameStatus.nextPlayer(gameStatus)
      case _ =>
    }

    publish(new CellChanged)
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

  def undo: Unit = {
    undoManager.undoMove
    publish(new CellChanged)
  }

  def redo: Unit = {
    undoManager.redoMove
    publish(new CellChanged)
  }

  def save: Unit = fileIo.save(grid, gameStatus)

  def load: Unit = {
    val loadResult = fileIo.load
    grid = loadResult._1
    gameStatus = loadResult._2
    publish(new CellChanged)
  }
}
