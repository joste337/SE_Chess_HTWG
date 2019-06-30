package de.htwg.se.SE_Chess_HTWG.aView.gui

import de.htwg.se.SE_Chess_HTWG.controller.{CellChanged, ControllerInterface}
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.CellColor
import javax.swing.ImageIcon

import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event._

class CellPanel(row: Int, col: Int, controller: ControllerInterface) extends FlowPanel {
  val cellIsWhite = new Color(255,205,155)
  val cellIsBlack = new Color(220,140,70)
  val cellIsSelected = new Color(200,150,255)
  val piecesImgBasePath = "src/main/scala/de/htwg/se/SE_Chess_HTWG/aView/gui/pieceImgs/"

  def cellText(row: Int, col: Int) = controller.getSquare(row, col)


  val boardSize = new Dimension(600, 600)
  val squareSize = new Dimension(boardSize.width / 8, boardSize.height / 8)

  val label =
    new Label {
      if (controller.getSquare(row, col).isSet) icon = new ImageIcon(piecesImgBasePath + controller.getSquare(row, col).value.get.getImageName + ".png")
    }

  val cell = new BoxPanel(Orientation.Vertical) { //Horizontal) {
    contents += label
    background = if (controller.getSquare(row, col).color == CellColor.WHITE) cellIsWhite else cellIsBlack
    preferredSize = new Dimension(75, 75) // 600 framesize / 8 cells
    border = LineBorder(java.awt.Color.BLACK, 2)
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)
    listenTo(controller)
    reactions += {
      case e: CellChanged => {
        if (controller.getSquare(row, col).isSet) {
          label.icon = new ImageIcon(piecesImgBasePath + controller.getSquare(row, col).value.get.getImageName + ".png")
        } else {
          label.icon = null
        }
        repaint
      }
      case MouseClicked(src, pt, mod, clicks, pops) => {
        controller.selectSquare(row, col)
        repaint
      }
    }
  }
  contents += cell

  def redraw = {
    contents.clear()
    cell.background = if (controller.getSquare(row, col).color == CellColor.WHITE) cellIsWhite else cellIsBlack
    if (controller.getSquare(row, col).highlighhted) cell.background = cellIsSelected
    cell.contents += label
    contents += cell
    repaint
  }
}