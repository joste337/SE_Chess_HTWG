package de.htwg.se.SE_Chess_HTWG.model.daoComponent

trait DAOInterface {
  def create(gridJson: String): Unit
  def read(id: Int): String
  def update(id: Int, gridJson: String): Boolean
  def delete(id: Int): Boolean
}
