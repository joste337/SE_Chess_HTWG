package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.testUtil.TestGrid
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RookSpec extends WordSpec with Matchers {
  val testGrid: GridInterface = TestGrid.getTestGrid
  val row: Int = 2
  val col: Int = 6

  "A Rook on testgrid" when { "get available moves" should {
    val rook: Piece = testGrid.getCell(row, col).value.get
    "contain all possible moves" in {
      Set(testGrid.getCell(2, 0), testGrid.getCell(2, 1), testGrid.getCell(2, 3),
        testGrid.getCell(2, 4), testGrid.getCell(2, 5), testGrid.getCell(2, 7),
        testGrid.getCell(0, 6), testGrid.getCell(1, 6), testGrid.getCell(3, 6),
        testGrid.getCell(4, 6), testGrid.getCell(5, 6), testGrid.getCell(6, 6),
        testGrid.getCell(7, 6)
      ).subsetOf(rook.getPossibleMoves(testGrid, testGrid.turnStatus).toSet) should be(true)
    }
  }}
}
