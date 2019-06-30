package de.htwg.se.SE_Chess_HTWG.model.daoComponent.slickImpl

import de.htwg.se.SE_Chess_HTWG.model.daoComponent.DAOInterface
import slick.jdbc.H2Profile.api._
import slick.lifted.ProvenShape

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class DAOSlick extends DAOInterface {
  val grids = TableQuery[Grids]
  val database = Database.forConfig("h2DB")
  database.run(DBIO.seq(grids.schema.create))

  override def create(gridJson: String): Unit = database.run(grids += (getSavedGridCount + 1, gridJson))

  override def read(id: Int): String = Await.result(database.run(grids.filter(_.id === id).result.headOption), Duration.Inf).get._2

  override def update(id: Int, gridJson: String): Boolean = Await.result(database.run(grids.filter(_.id === id).update(id, gridJson)) map { _ >= 0}, Duration.Inf)

  override def delete(id: Int): Boolean = Await.result(database.run(grids.filter(_.id === id).delete) map { _ >= 0}, Duration.Inf)

  def getSavedGridCount: Int = Await.result(database.run(grids.result),Duration.Inf).toList.size
}

case class Grids(tag: Tag) extends Table[(Int, String)](tag, "Grids2") {
  def id: Rep[Int] = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def grid: Rep[String] = column[String]("Grid")

  def * : ProvenShape[(Int, String)] = (id,grid)
}
