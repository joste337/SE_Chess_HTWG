package de.htwg.se.SE_Chess_HTWG.model.fileIOComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface
import de.htwg.se.SE_Chess_HTWG.testUtil.TestGrid
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class FileIOJsonSpec extends WordSpec with Matchers {
  val testGrid: GridInterface = TestGrid.getTestGrid
  val fileIO: FileIOJsonImpl = new FileIOJsonImpl

  "A grid" when { "saved and loaded via JSON" should {
    fileIO.save(testGrid)
    val loadedGrid = fileIO.load

    "load correctly" in {
      loadedGrid.toString.equals(testGrid.toString) should be(true)
    }
  }}
}
