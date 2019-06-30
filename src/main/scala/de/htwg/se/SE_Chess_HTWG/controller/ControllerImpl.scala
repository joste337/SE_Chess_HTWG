package de.htwg.se.SE_Chess_HTWG.controller

import com.google.inject.{Guice, Inject, Injector}
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.model.daoComponent.DAOInterface
import de.htwg.se.SE_Chess_HTWG.model.fileIOComponent.FileIOInterface
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridGenerator, GridImpl, GridInterface, Square}
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.json.Json

import scala.swing.event.Event

class ControllerImpl @Inject()(var grid: GridInterface) extends ControllerInterface {
  val injector: Injector = Guice.createInjector(new ChessModule)
  val fileIO: FileIOInterface = injector.instance[FileIOInterface]
  val database: DAOInterface = injector.instance[DAOInterface]
  val undoManager = new UndoManagerImpl(grid)

  override def createNewGrid: Unit = applyMoveResult(grid.createNewGrid)
  override def selectSquare(row: Int, col: Int): Unit = applyMoveResult(grid.executeMove(row, col))
  override def getSquare(row: Int, col: Int): Square = grid.getCell(row, col)
  override def gridString: String = grid.toString

  override def save(): Unit = executeAndPublish(() => fileIO.save(grid))
  override def load(): Unit = executeAndPublish(() => grid = fileIO.load)

  override def undo: Unit = executeAndPublish(() => grid = undoManager.undoMove)
  override def redo: Unit = executeAndPublish(() => grid = undoManager.redoMove)

  override def dbCreate(): Unit = executeAndPublish(() => database.create(Json.prettyPrint(GridGenerator.gridToJson(grid))))
  override def dbRead(id: Int): Unit = executeAndPublish(() => grid = GridGenerator.gridFromJson(database.read(id)))
  override def dbUpdate(id: Int): Unit = executeAndPublish(() => database.update(id, Json.prettyPrint(GridGenerator.gridToJson(grid))))
  override def dbDelete(id: Int): Unit = executeAndPublish(() => database.delete(id))

  def applyMoveResult(grid: GridImpl): Unit = executeAndPublish(() => this.grid = grid)

  def executeAndPublish(callback: () => Unit): Unit = {
    callback.apply()
    publish(new CellChanged)
  }
}

class CellChanged extends Event