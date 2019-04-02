package de.htwg.se.SE_Chess_HTWG

import com.google.inject.AbstractModule
import de.htwg.se.SE_Chess_HTWG.controller.{ControllerImpl, ControllerInterface, UndoManager, UndoManagerImpl}
import de.htwg.se.SE_Chess_HTWG.model.fileIOComponent.{FileIOInterface, FileIOJsonImpl, FileIOXmlImpl}
import de.htwg.se.SE_Chess_HTWG.model.gridComponent._
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.{PieceFactory, PieceFactoryImpl}
import net.codingwell.scalaguice.ScalaModule

class ChessModule extends AbstractModule with ScalaModule{

  def configure(): Unit = {
    bind[GridInterface].to[GridImpl]
    bind[ControllerInterface].to[ControllerImpl]
    bind[FileIOInterface].to[FileIOXmlImpl]
    bind[PieceFactory].to[PieceFactoryImpl]
  }
}
