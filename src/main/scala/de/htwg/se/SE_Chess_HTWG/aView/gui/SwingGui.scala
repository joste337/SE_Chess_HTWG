package de.htwg.se.SE_Chess_HTWG.aView.gui

import java.io.PrintWriter
import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.SE_Chess_HTWG.controller.{ControllerImpl, ControllerInterface}
import de.htwg.se.SE_Chess_HTWG.model.gridComponent._
import java.awt.Color
import scala.io.Source


class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui (controller: ControllerInterface) extends Frame {
  listenTo(controller)

  title = "SE Chess HTWG"

  var cells = Array.ofDim[CellPanel](8,8)

  def gridPanel: GridPanel = new GridPanel(8, 8) {
    border = LineBorder(Color.RED, 3)
    for {
      outerRow <- 0 until 8
      outerColumn <- 0 until 8
    } {
      val row = outerRow
      val col = outerColumn
      val cellPanel = new CellPanel(row,col,controller)
      cells(row)(col) = cellPanel
      contents += cellPanel
    }

  }

  contents = new TextArea
  menuBar = new MenuBar {
    contents += new Menu("File") {
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
  }
  contents = Button("New Game")(println("A new game has been started!"))
  size = new Dimension (800,600)
  centerOnScreen()
  this.visible = true

  val textArea = new TextArea()

  def openFile {
    val chooser = new FileChooser()
    if(chooser.showOpenDialog(null)==FileChooser.Result.Approve) {
      val source = Source.fromFile(chooser.selectedFile)
      textArea.text = source.mkString
      source.close()
    }
  }

  def saveFile: Unit = {
    val chooser = new FileChooser()
    if(chooser.showSaveDialog(null)==FileChooser.Result.Approve) {
      val printwriter = new PrintWriter(chooser.selectedFile)
      printwriter.print(textArea.text)
      printwriter.close()
    }
  }
}

