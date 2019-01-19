package de.htwg.se.SE_Chess_HTWG.aView.gui

import java.io.PrintWriter
import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.SE_Chess_HTWG.controller.ControllerInterface
import de.htwg.se.SE_Chess_HTWG.model.gridComponent._
import java.awt.Color
import scala.io.Source

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui (controller: ControllerInterface) extends Frame  {

  listenTo(controller)
  title = "SE Chess HTWG"

  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("New") {
        newGame
      })
      contents += new MenuItem(Action("Open") {
        openFile
      })
      contents += new MenuItem(Action("Save") {
        saveFile
      })
      contents += new Separator()
      contents += new MenuItem(Action("Exit") {
        sys.exit(0)
      })
    }
    visible = true
  }

  val textArea = new TextArea()  //for testing gridtostring

  contents = new GridPanel(8,8) {
    contents += new TextField(controller.gridToString)
  }

    val player: (String, String) = ("Player 1", "Player 2")
    var cells = Array.ofDim[CellPanel](8, 8)







  size = new Dimension(600, 600)
  centerOnScreen()
  this.visible = true



  def newGame: Unit = {
    controller.createNewGrid
  }

  def openFile {
    val chooser = new FileChooser()
    if (chooser.showOpenDialog(null) == FileChooser.Result.Approve) {
      val source = Source.fromFile(chooser.selectedFile)
      textArea.text = source.mkString
      source.close()
    }
  }

  def saveFile: Unit = {
    val chooser = new FileChooser()
    if (chooser.showSaveDialog(null) == FileChooser.Result.Approve) {
      val printwriter = new PrintWriter(chooser.selectedFile)
      printwriter.print(textArea.text)
      printwriter.close()
    }
  }



}