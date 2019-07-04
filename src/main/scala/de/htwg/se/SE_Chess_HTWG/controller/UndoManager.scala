package de.htwg.se.SE_Chess_HTWG.controller

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.Piece

import scala.util.{Failure, Success, Try}

trait UndoManager {
  var undoStack: List[((Int, Int, Option[Piece]), (Int, Int, Option[Piece]))] = Nil
  var redoStack: List[((Int, Int, Option[Piece]), (Int, Int, Option[Piece]))] = Nil
  def undoMove: Try[GridInterface]
  def redoMove: Try[GridInterface]
}

class UndoManagerImpl(val grid: GridInterface) extends UndoManager {
  def undoMove: Try[GridInterface] = {
    Try(undoStack.head) match {
      case Success(cells) => {
        undoStack = undoStack.tail
        redoStack = (cells._2, cells._1)::redoStack
        Success(grid.replacePiece(cells._1._1, cells._1._2, cells._1._3).replacePiece(cells._2._1, cells._2._2, cells._2._3))
      }
      case Failure(e) => Failure(e)
    }
  }

  def redoMove: Try[GridInterface] = {
    Try(redoStack.head) match {
      case Success(cells) => {
        redoStack = redoStack.tail
        undoStack = (cells._2, cells._1)::undoStack
        Success(grid.replacePiece(cells._1._1, cells._1._2, cells._1._3).replacePiece(cells._2._1, cells._2._2, cells._2._3))
      }
      case Failure(e) => Failure(e)
    }
  }
}
