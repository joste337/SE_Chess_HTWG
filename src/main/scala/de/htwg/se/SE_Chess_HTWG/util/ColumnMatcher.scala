package de.htwg.se.SE_Chess_HTWG.util

import de.htwg.se.SE_Chess_HTWG.model.pieceComponent._

object ColumnMatcher {
  // Hilfsmethode um die Randannotationen am Brett zu setzen
  def matchColToLetter(number: Int): String = {
    number match {
      case 0 => "a  "  //a
      case 1 => "b  "
      case 2 => "c  "
      case 3 => "d  "
      case 4 => "e  "
      case 5 => "f  "
      case 6 => "g  "
      case 7 => "h  "
    }
  }

  def matchLetterToCol(letter: String): Int = {
    letter match {
      case "a" => 0
      case "b" => 1
      case "c" => 2
      case "d" => 3
      case "e" => 4
      case "f" => 5
      case "g" => 6
      case "h" => 7
    }
  }
}
