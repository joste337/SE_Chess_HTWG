package de.htwg.se.SE_Chess_HTWG

import com.google.inject.Guice
import de.htwg.se.SE_Chess_HTWG.aView.Tui
import de.htwg.se.SE_Chess_HTWG.controller.ControllerInterface

object SE_Chess_HTWG {
  val injector = Guice.createInjector(new ChessModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)


  def main(args: Array[String]): Unit = {
    var input: String = ""

    do {
      input = readLine() //is deprecated
      tui.processInputLine(input)
    } while (input != "q")
  }
}
