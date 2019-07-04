enablePlugins(GatlingPlugin)

name          := "SE_Chess_HTWG"
organization  := "de.htwg.se"
version       := "0.0.1"
scalaVersion  := "2.12.8"


libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "junit" % "junit" % "4.8" % "test",
  "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.1",
  "net.codingwell" %% "scala-guice" % "4.2.3",
  "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6",
  "com.typesafe.play" %% "play-json" % "2.6.6",
  "org.scalafx" %% "scalafx" % "8.0.144-R12",
  "com.typesafe.akka" %% "akka-http"   % "10.1.8",
  "org.scalaj" %% "scalaj-http" % "2.4.2",
  "com.typesafe.akka" %% "akka-stream" % "2.5.19",
  "com.typesafe.slick" %% "slick" % "3.3.0",
  "com.h2database" % "h2" % "1.4.199",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.6.0",
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.0" % "test",
  "io.gatling" % "gatling-test-framework"    % "2.3.0" % "test",
  "org.slf4j" % "slf4j-api" % "1.7.5",
)