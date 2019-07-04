package de.htwg.se.SE_Chess_HTWG.model.gridComponent

import com.google.inject.{Guice, Inject}
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.model.moveComponent.MoveExecutor
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent.PieceColor.PieceColor
import de.htwg.se.SE_Chess_HTWG.model.pieceComponent._

import scala.math.abs

case class GridImpl @Inject()(cells: Matrix, specialSquares: SpecialSquares, turnStatus: TurnStatus) extends GridInterface {
  val BOARD_SIZE: Int = 8
  val injector = Guice.createInjector(new ChessModule)
  val pieceFactory: PieceFactory = injector.getInstance(classOf[PieceFactory])

  override def createNewGrid: GridInterface = setUpPieces.setTurnStatus(TurnStatus(Turn.P1))

  override def getSquare(square: (Int, Int)): Square = cells.getCell(square._1, square._2)

  override def replacePiece(row: Int, col: Int, value: Option[Piece]): GridInterface = copy(cells.replaceValue(row, col, value))
  override def replacePiece(square: (Int, Int), value: Option[Piece]): GridInterface = copy(cells.replaceValue(square._1, square._2, value))

  override def moveFromSelectedSquare(destSquare: (Int, Int)): GridInterface = replacePiece(destSquare, getSquare(specialSquares.selectedSquare.get).value).replacePiece(specialSquares.selectedSquare.get, None)
  override def executeMove(row: Int, col: Int): GridInterface = new MoveExecutor(this).executeMove(row, col)
  override def replaceSelectedSquare(square: (Int, Int)): GridInterface = copy(cells, specialSquares.replaceSelectedSquare(Some(square)))
  override def resetSelectedSquare(): GridInterface = copy(cells, specialSquares.replaceSelectedSquare(None))
  override def squareIsSelected(): Boolean = !specialSquares.selectedSquare.isEmpty

  override def setTurnStatus(turnStatus: TurnStatus): GridInterface = copy(cells, specialSquares, turnStatus)
  override def nextTurn(): GridInterface = setTurnStatus(turnStatus.nextTurn())

  override def unhighlightAll(): GridInterface = copy(cells.unhighlightAll(), specialSquares)


  override def highlightSquares(squares: List[Square]): GridInterface = {
    if (squares.isEmpty) this else highlightSquare(squares.head.row, squares.head.col).highlightSquares(squares.tail)
  }
  def highlightSquare(square: (Int, Int)): GridInterface = copy(cells.highlight(square._1, square._2))


  def setUpPieces: GridInterface = {
    var returnGrid: GridInterface = this
    for (col <- 0 until BOARD_SIZE) {
      returnGrid = returnGrid.replacePiece(1, col, Some(pieceFactory.getPiece(PieceType.PAWN, PieceColor.WHITE, getSquare((1, col)))))
      returnGrid = returnGrid.replacePiece(0, col, Some(getPieceForColumn(0, col, PieceColor.WHITE)))

      returnGrid = returnGrid.replacePiece(6, col, Some(pieceFactory.getPiece(PieceType.PAWN, PieceColor.BLACK, getSquare((6, col)))))
      returnGrid = returnGrid.replacePiece(7, col, Some(getPieceForColumn(7, col, PieceColor.BLACK)))
    }
    returnGrid
  }

  def getPieceForColumn(row: Int, col: Int, pieceColor: PieceColor): Piece = {
    col match {
      case 0 => pieceFactory.getPiece(PieceType.ROOK, pieceColor, getSquare((row, col)))
      case 1 => pieceFactory.getPiece(PieceType.KNIGHT, pieceColor, getSquare((row, col)))
      case 2 => pieceFactory.getPiece(PieceType.BISHOP, pieceColor, getSquare((row, col)))
      case 3 => if (pieceColor == PieceColor.WHITE) pieceFactory.getPiece(PieceType.QUEEN, pieceColor, getSquare((row, col))) else pieceFactory.getPiece(PieceType.KING, pieceColor, getSquare((row, col)))
      case 4 => if (pieceColor == PieceColor.WHITE) pieceFactory.getPiece(PieceType.KING, pieceColor, getSquare((row, col))) else pieceFactory.getPiece(PieceType.QUEEN, pieceColor, getSquare((row, col)))
      case 5 => pieceFactory.getPiece(PieceType.BISHOP, pieceColor, getSquare((row, col)))
      case 6 => pieceFactory.getPiece(PieceType.KNIGHT, pieceColor, getSquare((row, col)))
      case 7 => pieceFactory.getPiece(PieceType.ROOK, pieceColor, getSquare((row, col)))
    }
  }

  override def toString: String = {
    val upperBorder = ("+--" + "--" * BOARD_SIZE) + "--------+\n"
    val fillerLine = "|  " + "  " * BOARD_SIZE + "        |\n"
    val lowerBorder = ("+  " + "/" * (BOARD_SIZE)) + "+\n"  //"+--" "/--" u0336
    val line = ("_  " + ("x " * BOARD_SIZE)) + "   |\n"
    var box = "\n" + (upperBorder + fillerLine + (line * BOARD_SIZE)) + fillerLine + lowerBorder

    for {
      row <- (0 until BOARD_SIZE).reverse
      col <- 0 until BOARD_SIZE
    } box = box.replaceFirst("x", getSquare(row, col).toString)
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
