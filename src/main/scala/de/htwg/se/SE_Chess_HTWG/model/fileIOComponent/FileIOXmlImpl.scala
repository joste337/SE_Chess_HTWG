package de.htwg.se.SE_Chess_HTWG.model.fileIOComponent

class FileIOXmlImpl {
//  val FILE_NAME: String = "game.xml"
//
//  val injector = Guice.createInjector(new a.ChessModule)
//  val pieceFactory: PieceFactory = injector.getInstance(classOf[PieceFactory])
//
//  override def save(grid: GridInterface, gameStatus: GameStatus): Unit = {
//    import java.io._
//    val pw = new PrintWriter(new File(FILE_NAME ))
//    val prettyPrinter = new PrettyPrinter(120,4)
//    val xml = prettyPrinter.format(gridToXml(grid, gameStatus))
//    pw.write(xml)
//    pw.close
//  }
//
//  override def load: (GridInterface, GameStatus) = {
//    val source = scala.xml.XML.loadFile(FILE_NAME)
//    val gameStatus = GameStatus.fromInputString((source \\ "game" \ "@status").text)
//    val injector = Guice.createInjector(new a.ChessModule)
//    var grid = injector.getInstance(classOf[GridInterface])
//    grid.createNewGrid
//
//    val pieces = (source \\ "piece")
//    for (piece <- pieces) {
//      val row: Int = (piece \ "@row").text.toInt
//      val col: Int = (piece \ "@col").text.toInt
//      val value: String = piece.text.trim
//      val isWhite: Boolean = (piece \ "@isWhite").text.toBoolean
//      val hasMoved: Boolean = (piece \ "@hasMoved").text.toBoolean
//      grid.getSquare(row, col).value = Some(pieceFactory.getPiece(Piece.getPieceTypeFromString(value), isWhite, row, col, hasMoved))
//    }
//
//    (grid, gameStatus)
//  }
//
//  def gridToXml(grid: GridInterface, gameStatus: GameStatus) = {
//    <game status ={GameStatus.toOutputString(gameStatus)}>
//      {
//      grid.getSetCells.map(cell => pieceToXml(cell.value.get))
//      }
//    </game>
//  }
//
//  def pieceToXml(piece: Piece) = {
//    <piece row ={piece.row.toString} col={piece.col.toString} isWhite={piece.isWhite.toString} hasMoved={piece.hasMoved.toString}>
//      {piece.toShortcut}
//    </piece>
//  }
}
