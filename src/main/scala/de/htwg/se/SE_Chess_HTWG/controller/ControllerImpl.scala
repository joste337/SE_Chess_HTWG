package de.htwg.se.SE_Chess_HTWG.controller

import com.google.inject.{Guice, Inject, Injector}
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.model.daoComponent.DAOInterface
import de.htwg.se.SE_Chess_HTWG.model.fileIOComponent.FileIOInterface
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridGenerator, GridImpl, GridInterface, Square}
import net.codingwell.scalaguice.InjectorExtensions._
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.json.Json

import scala.concurrent.Future
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.swing.event.Event
import scala.util.{Failure, Success}

class ControllerImpl @Inject()(var grid: GridInterface) extends ControllerInterface {
  val injector: Injector = Guice.createInjector(new ChessModule)
  val fileIO: FileIOInterface = injector.instance[FileIOInterface]
  val database: DAOInterface = injector.instance[DAOInterface]
  val undoManager = new UndoManagerImpl(grid)
  val log : Logger = LoggerFactory.getLogger(this.getClass)

  override def createNewGrid: Unit = applyMoveResult(grid.createNewGrid)
  override def selectSquare(row: Int, col: Int): Unit = applyMoveResult(grid.executeMove(row, col))
  override def getSquare(row: Int, col: Int): Square = grid.getSquare(row, col)
  override def gridString: String = grid.toString

  override def save(): Unit = Future(fileIO.save(grid)).onComplete {
    case Success(_) => log.info("Grid saved successfully to file.")
    case Failure(_) => log.info("Grid could not be saved to file.")
  }

  override def load(): Unit = Future(fileIO.load).onComplete {
    case Success(result) => executeAndPublish(() => grid = result)
    case Failure(_) => log.info("Grid could not be loaded from file.")
  }

  override def undo(): Unit = undoManager.undoMove match {
    case Success(resultGrid) => executeAndPublish(() => grid =resultGrid)
    case Failure(_) => println("Couldn't undo move.")
  }

  override def redo(): Unit = undoManager.redoMove match {
    case Success(resultGrid) => executeAndPublish(() => grid =resultGrid)
    case Failure(_) => println("Couldn't redo move.")
  }

  override def dbCreate(): Future[Unit] = Future(database.create(Json.prettyPrint(GridGenerator.gridToJson(grid))))
  override def dbRead(id: Int): Unit = applyMoveResult(GridGenerator.gridFromJson(database.read(id)))
  override def dbUpdate(id: Int): Future[Boolean] = Future(database.update(id, Json.prettyPrint(GridGenerator.gridToJson(grid))))
  override def dbDelete(id: Int): Future[Boolean] = Future(database.delete(id))

  def applyMoveResult(grid: GridInterface): Unit = executeAndPublish(() => this.grid = grid)

  def executeAndPublish(callback: () => Unit): Unit = {
    callback.apply()
    publish(new CellChanged)
  }
}

class CellChanged extends Event