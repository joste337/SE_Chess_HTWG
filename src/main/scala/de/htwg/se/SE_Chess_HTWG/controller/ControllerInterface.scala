package de.htwg.se.SE_Chess_HTWG.controller

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.Square

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  def gridString: String
  def getSquare(row: Int, col: Int): Square
  def createNewGrid: Unit
  def selectSquare(row: Int, col: Int): Unit

  def undo(): Unit
  def redo(): Unit

  def save(): Unit
  def load(): Unit
}
