package de.htwg.se.SE_Chess_HTWG

import com.google.inject.AbstractModule
import de.htwg.se.SE_Chess_HTWG.controller.{ControllerImpl, ControllerInterface}
import de.htwg.se.SE_Chess_HTWG.model.fileIOComponent.{FileIOInterface, FileIOJsonImpl, FileIOXmlImpl}
import de.htwg.se.SE_Chess_HTWG.model.gridComponent._
import net.codingwell.scalaguice.ScalaModule

class ChessModule extends AbstractModule with ScalaModule{

  def configure() = {
    bind[GridInterface].to[GridImpl]
    bind[ControllerInterface].to[ControllerImpl]
    bind[FileIOInterface].to[FileIOXmlImpl]
  }

}
