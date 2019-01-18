package de.htwg.se.SE_Chess_HTWG.model.gridComponent

case class Matrix(rows: Vector[Vector[Cell]]) {
  def this() = this(Vector.tabulate(8, 8) { (row, col) => Cell(None, false) })

  def cell(row: Int, col: Int): Cell = rows(row)(col)

  def fill(filling: Cell): Matrix = copy(Vector.tabulate(8, 8) { (row, col) => filling })

  def replaceCell(row: Int, col: Int, cell: Cell): Matrix = copy(rows.updated(row, rows(row).updated(col, cell)))
}
