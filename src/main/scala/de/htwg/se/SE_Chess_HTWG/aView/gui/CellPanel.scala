package de.htwg.se.SE_Chess_HTWG.aView.gui

import de.htwg.se.SE_Chess_HTWG.controller.{CellChanged, ControllerInterface}
import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event._

class CellPanel(row: Int, col: Int, controller: ControllerInterface) extends FlowPanel {
  //RGB Black 220, 140, 70; White 255, 205, 155; Pink = 200,150,255
  val cellIsWhite = new Color(255,205,155)
  val cellIsBlack = new Color(220,140,70)
  val cellIsSelected = new Color(200,150,255)

  def myCell = controller.cell(row, col)
  def cellText(row: Int, col: Int) = controller.grid.getCell(row, col)


  val boardSize = new Dimension(600, 600)
  val squareSize = new Dimension(boardSize.width / 8, boardSize.height / 8)

  val label =
    new Label {
      text = controller.cell(row,col).toString
      //font = new Font("Verdana", 1, 36)
    }
  val cell = new BoxPanel(Orientation.Vertical) { //Horizontal) {
    contents += label
    preferredSize = new Dimension(75, 75) // 600 framesize / 8 cells
    border = LineBorder(java.awt.Color.BLACK, 2)
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)
    listenTo(controller)
    reactions += {
      case e: CellChanged => {
        label.text = controller.cell(row,col).toString
        repaint
      }
      case MouseClicked(src, pt, mod, clicks, pops) => {
        //geklicktes feld: welche figur, mögliche züge. ziehen.
        controller.movePiece(row,col,row,col)
        repaint
      }
    }
  }
  contents += cell



  def redraw = {
    contents.clear()
    label.text = controller.cell(row,col).toString
    contents += cell
    repaint
  }


}