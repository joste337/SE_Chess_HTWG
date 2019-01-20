package de.htwg.se.SE_Chess_HTWG.model.fileIOComponent

import com.google.inject.Guice
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.controller.GameStatus
import de.htwg.se.SE_Chess_HTWG.controller.GameStatus.GameStatus
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent._
import play.api.libs.json._

import scala.io.Source


class FileIOJsonImpl extends FileIOInterface {
  val FILE_NAME: String = "game.json"

  val injector = Guice.createInjector(new ChessModule)
  val pieceFactory: PieceFactory = injector.getInstance(classOf[PieceFactory])

  override def save(grid: GridInterface, gameStatus: GameStatus): Unit = {
    import java.io._
    val pw = new PrintWriter(new File(FILE_NAME))
    pw.write(Json.prettyPrint(gridToJson(grid, gameStatus)))
    pw.close
  }

  override def load: (GridInterface, GameStatus) = {
    val source: String = Source.fromFile(FILE_NAME).getLines.mkString
    val json: JsValue = Json.parse(source)
    val gameStatus = GameStatus.fromInputString((json \ "game" \ "status").as[String])
    val injector = Guice.createInjector(new ChessModule)
    var grid = injector.getInstance(classOf[GridInterface])
    grid = grid.createNewGridWithoutPieces

    val numberOfPieces = (json \\ "row").size
    for (i <- 0 until numberOfPieces) {
      val row = (json \\ "row") (i).as[Int]
      val col = (json \\ "col") (i).as[Int]
      val value = (json \\ "value") (i).as[String]
      val isWhite = (json \\ "isWhite") (i).as[Boolean]
      val hasMoved = (json \\ "hasMoved") (i).as[Boolean]
      grid.getCell(row, col).value = Some(pieceFactory.getPiece(PieceInterface.getPieceTypeFromString(value), isWhite, row, col, hasMoved))
    }

    (grid, gameStatus)
  }

  def gridToJson(grid: GridInterface, gameStatus: GameStatus) = {
    Json.obj(
      "game" -> Json.obj(
        "status" -> GameStatus.toOutputString(gameStatus),
        "pieces" -> grid.getSetCells.map(cell => Json.toJson(cell.value.get))
      )
    )
  }
}
