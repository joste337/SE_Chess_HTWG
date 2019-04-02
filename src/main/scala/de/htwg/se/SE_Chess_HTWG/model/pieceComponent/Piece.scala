package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceType.PieceType
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult
import play.api.libs.json.{Json, Writes}

trait Piece {
  val isWhite: Boolean
  var hasMoved: Boolean
  var col: Int
  var row: Int
  def toSimpleString: String
  def executeMove(grid: GridInterface, move: Move): MovementResult
  def getPossibleSquares(grid: GridInterface): List[Cell]
  def getImageName: String
}

object Piece extends Enumeration {
  implicit val pieceWrites = new Writes[Piece] {
    def writes(piece: Piece) = Json.obj(
      "row" -> piece.row,
      "col" -> piece.col,
      "value" -> piece.toSimpleString,
      "isWhite" -> piece.isWhite,
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
}

object PieceType extends Enumeration {
  type PieceType = Value
  val PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING = Value
}
