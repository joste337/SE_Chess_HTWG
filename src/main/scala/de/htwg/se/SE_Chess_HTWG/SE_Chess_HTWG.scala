package de.htwg.se.SE_Chess_HTWG

import com.google.inject.Guice
import de.htwg.se.SE_Chess_HTWG.controller.ControllerInterface
import de.htwg.se.SE_Chess_HTWG.aView.tui.Tui
import de.htwg.se.SE_Chess_HTWG.aView.web.WebView

object SE_Chess_HTWG {
  val injector = Guice.createInjector(new ChessModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  //val gui = new SwingGui(controller)
  val server = new WebView(controller)

  controller.createNewGrid

  def main(args: Array[String]): Unit = {
    var input: String = ""

    do {
      input = readLine()
//      tui.processInputLine(input)
    } while (input != "q")
  }
}
