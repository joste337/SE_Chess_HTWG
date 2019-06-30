package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import com.google.inject.Guice
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.{PieceColor, PieceFactory, PieceType, Rook}
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CellSpec extends WordSpec with Matchers {
  val injector = Guice.createInjector(new ChessModule)
  val pieceFactory: PieceFactory = injector.getInstance(classOf[PieceFactory])

  "A Cell" when { "new with white and no piece" should {
    val cell = Square(None, 1, 1, CellColor.WHITE).highlight()
    "not be set" in {
      cell.isSet should be(false)
    }
    "be highlighted"  in {
      cell.highlighhted should be(true)
    }
    "have the right string representation of the square" in {
      cell.toString should be("\u25af")
    }
  }}

  "A Cell" when { "new with black and no piece" should {
    val cell = Square(None, 1, 1, CellColor.BLACK)
    "not be set" in {
      cell.isSet should be(false)
    }
    "not be highlighted"  in {
      cell.highlighhted should be(false)
    }
    "have the right string representation of the square" in {
      cell.toString should be("\u25ae")
    }
  }}

  "A Cell" when { "new with black and a rook" should {
    var cell = Square(None, 1, 1, CellColor.BLACK)
    cell = cell.replaceValue(Some(pieceFactory.getPiece(PieceType.ROOK, PieceColor.BLACK, cell)))
    "be set" in {
      cell.isSet should be(true)
    }
    "have the right string representation of the piece" in {
      cell.toString should be("\u265C")
    }
  }}
}
