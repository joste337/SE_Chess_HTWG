package de.htwg.se.SE_Chess_HTWG

import de.htwg.se.SE_Chess_HTWG.aView.Tui
import de.htwg.se.SE_Chess_HTWG.controller.Controller
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridBaseImpl

import scala.io.StdIn

object SE_Chess_HTWG {
  val controller = new Controller(new GridBaseImpl)
  val tui = new Tui(controller)


  def main(args: Array[String]): Unit = {
    var input: String = ""

    do {
      input = StdIn() //readLine() is deprecated
      tui.processInputLine(input)
    } while (input != "q")
  }
}
