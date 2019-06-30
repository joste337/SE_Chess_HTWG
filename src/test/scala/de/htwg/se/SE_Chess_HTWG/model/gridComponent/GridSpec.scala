package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import com.google.inject.Guice
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent._
import de.htwg.se.SE_Chess_HTWG.testUtil.TestGrid
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
      testGrid.getPieceForColumn(0,0, PieceColor.WHITE).square.col should be(0)
      testGrid.getPieceForColumn(0,0, PieceColor.WHITE).toShortcut should be("R")
      testGrid.getPieceForColumn(0,1, PieceColor.WHITE).toShortcut should be("N")
      testGrid.getPieceForColumn(0,1, PieceColor.WHITE).hasMoved should be(false)
      testGrid.getPieceForColumn(0,2, PieceColor.WHITE).toShortcut should be("B")
      testGrid.getPieceForColumn(0,2, PieceColor.WHITE).isWhite should be(true)
      testGrid.getPieceForColumn(0,3, PieceColor.WHITE).toShortcut should be("Q")
      testGrid.getPieceForColumn(0,3, PieceColor.WHITE).isWhite should be(true)
      testGrid.getPieceForColumn(0,3, PieceColor.BLACK).toShortcut should be("K")
      testGrid.getPieceForColumn(0,3, PieceColor.BLACK).isWhite should be(false)
      testGrid.getPieceForColumn(0,4, PieceColor.BLACK).toShortcut should be("Q")
      testGrid.getPieceForColumn(0,4, PieceColor.WHITE).toShortcut should be("K")
      testGrid.getPieceForColumn(0,5, PieceColor.WHITE).toShortcut should be("B")
      testGrid.getPieceForColumn(0,5, PieceColor.WHITE).isWhite should be(true)
      testGrid.getPieceForColumn(0,6, PieceColor.WHITE).toShortcut should be("N")
      testGrid.getPieceForColumn(0,6, PieceColor.WHITE).hasMoved should be(false)
      testGrid.getPieceForColumn(0,7, PieceColor.WHITE).square.col should be(7)
      testGrid.getPieceForColumn(0,7, PieceColor.WHITE).toShortcut should be("R")
    }
  }}
}
