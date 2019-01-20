package de.htwg.se.SE_Chess_HTWG.model.fileIOComponent

import de.htwg.se.SE_Chess_HTWG.controller.GameStatus.GameStatus
import de.htwg.se.SE_Chess_HTWG.model.gridComponent.GridInterface

trait FileIOInterface {
  def load: (GridInterface, GameStatus)
  def save(grid: GridInterface, gameStatus: GameStatus): Unit
}
