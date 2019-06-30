package de.htwg.se.SE_Chess_HTWG.aView.gui

import scala.swing. _
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.SE_Chess_HTWG.controller.{CellChanged, ControllerInterface}
import java.awt.Color

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui (controller: ControllerInterface) extends Frame with Reactor{

  //peer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  listenTo(controller)
  title = "SE Chess HTWG"

  menuBar = new MenuBar {
    contents += new Menu("File    ") {
      contents += new MenuItem(Action("New") { newGame() })
      contents += new Separator()
      contents += new MenuItem(Action("Undo") { undo() })
      contents += new MenuItem(Action("Redo") { redo() })
      contents += new Separator()
      contents += new MenuItem(Action("Load") { openFile() })
      contents += new MenuItem(Action("Save") { saveFile() })
      contents += new Separator()
      contents += new MenuItem(Action("Exit") { sys.exit(0) })
    }
    visible = true
  }

  var cells = Array.ofDim[CellPanel](8, 8)

  val textArea = new TextArea()  //for testing gridtostring

  val cellIsWhite = new Color(255,205,155)
  val cellIsBlack = new Color(220,140,70)
  val cellIsSelected = new Color(200,150,255)

  def gridPanel = new GridPanel(8,8) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = java.awt.Color.BLACK

    for {
      x <- (0 until 8).reverse
      y <- 0 until 8
    } {
      val cellPanel = new CellPanel(x, y, controller)
      cells(x)(y) = cellPanel
      contents += cellPanel
      listenTo(cellPanel)
    }
  }

  contents = new BorderPanel {
    add(gridPanel, BorderPanel.Position.Center)
  }

  size = new Dimension(600, 600)
  centerOnScreen()
  this.visible = true

  def newGame(): Unit = controller.createNewGrid

  def undo(): Unit = controller.undo()
  def redo(): Unit = controller.redo()

  def openFile(): Unit =  controller.load()
  def saveFile(): Unit = controller.save()

  def redraw: Unit = {
    for {
      x <- (0 until 8).reverse
      y <- 0 until 8
    } {
      cells(x)(y).redraw
    }
    repaint
  }

  reactions += {
    case event: CellChanged => redraw
  }
}