package de.htwg.se.SE_Chess_HTWG.aView.gui

import java.io.PrintWriter

import scala.swing.{Color, _}
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.SE_Chess_HTWG.controller.{CellChanged, ControllerInterface, GameStatus}
import de.htwg.se.SE_Chess_HTWG.model.gridComponent._
import java.awt.Color

import javax.swing.JFrame

import scala.io.Source

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui (controller: ControllerInterface) extends Frame with Reactor{

  peer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  listenTo(controller)
  title = "SE Chess HTWG"

  menuBar = new MenuBar {
    contents += new Menu("File    ") {
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