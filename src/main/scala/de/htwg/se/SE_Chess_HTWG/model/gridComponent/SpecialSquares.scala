package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import play.api.libs.json.{Json, Writes}

case class SpecialSquares(enPassantSquare: Option[(Int, Int)] = None, selectedSquare: Option[(Int, Int)] = None, possibleMoves: List[Square] = Nil) {
  def this() = this(None, None)

  def replaceEnPassantSquare(enPassantSquare: Option[(Int, Int)]): SpecialSquares = copy(enPassantSquare, selectedSquare)
  def replaceSelectedSquare(selectedSquare: Option[(Int, Int)]): SpecialSquares = copy(enPassantSquare, selectedSquare)
  def replacePossibleMoves(possibleMoves: List[Square]): SpecialSquares = copy(enPassantSquare, selectedSquare, possibleMoves)
}

object SpecialSquares {
  implicit val specialSquaresWrites = new Writes[SpecialSquares] {
    def writes(specialSquares: SpecialSquares) = Json.obj(
      "enPassantSquare" -> Json.toJson(specialSquares.enPassantSquare),
      "selectedSquare" -> Json.toJson(specialSquares.selectedSquare),
      "possibleMoves" -> specialSquares.possibleMoves.map(square => Json.toJson(square))
    )
  }
}
