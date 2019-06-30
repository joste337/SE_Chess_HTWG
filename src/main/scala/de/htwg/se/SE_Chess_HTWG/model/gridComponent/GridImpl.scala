package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import com.google.inject.Inject
import de.htwg.se.SE_Chess_HTWG.model.moveComponent.MoveExecutor
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent._

import scala.math.abs

case class GridImpl @Inject()(cells: Matrix, specialSquares: SpecialSquares, turnStatus: TurnStatus) extends GridInterface {
  val BOARD_SIZE: Int = 8
  val pieceFactory: PieceFactory = new PieceFactoryImpl

  def createNewGrid: GridImpl = setUpPieces.setTurnStatus(TurnStatus(Turn.P1))

  def getCell(square: (Int, Int)): Square = cells.getCell(square._1, square._2)

  def replacePiece(row: Int, col: Int, value: Option[Piece]): GridImpl = copy(cells.replaceValue(row, col, value))
  def replacePiece(square: (Int, Int), value: Option[Piece]): GridImpl = copy(cells.replaceValue(square._1, square._2, value))

  def moveFromSelectedSquare(destSquare: (Int, Int)): GridImpl = replacePiece(destSquare, getCell(specialSquares.selectedSquare.get).value).replacePiece(specialSquares.selectedSquare.get, None)
  def executeMove(row: Int, col: Int): GridImpl = new MoveExecutor(this).executeMove(row, col)
  def replaceSelectedSquare(square: (Int, Int)): GridImpl = copy(cells, specialSquares.replaceSelectedSquare(Some(square)))
  def resetSelectedSquare(): GridImpl = copy(cells, specialSquares.replaceSelectedSquare(None))
  def squareIsSelected(): Boolean = !specialSquares.selectedSquare.isEmpty

  def setTurnStatus(turnStatus: TurnStatus): GridImpl = copy(cells, specialSquares, turnStatus)
  def nextTurn(): GridImpl = setTurnStatus(turnStatus.nextTurn())

  def unhighlightAll(): GridImpl = copy(cells.unhighlightAll(), specialSquares)
  def highlightSquares(squares: List[Square]): GridImpl = if (squares.isEmpty) this else highlightSquare(squares.head.row, squares.head.col).highlightSquares(squares.tail)
  def highlightSquare(square: (Int, Int)): GridImpl = copy(cells.highlight(square._1, square._2))


  def setUpPieces: GridImpl = {
    var returnGrid = this
    for (col <- 0 until BOARD_SIZE) {
      returnGrid = returnGrid.replacePiece(1, col, Some(pieceFactory.getPiece(PieceType.PAWN, PieceColor.WHITE, getCell((1, col)))))
      returnGrid = returnGrid.replacePiece(0, col, Some(getPieceForColumn(0, col, PieceColor.WHITE)))

      returnGrid = returnGrid.replacePiece(6, col, Some(pieceFactory.getPiece(PieceType.PAWN, PieceColor.BLACK, getCell((6, col)))))
      returnGrid = returnGrid.replacePiece(7, col, Some(getPieceForColumn(7, col, PieceColor.BLACK)))
    }
    returnGrid
  }

  def getPieceForColumn(row: Int, col: Int, pieceColor: PieceColor): Piece = {
    col match {
      case 0 => pieceFactory.getPiece(PieceType.ROOK, pieceColor, getCell((row, col)))
      case 1 => pieceFactory.getPiece(PieceType.KNIGHT, pieceColor, getCell((row, col)))
      case 2 => pieceFactory.getPiece(PieceType.BISHOP, pieceColor, getCell((row, col)))
      case 3 => if (pieceColor == PieceColor.WHITE) pieceFactory.getPiece(PieceType.QUEEN, pieceColor, getCell((row, col))) else pieceFactory.getPiece(PieceType.KING, pieceColor, getCell((row, col)))
      case 4 => if (pieceColor == PieceColor.WHITE) pieceFactory.getPiece(PieceType.KING, pieceColor, getCell((row, col))) else pieceFactory.getPiece(PieceType.QUEEN, pieceColor, getCell((row, col)))
      case 5 => pieceFactory.getPiece(PieceType.BISHOP, pieceColor, getCell((row, col)))
      case 6 => pieceFactory.getPiece(PieceType.KNIGHT, pieceColor, getCell((row, col)))
      case 7 => pieceFactory.getPiece(PieceType.ROOK, pieceColor, getCell((row, col)))
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

  def matchColToLetter(number: Int): String = {
    number match {
      case 0 => "a  "
      case 1 => "b  "
      case 2 => "c  "
      case 3 => "d  "
      case 4 => "e  "
      case 5 => "f  "
      case 6 => "g  "
      case 7 => "h  "
    }
  }
}
