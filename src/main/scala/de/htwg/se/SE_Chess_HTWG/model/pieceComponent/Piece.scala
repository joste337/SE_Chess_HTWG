package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult
import play.api.libs.json.{JsValue, Json, Writes}

trait Piece {
  val isWhite: Boolean
  var hasMoved: Boolean
  var col: Int
  var row: Int
  def toSimpleString: String
  def executeMove(grid: GridInterface, move: Move): MovementResult
  def getPossibleSquares(grid: GridInterface): List[Cell]
}

object Piece {
  implicit val pieceWrites = new Writes[Piece] {
    def writes(piece: Piece) = Json.obj(
      "row" -> piece.row,
      "col" -> piece.col,
      "value" -> piece.toSimpleString,
      "isWhite" -> piece.isWhite,
      "hasMoved" -> piece.hasMoved
    )
  }

  def createPieceFromSimpleString(simpleString: String, isWhite: Boolean, row: Int, col: Int, hasMoved: Boolean): Option[Piece] = {
    simpleString match {
      case "P" => Some(Pawn(isWhite, row, col, hasMoved))
      case "R" => Some(Rook(isWhite, row, col, hasMoved))
      case "N" => Some(Knight(isWhite, row, col, hasMoved))
      case "B" => Some(Bishop(isWhite, row, col, hasMoved))
      case "K" => Some(King(isWhite, row, col, hasMoved))
      case "Q" => Some(Queen(isWhite, row, col, hasMoved))
      case _ => None
    }
  }
}
