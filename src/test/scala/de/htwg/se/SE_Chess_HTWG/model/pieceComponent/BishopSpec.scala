package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.testUtil.TestGrid
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class BishopSpec extends WordSpec with Matchers {
  val testGrid: GridInterface = TestGrid.getTestGrid
  val row: Int = 6
  val col: Int = 1

  "A Bishop on testgrid" when { "get available moves" should {
    val bishop: Piece = testGrid.getSquare(row, col).value.get
    "contain all possible moves" in {
      Set(testGrid.getSquare(7, 0), testGrid.getSquare(5, 0), testGrid.getSquare(7, 2),
        testGrid.getSquare(5, 2), testGrid.getSquare(4, 3), testGrid.getSquare(3, 4),
        testGrid.getSquare(2, 5), testGrid.getSquare(1, 6), testGrid.getSquare(0, 7)
      ).subsetOf(bishop.getPossibleMoves(testGrid, testGrid.turnStatus).toSet) should be(true)
    }
  }}
}
