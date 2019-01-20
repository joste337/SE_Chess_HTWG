package de.htwg.se.SE_Chess_HTWG.model.pieceComponent

import java.io.File

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.{Cell, GridInterface}
import de.htwg.se.SE_Chess_HTWG.model.movement.{Move, MovementHelper}
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult
import javax.imageio.ImageIO

case class Knight(override val isWhite: Boolean, override var row: Int, override var col: Int, override var hasMoved: Boolean = false) extends Piece{
  override def toString: String = if (isWhite) "\u2658" else "\u265E"
  val image = ImageIO.read(new File("/de/htwg/se/SE_Chess_HTWG/aView/gui/Pieces.png")) //(getClass.getResource("Pieces.png"))
  val fig = image.getSubimage(3 * (image.getWidth() / 6), (if (isWhite) 1 else 0) * (image.getHeight() / 2), (image.getWidth / 6), (image.getHeight / 2))

  def executeMove(grid: GridInterface, move: Move): MovementResult = {
    if (getPossibleSquares(grid) contains move.getToCell) move.doMove() else MovementResult.ERROR
  }

  def getPossibleSquares(grid: GridInterface): List[Cell] = {
    val possibleSquares: List[(Int, Int)] = List((row + 2, col + 1), (row + 2, col - 1), (row - 2, col + 1),
      (row - 2, col - 1), (row + 1, col + 2), (row + 1, col - 2), (row - 1, col + 2), (row - 1, col - 2))
    MovementHelper.getSquaresInGrid(grid, possibleSquares, isWhite)
  }
}
