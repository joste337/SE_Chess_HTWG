package de.htwg.se.SE_Chess_HTWG.testUtil

import com.google.inject.Guice
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridImpl, GridInterface, Matrix}
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent._

object TestGrid {

  /*  build a test grid with the form of:
      w b w b w b w b
      b B b w b w b w
      w b w N w b w b
      b w b w b w b w
      w b w b w K w b
      b w b w b w R w
      P b w b w b w Q
      b w b w b w b w
  */
  val injector = Guice.createInjector(new ChessModule)
  val grid = injector.getInstance(classOf[GridInterface])

  def getTestGrid: GridInterface = {
    var cells: Matrix = new Matrix
    for {
      row <- 0 until 8
      col <- 0 until 8
    } if ((row + col) % 2 != 0) cells = cells.replaceCell(row, col, Cell(None, true))
    grid.setCells(cells)

    grid.setCells(grid.replaceValue(1, 0, Some(Pawn(true, 1, 0, false))))
    grid.setCells(grid.replaceValue(1, 7, Some(Queen(true, 1, 7, true))))
    grid.setCells(grid.replaceValue(2, 6, Some(Rook(true, 2, 6, true))))
    grid.setCells(grid.replaceValue(3, 5, Some(King(true, 3, 5, true))))
    grid.setCells(grid.replaceValue(6, 1, Some(Bishop(true, 6, 1, true))))
    grid.setCells(grid.replaceValue(5, 3, Some(Knight(true, 5, 3, true))))

    grid
  }
}
