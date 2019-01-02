package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.pieceComponent._
import de.htwg.se.SE_Chess_HTWG.util.ColumnMatcher._

import scala.math.abs


class GridBaseImpl(var cells: Matrix[Cell]) extends GridInterface {
  val BOARD_SIZE: Int = 8
  val mover = new Mover(this)
  def this() = this(new Matrix[Cell](Cell(None, false)))

  def getCell(row: Int, col: Int): Cell = cells.cell(row, col)
  def setCells(cells: Matrix[Cell]): Unit = this.cells = cells
  def replaceColor(row: Int, col: Int, isWhite: Boolean): Matrix[Cell] = cells.replaceCell(row, col, Cell(getCell(row, col).value, isWhite))
  def replaceValue(row: Int, col: Int, value: Option[Piece]): Matrix[Cell] = cells.replaceCell(row, col, Cell(value, getCell(row, col).isWhite))

  override def movePiece(fromRow: Int, fromCol: Int, toRow: Int, toCol: Int): Boolean = {
    mover.movePiece(fromRow, fromCol, toRow, toCol)
  }

  // "Schöner TUI output für das grid erzeugen
  override def toString: String = {
    val upperBorder = ("+--" + "--" * BOARD_SIZE) + "-+\n"
    val fillerLine = "|  " + "  " * BOARD_SIZE + " |\n"
    val lowerBorder = ("+--" + "/-" * BOARD_SIZE) + "-+\n"
    val line = ("_  " + ("x " * BOARD_SIZE)) + " |\n"
    var box = "\n" + (upperBorder + fillerLine + (line * BOARD_SIZE)) + fillerLine + lowerBorder

    for {
      row <- (0 until BOARD_SIZE).reverse
      col <- 0 until BOARD_SIZE
    } box = box.replaceFirst("x", getCell(row, col).toString).replaceFirst("_", abs(col - 8).toString).replaceFirst("/", matchColToLetter(col))
    box
  }

  override def createNewGrid: GridInterface = {
    // Figuren in das grid einfügen
    for (col <- 0 until BOARD_SIZE) {
      // weiße Figuren
      setCells(replaceValue(1, col, Some(new Pawn(true))))
      cells = replaceValue(0, col, Some(matchColToPiece(col, true)))
      // schwarze Figuren
      cells = replaceValue(6, col, Some(new Pawn(false)))
      cells = replaceValue(7, col, Some(matchColToPiece(col, false)))
    }
    // Felder abwechselns auf schwarz/weiß setzen
    for {
      row <- 0 until BOARD_SIZE
      col <- 0 until BOARD_SIZE
    } if ((row + col) % 2 != 0) cells = replaceColor(row, col, true)

    this
  }
}
