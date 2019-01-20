package de.htwg.se.SE_Chess_HTWG.controller

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece.Piece
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceInterface

trait UndoManager {
  var undoStack: List[((Int, Int, Option[PieceInterface]), (Int, Int, Option[PieceInterface]))]
  var redoStack: List[((Int, Int, Option[PieceInterface]), (Int, Int, Option[PieceInterface]))]
  def undoMove: Unit
  def redoMove: Unit
}

class UndoManagerImpl(var grid: GridInterface) extends UndoManager {
  var undoStack: List[((Int, Int, Option[PieceInterface]), (Int, Int, Option[PieceInterface]))] = Nil
  var redoStack: List[((Int, Int, Option[PieceInterface]), (Int, Int, Option[PieceInterface]))] = Nil

  def undoMove: Unit = {
    val cells: ((Int, Int, Option[PieceInterface]), (Int, Int,Option[PieceInterface])) = undoStack.head
    undoStack = undoStack.tail
    redoStack = (cells._2, cells._1)::redoStack
    grid.setCells(grid.replaceValue(cells._1._1, cells._1._2, cells._1._3))
    grid.setCells(grid.replaceValue(cells._2._1, cells._2._2, cells._2._3))
  }

  def redoMove: Unit = {
    val cells: ((Int, Int, Option[PieceInterface]), (Int, Int,Option[PieceInterface])) = redoStack.head
    redoStack = redoStack.tail
    undoStack = (cells._2, cells._1)::undoStack
    grid.setCells(grid.replaceValue(cells._1._1, cells._1._2, cells._1._3))
    grid.setCells(grid.replaceValue(cells._2._1, cells._2._2, cells._2._3))
  }
}
