package de.htwg.se.SE_Chess_HTWG.util

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.pieceComponent._

object ColumnMatcher {
  // Hilfsmethode um die Randannotationen am Brett zu setzen
  def matchColToLetter(number: Int): String = {
    number match {
      case 0 => "a"
      case 1 => "b"
      case 2 => "c"
      case 3 => "d"
      case 4 => "e"
      case 5 => "f"
      case 6 => "g"
      case 7 => "h"
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

  // Hilfsmethode um die richtige Figur in der jeweiligen Spalte zu setzen
  def matchColToPiece(col: Int, isWhite: Boolean): Piece = {
    col match {
      case 0 => Rook(isWhite)
      case 1 => Knight(isWhite)
      case 2 => Bishop(isWhite)
      case 3 => if (isWhite) Queen(isWhite) else King(isWhite)
      case 4 => if (isWhite) new King(isWhite) else Queen(isWhite)
      case 5 => Bishop(isWhite)
      case 6 => Knight(isWhite)
      case 7 => Rook(isWhite)
    }
  }
}
