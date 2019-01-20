package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece.Piece
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult
import play.api.libs.json.{JsValue, Json, Writes}

trait PieceInterface {
  val isWhite: Boolean
  var hasMoved: Boolean
  var col: Int
  var row: Int
  def toSimpleString: String
  def executeMove(grid: GridInterface, move: Move): MovementResult
  def getPossibleSquares(grid: GridInterface): List[Cell]
}

object PieceInterface extends Enumeration {
  implicit val pieceWrites = new Writes[PieceInterface] {
    def writes(piece: PieceInterface) = Json.obj(
      "row" -> piece.row,
      "col" -> piece.col,
      "value" -> piece.toSimpleString,
      "isWhite" -> piece.isWhite,
      "hasMoved" -> piece.hasMoved
    )
  }

  def getPieceTypeFromString(simpleString: String): Piece = {
    simpleString match {
      case "P" => Piece.PAWN
      case "R" => Piece.ROOK
      case "N" => Piece.KNIGHT
      case "B" => Piece.BISHOP
      case "K" => Piece.KING
      case "Q" => Piece.QUEEN
    }
  }
}

object Piece extends Enumeration {
  type Piece = Value
  val PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING = Value

//  def createPieceFromSimpleString(simpleString: String, isWhite: Boolean, row: Int, col: Int, hasMoved: Boolean): Option[Piece] = {
//    simpleString match {
//      case "P" => Some(Pawn(isWhite, row, col, hasMoved))
//      case "R" => Some(Rook(isWhite, row, col, hasMoved))
//      case "N" => Some(Knight(isWhite, row, col, hasMoved))
//      case "B" => Some(Bishop(isWhite, row, col, hasMoved))
//      case "K" => Some(King(isWhite, row, col, hasMoved))
//      case "Q" => Some(Queen(isWhite, row, col, hasMoved))
//      case _ => None
//    }
//  }
}
