package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.testUtil.TestGrid
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class KingSpec extends WordSpec with Matchers {
  val testGrid: GridInterface = TestGrid.getTestGrid
  val row: Int = 3
  val col: Int = 5

  "A King on testgrid" when { "get available moves" should {
    val king: Piece = testGrid.getSquare(row, col).value.get
    "contain all possible moves" in {
      Set(testGrid.getSquare(4, 4), testGrid.getSquare(4, 6), testGrid.getSquare(4, 5),
        testGrid.getSquare(2, 4), testGrid.getSquare(2, 5), testGrid.getSquare(3, 4),
        testGrid.getSquare(3, 6)
      ).subsetOf(king.getPossibleMoves(testGrid, testGrid.turnStatus).toSet) should be(true)
    }
  }}
}
