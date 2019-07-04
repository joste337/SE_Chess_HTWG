package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import com.google.inject.Guice
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.{PieceColor, PieceFactory}
import play.api.libs.json.{JsValue, Json}

object GridGenerator {

  def gridFromJson(gridJson: JsValue): GridInterface = {
    val turnStatus = TurnStatus.fromInputString((gridJson \ "game" \ "turnStatus").as[String])
    val injector = Guice.createInjector(new ChessModule)
    var grid = injector.getInstance(classOf[GridInterface])
    val pieceFactory = injector.getInstance(classOf[PieceFactory])
    grid = grid.setTurnStatus(turnStatus)

    val numberOfPieces = (gridJson \\ "row").size
    for (i <- 0 until numberOfPieces) {
      val row = (gridJson \\ "row") (i).as[Int]
      val col = (gridJson \\ "col") (i).as[Int]
      val value = (gridJson \\ "value") (i).as[String]
      val pieceColor = if ((gridJson \\ "isWhite") (i).as[Boolean]) PieceColor.WHITE else PieceColor.BLACK
      val hasMoved = (gridJson \\ "hasMoved") (i).as[Boolean]
      grid = grid.replacePiece(row, col, Some(pieceFactory.getPiece(pieceFactory.getPieceTypeFromString(value), pieceColor, grid.getSquare(row, col), hasMoved)))
    }
    grid
  }

  def gridFromJson(gridJson: String): GridInterface = {
    val json: JsValue = Json.parse(gridJson)
    gridFromJson(json)
  }

  def gridToJson(grid: GridInterface): JsValue = {
    Json.obj(
      "game" -> Json.obj(
        "turnStatus" -> TurnStatus.toOutputString(grid.turnStatus),
        "pieces" -> grid.cells.getSetCells.map(cell => Json.toJson(cell.value.get))
      )
    )
  }
}
