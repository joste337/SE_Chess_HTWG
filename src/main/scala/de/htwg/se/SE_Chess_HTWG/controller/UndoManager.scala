package de.htwg.se.SE_Chess_HTWG.controller

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece

trait UndoManager {
  var undoStack: List[((Int, Int, Option[Piece]), (Int, Int, Option[Piece]))]
  var redoStack: List[((Int, Int, Option[Piece]), (Int, Int, Option[Piece]))]
  def undoMove: Unit
  def redoMove: Unit
}

class UndoManagerImpl(var grid: GridInterface) extends UndoManager {
  var undoStack: List[((Int, Int, Option[Piece]), (Int, Int, Option[Piece]))] = Nil
  var redoStack: List[((Int, Int, Option[Piece]), (Int, Int, Option[Piece]))] = Nil

  def undoMove: Unit = {
    val cells: ((Int, Int, Option[Piece]), (Int, Int,Option[Piece])) = undoStack.head
    undoStack = undoStack.tail
    redoStack = (cells._2, cells._1)::redoStack
    grid.setCells(grid.replaceValue(cells._1._1, cells._1._2, cells._1._3))
    grid.setCells(grid.replaceValue(cells._2._1, cells._2._2, cells._2._3))
  }

  def redoMove: Unit = {
    val cells: ((Int, Int, Option[Piece]), (Int, Int,Option[Piece])) = redoStack.head
    redoStack = redoStack.tail
    undoStack = (cells._2, cells._1)::undoStack
    grid.setCells(grid.replaceValue(cells._1._1, cells._1._2, cells._1._3))
    grid.setCells(grid.replaceValue(cells._2._1, cells._2._2, cells._2._3))
  }
}
