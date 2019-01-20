package de.htwg.se.SE_Chess_HTWG.util

import de.htwg.se.SE_Chess_HTWG.model.pieceComponent._
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ColumnMatcherSpec extends WordSpec with Matchers {
  "ColumnMatcher" when { "matchLetterToCol" should {
    "return the right col" in {
      ColumnMatcher.matchLetterToCol("a") should be(0)
      ColumnMatcher.matchLetterToCol("b") should be(1)
      ColumnMatcher.matchLetterToCol("c") should be(2)
      ColumnMatcher.matchLetterToCol("d") should be(3)
      ColumnMatcher.matchLetterToCol("e") should be(4)
      ColumnMatcher.matchLetterToCol("f") should be(5)
      ColumnMatcher.matchLetterToCol("g") should be(6)
      ColumnMatcher.matchLetterToCol("h") should be(7)
    }
  }}
  "ColumnMatcher" when { "matchColToLetter" should {
    "return the right letter" in {
      ColumnMatcher.matchColToLetter(0) should be("a  ")
      ColumnMatcher.matchColToLetter(1) should be("b  ")
      ColumnMatcher.matchColToLetter(2) should be("c  ")
      ColumnMatcher.matchColToLetter(3) should be("d  ")
      ColumnMatcher.matchColToLetter(4) should be("e  ")
      ColumnMatcher.matchColToLetter(5) should be("f  ")
      ColumnMatcher.matchColToLetter(6) should be("g  ")
      ColumnMatcher.matchColToLetter(7) should be("h  ")
    }
  }}
}
