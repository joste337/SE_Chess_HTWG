package de.htwg.se.SE_Chess_HTWG.model.fileIOComponent

import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface


trait FileIOInterface {
  def load: GridInterface
  def save(grid: GridInterface): Unit
}
