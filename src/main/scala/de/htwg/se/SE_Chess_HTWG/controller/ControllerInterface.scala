package de.htwg.se.SE_Chess_HTWG.controller

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{GridInterface, Square}

import scala.concurrent.Future
import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  var grid: GridInterface

  def gridString: String
  def getSquare(row: Int, col: Int): Square
  def createNewGrid: Unit
  def selectSquare(row: Int, col: Int): Unit

  def undo(): Unit
  def redo(): Unit

  def save(): Unit
  def load(): Unit

  def dbCreate(): Future[Unit]
  def dbRead(id: Int): Unit
  def dbUpdate(id: Int): Future[Boolean]
  def dbDelete(id: Int): Future[Boolean]
}
