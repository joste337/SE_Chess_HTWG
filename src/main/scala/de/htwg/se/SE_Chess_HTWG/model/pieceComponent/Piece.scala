package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import java.awt.image.BufferedImage

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

trait Piece {
  val isWhite: Boolean
  var hasMoved: Boolean
  var col: Int
  var row: Int
  val fig: BufferedImage //col match: 0 King, 1 Queen, 2 Bishop, 3 Knight, 4 Rook, 5 Pawn at fig -> image.getSubimage(column * (image.getWidth() ....
  def executeMove(grid: GridInterface, move: Move): MovementResult
  def getPossibleSquares(grid: GridInterface): List[Cell]
}
