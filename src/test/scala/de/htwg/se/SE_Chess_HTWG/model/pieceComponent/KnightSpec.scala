package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.testUtil.TestGrid
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class KnightSpec extends WordSpec with Matchers {
  val testGrid: GridInterface = TestGrid.getTestGrid
  val row: Int = 5
  val col: Int = 3

  "A Knight on testgrid" when { "get available moves" should {
    val knight: Piece = testGrid.getSquare(row, col).value.get
    println("AAAAAAAAAAAA " + knight.getPossibleMoves(testGrid, testGrid.turnStatus).foreach(cell => println(cell.row + " , " + cell.col)))
    "contain all possible moves" in {
      Set(testGrid.getSquare(7, 4), testGrid.getSquare(7, 2), testGrid.getSquare(3, 4),
        testGrid.getSquare(3, 2), testGrid.getSquare(6, 5), testGrid.getSquare(4, 5),
        testGrid.getSquare(4, 1)
      ).subsetOf(knight.getPossibleMoves(testGrid, testGrid.turnStatus).toSet) should be(true)
    }
  }}
}
