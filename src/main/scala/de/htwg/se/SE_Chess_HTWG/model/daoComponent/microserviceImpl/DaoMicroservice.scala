package de.htwg.se.SE_Chess_HTWG.model.daoComponent.microserviceImpl

import com.google.inject.{Guice, Injector}
import de.htwg.se.SE_Chess_HTWG.ChessModule
import de.htwg.se.SE_Chess_HTWG.model.daoComponent.DAOInterface

object DaoMicroservice {
  def main(args: Array[String]): Unit = {
    new DaoMicroserviceServer(new DaoMicroservice)
  }
}

class DaoMicroservice {
  val injector: Injector = Guice.createInjector(new ChessModule)
  var database: DAOInterface = injector.getInstance(classOf[DAOInterface])
}
