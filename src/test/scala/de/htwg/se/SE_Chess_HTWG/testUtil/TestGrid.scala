package de.htwg.se.SE_Chess_HTWG.testUtil

import com.google.inject.Guice
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
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
  val pieceFactory: PieceFactory = injector.getInstance(classOf[PieceFactory])

  def getTestGrid: GridInterface = {
    grid.replacePiece(1, 0, Some(pieceFactory.getPiece(PieceType.PAWN, PieceColor.BLACK, grid.getCell(1, 0))))
      .replacePiece(1, 7, Some(pieceFactory.getPiece(PieceType.QUEEN,PieceColor.BLACK, grid.getCell(1, 7))))
      .replacePiece(2, 6, Some(pieceFactory.getPiece(PieceType.ROOK, PieceColor.BLACK, grid.getCell(2, 6))))
      .replacePiece(3, 5, Some(pieceFactory.getPiece(PieceType.KING, PieceColor.BLACK, grid.getCell(3, 5))))
      .replacePiece(6, 1, Some(pieceFactory.getPiece(PieceType.BISHOP, PieceColor.BLACK, grid.getCell(6, 1))))
      .replacePiece(5, 3, Some(pieceFactory.getPiece(PieceType.KNIGHT, PieceColor.BLACK, grid.getCell(5, 3))))
  }
}
