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

  // Hilfsmethode um die richtige Figur in der jeweiligen Spalte zu setzen
  def matchColToPiece(row: Int, col: Int, isWhite: Boolean): Piece = {
    col match {
      case 0 => Rook(isWhite, row, col)
      case 1 => Knight(isWhite, row, col)
      case 2 => Bishop(isWhite, row, col)
      case 3 => if (isWhite) Queen(isWhite, row, col) else King(isWhite, row, col)
      case 4 => if (isWhite) new King(isWhite, row, col) else Queen(isWhite, row, col)
      case 5 => Bishop(isWhite, row, col)
      case 6 => Knight(isWhite, row, col)
      case 7 => Rook(isWhite, row, col)
    }
  }
}
