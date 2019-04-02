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
      testGrid.matchColToPiece(0,0, true).col should be(0)
      testGrid.matchColToPiece(0,0, true).toSimpleString should be("R")
      testGrid.matchColToPiece(0,1, true).toSimpleString should be("N")
      testGrid.matchColToPiece(0,1, true).hasMoved should be(false)
      testGrid.matchColToPiece(0,2, true).toSimpleString should be("B")
      testGrid.matchColToPiece(0,2, true).isWhite should be(true)
      testGrid.matchColToPiece(0,3, true).toSimpleString should be("Q")
      testGrid.matchColToPiece(0,3, true).isWhite should be(true)
      testGrid.matchColToPiece(0,3, false).toSimpleString should be("K")
      testGrid.matchColToPiece(0,3, false).isWhite should be(false)
      testGrid.matchColToPiece(0,4, false).toSimpleString should be("Q")
      testGrid.matchColToPiece(0,4, true).toSimpleString should be("K")
      testGrid.matchColToPiece(0,5, true).toSimpleString should be("B")
      testGrid.matchColToPiece(0,5, true).isWhite should be(true)
      testGrid.matchColToPiece(0,6, true).toSimpleString should be("N")
      testGrid.matchColToPiece(0,6, true).hasMoved should be(false)
      testGrid.matchColToPiece(0,7, true).col should be(7)
      testGrid.matchColToPiece(0,7, true).toSimpleString should be("R")
    }
  }}

}
