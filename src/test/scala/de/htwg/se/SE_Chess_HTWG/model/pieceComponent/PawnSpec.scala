package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.testUtil.TestGrid
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PawnSpec extends WordSpec with Matchers {
  val testGrid: GridInterface = TestGrid.getTestGrid
  val row: Int = 1
  val col: Int = 0

  "A Pawn on testgrid" when { "get available moves" should {
    val pawn: Piece = testGrid.getCell(row, col).value.get
    "contain all possible moves" in {
      Set(testGrid.getCell(0, 0)
      ).subsetOf(pawn.getPossibleMoves(testGrid, testGrid.turnStatus).toSet) should be(true)
    }
  }}
}
