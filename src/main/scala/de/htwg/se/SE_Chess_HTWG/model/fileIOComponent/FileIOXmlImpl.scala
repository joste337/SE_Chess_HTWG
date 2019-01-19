package de.htwg.se.SE_Chess_HTWG.model.fileIOComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{CellInterface, GridInterface}
import play.api.libs.json.{JsNumber, Json, Writes}
import java.io._

import de.htwg.se.SE_Chess_HTWG.controller.GameStatus
import de.htwg.se.SE_Chess_HTWG.controller.GameStatus.GameStatus
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece

class FileIOXmlImpl extends FileIOInterface {
  override def save(grid: GridInterface, gameStatus: GameStatus): Unit = {

  }

  override def load: (GridInterface, GameStatus) = {
    (null, null)
  }

}
