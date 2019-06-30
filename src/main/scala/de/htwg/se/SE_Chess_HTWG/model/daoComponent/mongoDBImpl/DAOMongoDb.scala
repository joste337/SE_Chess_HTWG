package de.htwg.se.SE_Chess_HTWG.model.daoComponent.mongoDBImpl

import de.htwg.se.SE_Chess_HTWG.model.daoComponent.DAOInterface
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.bson.collection.mutable.Document
import org.mongodb.scala.model.Filters._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class DAOMongoDb extends DAOInterface {
  val codecRegistry: CodecRegistry = fromRegistries(fromProviders(classOf[Grid]), DEFAULT_CODEC_REGISTRY )
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("grids").withCodecRegistry(codecRegistry)
  val collection: MongoCollection[Grid] = database.getCollection("grid")

  override def create(gridJson: String): Unit = Await.result(collection.insertOne(Grid(getSavedGridCount + 1, gridJson)).toFuture(), Duration.Inf)

  override def read(id: Int): String = Await.result(collection.find(equal("_id", id)).first().toFuture(), Duration.Inf).get()._2

  override def update(id: Int, gridJson: String): Boolean = {
    val filter = Document("_id" -> id)
    val mod = Document("$set" -> Document("grid" -> gridJson))

    Await.result(collection.updateOne(filter, mod).toFuture(), Duration.Inf).wasAcknowledged()
  }

  override def delete(id: Int): Boolean = Await.result(collection.deleteOne(equal("_id", id)).toFuture(), Duration.Inf).wasAcknowledged()

  def getSavedGridCount: Int = Await.result(collection.find().toFuture(),Duration.Inf).toList.map(grid => grid.get()).size
}

case class Grid(_id: Int, grid: String) {
  def get(): (Int, String) = (_id, grid)
}
