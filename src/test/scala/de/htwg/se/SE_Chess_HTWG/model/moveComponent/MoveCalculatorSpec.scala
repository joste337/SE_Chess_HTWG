package de.htwg.se.SE_Chess_HTWG.model.moveComponent

import com.google.inject.Guice
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridImpl, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceFactory
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MoveCalculatorSpec extends WordSpec with Matchers {

  val injector = Guice.createInjector(new ChessModule)
  val grid: GridInterface = injector.getInstance(classOf[GridInterface])
  val moveCalculator = new MoveCalculator

  "Grid" when { "matchColToPiece" should {
    "return the right piece" in {
      println(moveCalculator.calculateMoves(grid.createNewGrid))
    }
  }}
}
