package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.testUtil.TestGrid
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

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
@RunWith(classOf[JUnitRunner])
class QueenSpec extends WordSpec with Matchers {
  val testGrid: GridInterface = TestGrid.getTestGrid
  val row: Int = 6
  val col: Int = 1

  "A Queen on testgrid" when { "get available moves" should {
    val queen: PieceInterface = testGrid.getCell(row, col).value.get
    "contain all possible moves" in {
      Set(testGrid.getCell(0, 7), testGrid.getCell(1, 1), testGrid.getCell(1, 2),
        testGrid.getCell(1, 3), testGrid.getCell(1, 4), testGrid.getCell(1, 5),
        testGrid.getCell(1, 6), testGrid.getCell(2, 7), testGrid.getCell(3, 7),
        testGrid.getCell(4, 7), testGrid.getCell(5, 7), testGrid.getCell(6, 7),
        testGrid.getCell(7, 7)
      ).subsetOf(queen.getPossibleSquares(testGrid).toSet) should be(true)
    }
  }}
}
