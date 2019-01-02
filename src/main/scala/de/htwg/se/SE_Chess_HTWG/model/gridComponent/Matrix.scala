package de.htwg.se.SE_Chess_HTWG.model.gridComponent

case class Matrix[T](rows: Vector[Vector[T]]) {
  def this(filling: T) = this(Vector.tabulate(8, 8) { (row, col) => filling })

  def cell(row: Int, col: Int): T = rows(row)(col)

  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(8, 8) { (row, col) => filling })

  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))
}
