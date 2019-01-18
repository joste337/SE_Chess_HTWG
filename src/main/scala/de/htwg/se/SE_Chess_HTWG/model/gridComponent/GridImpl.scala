package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import com.google.inject.Inject
import de.htwg.se.SE_Chess_HTWG.model.movement.Move
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent._
import de.htwg.se.SE_Chess_HTWG.util.ColumnMatcher._
import de.htwg.se.SE_Chess_HTWG.util.MovementResult
import de.htwg.se.SE_Chess_HTWG.util.MovementResult.MovementResult

import scala.math.abs

class GridImpl @Inject()(var cells: Matrix) extends GridInterface {
  val BOARD_SIZE: Int = 8
  var enPassantSquare: Option[Cell] = None
  var promotionSquare: Option[Cell] = None

  def getCell(row: Int, col: Int): Cell = cells.cell(row, col)

  def setCells(cells: Matrix): Unit = this.cells = cells

  def replaceColor(row: Int, col: Int, isWhite: Boolean): Matrix = cells.replaceCell(row, col, Cell(getCell(row, col).value, isWhite))

  def replaceValue(row: Int, col: Int, value: Option[Piece]): Matrix = cells.replaceCell(row, col, Cell(value, getCell(row, col).isWhite))

  def movePiece(move: Move): MovementResult = move.executeMove

  override def createNewGrid: GridInterface = {
    for (col <- 0 until BOARD_SIZE) {
      setCells(replaceValue(1, col, Some(new Pawn(true, 1, col))))
      cells = replaceValue(0, col, Some(matchColToPiece(0, col, true)))

      cells = replaceValue(6, col, Some(new Pawn(false, 6, col)))
      cells = replaceValue(7, col, Some(matchColToPiece(7, col, false)))
    }

    for {
      row <- 0 until BOARD_SIZE
      col <- 0 until BOARD_SIZE
    } if ((row + col) % 2 != 0) cells = replaceColor(row, col, true)

    this
  }

  override def promotePiece(row: Int, col: Int, pieceShortcut: String): MovementResult = {
    if (promotionSquare.isDefined && promotionSquare.get.value.get.row == row && promotionSquare.get.value.get.col == col) {
      val promotionPiece: Option[Piece] = getPromotionPieceFromPieceShortcut(row, col, pieceShortcut, getCell(row, col).value.get.isWhite)
      if (promotionPiece.isDefined) {
        replaceValue(row, col, promotionPiece)
        MovementResult.SUCCESS
      } else {
        MovementResult.ERROR
      }
    } else {
      MovementResult.ERROR
    }
  }

  override def toString: String = {
    val upperBorder = ("+--" + "--" * BOARD_SIZE) + "--------+\n"
    val fillerLine = "|  " + "  " * BOARD_SIZE + "        |\n"
    val lowerBorder = ("+  " + "/" * (BOARD_SIZE)) + "+\n"  //"+--" "/--" u0336
    val line = ("_  " + ("x " * BOARD_SIZE)) + "  |\n"
    var box = "\n" + (upperBorder + fillerLine + (line * BOARD_SIZE)) + fillerLine + lowerBorder

    for {
      row <- (0 until BOARD_SIZE).reverse
      col <- 0 until BOARD_SIZE
    } box = box.replaceFirst("x", getCell(row, col).toString)
      .replaceFirst("_", abs(col - 8).toString).replaceFirst("/", matchColToLetter(col))
    box
  }

  def getPromotionPieceFromPieceShortcut(row: Int, col: Int, pieceShortcut: String, isWhite: Boolean): Option[Piece] = {
    pieceShortcut match {
      case "Q" => Some(Queen(isWhite, row, col))
      case "R" => Some(Rook(isWhite, row, col))
      case "N" => Some(Knight(isWhite, row, col))
      case "B" => Some(Bishop(isWhite, row, col))
      case _ => None
    }
  }
}
