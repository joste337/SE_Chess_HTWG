package de.htwg.se.SE_Chess_HTWG.model.fileIOComponent

import de.htwg.se.SE_Chess_HTWG.controller.GameStatus
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.testUtil.TestGrid
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FileIOXmlSpec extends WordSpec with Matchers {
  val testGrid: GridInterface = TestGrid.getTestGrid
  val fileIO: FileIOXmlImpl = new FileIOXmlImpl

  "A grid" when { "saved and loaded via XML" should {
    fileIO.save(testGrid, GameStatus.PLAYER1TURN)
    val loadedGrid = fileIO.load._1

    "load correctly" in {
      loadedGrid.toString.equals(testGrid.toString) should be(true)
    }
  }}
}
