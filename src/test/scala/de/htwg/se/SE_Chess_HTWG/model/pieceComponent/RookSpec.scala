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
    val rook: Piece = testGrid.getSquare(row, col).value.get
    "contain all possible moves" in {
      Set(testGrid.getSquare(2, 0), testGrid.getSquare(2, 1), testGrid.getSquare(2, 3),
        testGrid.getSquare(2, 4), testGrid.getSquare(2, 5), testGrid.getSquare(2, 7),
        testGrid.getSquare(0, 6), testGrid.getSquare(1, 6), testGrid.getSquare(3, 6),
        testGrid.getSquare(4, 6), testGrid.getSquare(5, 6), testGrid.getSquare(6, 6),
        testGrid.getSquare(7, 6)
      ).subsetOf(rook.getPossibleMoves(testGrid, testGrid.turnStatus).toSet) should be(true)
    }
  }}
}
