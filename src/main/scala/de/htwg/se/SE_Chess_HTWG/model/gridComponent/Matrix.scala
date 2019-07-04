package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.CellColor.CellColor
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece


case class Matrix(rows: Vector[Vector[Square]]) {
  def this() = this(Vector.tabulate(8, 8) { (row, col) => Square(None, row, col, if ((row + col) % 2 != 0 ) CellColor.WHITE else CellColor.BLACK) })

  def getSquare(row: Int, col: Int): Square = rows(row)(col)

  private def replaceSquare(row: Int, col: Int, cell: Square): Matrix = copy(rows.updated(row, rows(row).updated(col, cell)))
  def replaceValue(row: Int, col: Int, piece: Option[Piece]): Matrix = replaceSquare(row, col, getSquare(row, col).replaceValue(piece))
  def replaceColor(row: Int, col: Int, color: CellColor): Matrix = replaceSquare(row, col, getSquare(row, col).replaceColor(color))

  def highlight(row: Int, col: Int): Matrix = copy(rows.updated(row, rows(row).updated(col, getSquare(row, col).highlight())))
  def unhighlightAll(): Matrix = copy(Vector.tabulate(8, 8) { (row, col) => getSquare(row, col).unHighlight() })

  def getSetCells: List[Square] = {
    for {
      row <- List.range(0, 8)
      col <- List.range(0, 8)
      if getSquare(row, col).isSet
    } yield getSquare(row, col)
  }
}
