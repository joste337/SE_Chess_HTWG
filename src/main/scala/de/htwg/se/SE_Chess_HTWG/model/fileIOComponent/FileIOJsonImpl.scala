package de.htwg.se.SE_Chess_HTWG.model.fileIOComponent

import com.google.inject.Guice
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridGenerator, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceFactory
import play.api.libs.json._

import scala.io.Source

class FileIOJsonImpl extends FileIOInterface {
  val FILE_NAME: String = "game.json"
  val injector = Guice.createInjector(new ChessModule)
  val pieceFactory: PieceFactory = injector.getInstance(classOf[PieceFactory])

  override def save(grid: GridInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File(FILE_NAME))
    pw.write(Json.prettyPrint(GridGenerator.gridToJson(grid)))
    pw.close
  }

  override def load: GridInterface = {
    val gridJson: String = Source.fromFile(FILE_NAME).getLines.mkString

    GridGenerator.gridFromJson(gridJson)
  }
}
