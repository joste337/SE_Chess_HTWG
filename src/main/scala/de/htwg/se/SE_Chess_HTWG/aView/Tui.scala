package de.htwg.se.SE_Chess_HTWG.aView

import de.htwg.se.SE_Chess_HTWG.controller.{CellChanged, ControllerInterface, GameStatus}
import de.htwg.se.SE_Chess_HTWG.util.{ColumnMatcher, MovementResult}

import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor {

  listenTo(controller)

  def processInputLine(input: String): Unit = {
    val command: String = input.split(" ")(0)

    command match {
      case "q" =>
      case "new" => controller.createNewGrid
      case "promote" => {
        if (input.split(" ").length != 3) println("Not a valid command.") else {
          val rowsAndCells = getRowAndCellFromPromotionInput(input)
          println(MovementResult.message(controller.promotePiece(rowsAndCells._1, rowsAndCells._2, rowsAndCells._3)))
        }
      }
      case "move" => {
        if (input.split(" ").length != 3) println("Not a valid command.") else {
          val rowsAndCells = getRowsAndCellsFromMoveInput(input)
          println(MovementResult.message(controller.movePiece(rowsAndCells._1, rowsAndCells._2, rowsAndCells._3, rowsAndCells._4)))
        }
      }
      case "save" => {
        controller.save
        println("Saved current game.")
      }
      case "load" => {
        controller.load
        println("Loaded saved game.")
      }
      case "undo" => controller.undo
      case "redo" => controller.redo
      case _ => printTui
    }
  }

  def getRowsAndCellsFromMoveInput(input: String): (Int, Int, Int, Int) = {
    val fromRow: Int = input.split(" ")(1).substring(1, 2).toInt - 1
    val fromCol: Int = ColumnMatcher.matchLetterToCol(input.split(" ")(1).substring(0, 1))
    val toRow: Int = input.split(" ")(2).substring(1, 2).toInt - 1
    val toCol: Int = ColumnMatcher.matchLetterToCol(input.split(" ")(2).substring(0, 1))
    (fromRow, fromCol, toRow, toCol)
  }

  def getRowAndCellFromPromotionInput(input: String): (Int, Int, String) = {
    val row: Int = input.split(" ")(1).substring(1, 2).toInt - 1
    val col: Int = ColumnMatcher.matchLetterToCol(input.split(" ")(1).substring(0, 1))
    val pieceShortcut: String = input.split(" ")(2)
    (row, col, pieceShortcut)
  }

  reactions += {
    case event: CellChanged => printTui
  }

  def printTui: Unit = {
    println(controller.gridToString)
    println(GameStatus.message(controller.gameStatus))
  }
}
