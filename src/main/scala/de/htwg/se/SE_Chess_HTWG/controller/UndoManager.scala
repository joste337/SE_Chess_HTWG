package de.htwg.se.SE_Chess_HTWG.controller

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece

trait UndoManager {
  var undoStack: List[((Int, Int, Option[Piece]), (Int, Int, Option[Piece]))] = Nil
  var redoStack: List[((Int, Int, Option[Piece]), (Int, Int, Option[Piece]))] = Nil
  def undoMove: GridInterface
  def redoMove: GridInterface
}

class UndoManagerImpl(val grid: GridInterface) extends UndoManager {
  def undoMove: GridInterface = {
    val cells: ((Int, Int, Option[Piece]), (Int, Int,Option[Piece])) = undoStack.head
    undoStack = undoStack.tail
    redoStack = (cells._2, cells._1)::redoStack
    grid.replacePiece(cells._1._1, cells._1._2, cells._1._3).replacePiece(cells._2._1, cells._2._2, cells._2._3)
  }

  def redoMove: GridInterface = {
    val cells: ((Int, Int, Option[Piece]), (Int, Int,Option[Piece])) = redoStack.head
    redoStack = redoStack.tail
    undoStack = (cells._2, cells._1)::undoStack
    grid.replacePiece(cells._1._1, cells._1._2, cells._1._3).replacePiece(cells._2._1, cells._2._2, cells._2._3)
  }
}
