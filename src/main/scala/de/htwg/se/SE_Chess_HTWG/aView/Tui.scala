package de.htwg.se.SE_Chess_HTWG.aView

import de.htwg.se.SE_Chess_HTWG.controller.{CellChanged, ControllerInterface, GameStatus}
import de.htwg.se.SE_Chess_HTWG.util.ColumnMatcher

import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor {

  listenTo(controller)

  def processInputLine(input: String): Unit = {
    val command: String = input.split(" ")(0)

    command match {
      case "q" =>
      case "new" => controller.createNewGrid
      case "move" => {
        if (input.split(" ").length != 3) println("Not a valid command.") else {
          val fromRow: Int = input.split(" ")(1).substring(1, 2).toInt - 1
          val fromCol: Int = ColumnMatcher.matchLetterToCol(input.split(" ")(1).substring(0, 1))
          val toRow: Int = input.split(" ")(2).substring(1, 2).toInt - 1
          val toCol: Int = ColumnMatcher.matchLetterToCol(input.split(" ")(2).substring(0, 1))
          if (!controller.movePiece(fromRow, fromCol, toRow, toCol)) println("Not a valid move.")
        }
      }
      case _ => printTui
    }
  }

  reactions += {
    case event: CellChanged => printTui
  }

  def printTui: Unit = {
    println(controller.gridToString)
    println(GameStatus.message(controller.gameStatus))
  }
}
