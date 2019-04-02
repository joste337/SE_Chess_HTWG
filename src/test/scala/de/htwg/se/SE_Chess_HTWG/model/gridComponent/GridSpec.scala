package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import com.google.inject.Guice
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.{King, PieceType, PieceFactory, Piece}
import de.htwg.se.SE_Chess_HTWG.testUtil.TestGrid
import de.htwg.se.SE_Chess_HTWG.util.ColumnMatcher
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GridSpec extends WordSpec with Matchers {

  val injector = Guice.createInjector(new ChessModule)
  val pieceFactory: PieceFactory = injector.getInstance(classOf[PieceFactory])
  val testGrid: GridInterface = TestGrid.getTestGrid

  "Grid" when { "matchColToPiece" should {
    "return the right piece" in {
      testGrid.getPieceForColumn(0,0, true).col should be(0)
      testGrid.getPieceForColumn(0,0, true).toShortcut should be("R")
      testGrid.getPieceForColumn(0,1, true).toShortcut should be("N")
      testGrid.getPieceForColumn(0,1, true).hasMoved should be(false)
      testGrid.getPieceForColumn(0,2, true).toShortcut should be("B")
      testGrid.getPieceForColumn(0,2, true).isWhite should be(true)
      testGrid.getPieceForColumn(0,3, true).toShortcut should be("Q")
      testGrid.getPieceForColumn(0,3, true).isWhite should be(true)
      testGrid.getPieceForColumn(0,3, false).toShortcut should be("K")
      testGrid.getPieceForColumn(0,3, false).isWhite should be(false)
      testGrid.getPieceForColumn(0,4, false).toShortcut should be("Q")
      testGrid.getPieceForColumn(0,4, true).toShortcut should be("K")
      testGrid.getPieceForColumn(0,5, true).toShortcut should be("B")
      testGrid.getPieceForColumn(0,5, true).isWhite should be(true)
      testGrid.getPieceForColumn(0,6, true).toShortcut should be("N")
      testGrid.getPieceForColumn(0,6, true).hasMoved should be(false)
      testGrid.getPieceForColumn(0,7, true).col should be(7)
      testGrid.getPieceForColumn(0,7, true).toShortcut should be("R")
    }
  }}

}
