package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceType.PieceType
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult
import play.api.libs.json.{Json, Writes}

trait Piece {
  val color: PieceColor
  var hasMoved: Boolean
  var col: Int
  var row: Int
  def isWhite: Boolean = if (color == PieceColor.WHITE) true else false
  def isOnRowAndCol(row: Int, col: Int): Boolean = this.row == row && this.col == col
  def executeMove(grid: GridInterface, move: Move): MovementResult = if (getPossibleSquares(grid) contains move.getToCell) move.doMove() else MovementResult.ERROR
  def getPossibleSquares(grid: GridInterface): List[Cell]
  def toShortcut: String
  def getImageName: String
}

object Piece extends Enumeration {
  implicit val pieceWrites = new Writes[Piece] {
    def writes(piece: Piece) = Json.obj(
      "row" -> piece.row,
      "col" -> piece.col,
      "value" -> piece.toShortcut,
      "color" -> piece.color,
      "hasMoved" -> piece.hasMoved
    )
  }

  def getPieceTypeFromString(simpleString: String): PieceType = {
    simpleString match {
      case "P" => PieceType.PAWN
      case "R" => PieceType.ROOK
      case "N" => PieceType.KNIGHT
      case "B" => PieceType.BISHOP
      case "K" => PieceType.KING
      case "Q" => PieceType.QUEEN
    }
  }

  def getPieceColor(isWhite: Boolean): PieceColor = {
    if (isWhite) PieceColor.WHITE else PieceColor.BLACK
  }
}

object PieceColor extends Enumeration {
  type PieceColor = Value
  val WHITE, BLACK = Value
}

object PieceType extends Enumeration {
  type PieceType = Value
  val PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING = Value
}
