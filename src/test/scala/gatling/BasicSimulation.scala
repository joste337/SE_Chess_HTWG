package gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class BasicSimulation extends Simulation{

  val httpProtocol = http.baseURL("http://localhost:8080")

  val uri = "http://localhost:8080/"

  val scenario1 = scenario("BasicSimulation")
      .exec(http("request_0")
        .get("/chess/view"))
      .exec(http("request_1")
        .get("/chess/new"))
      .exec(http("request_2")
        .get("/chess/select?row=1&col=1"))
      .exec(http("request_3")
        .get("/chess/select?row=2&col=1"))
      .exec(http("request_4")
        .get("/chess/view"))

  setUp(
    scenario1.inject(atOnceUsers(200))
  ).protocols(httpProtocol)
}
