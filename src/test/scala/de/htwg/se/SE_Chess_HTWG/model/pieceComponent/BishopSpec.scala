package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
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
    val bishop: PieceInterface = testGrid.getCell(row, col).value.get
    "contain all possible moves" in {
      Set(testGrid.getCell(7, 0), testGrid.getCell(5, 0), testGrid.getCell(7, 2),
        testGrid.getCell(5, 2), testGrid.getCell(4, 3), testGrid.getCell(3, 4),
        testGrid.getCell(2, 5), testGrid.getCell(1, 6), testGrid.getCell(0, 7)
      ).subsetOf(bishop.getPossibleSquares(testGrid).toSet) should be(true)
    }
  }}
}
