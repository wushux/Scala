name := "CSYE7200_wk3_RandomState"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"


//addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.0")

//val scalaTestVersion = "2.2.4"
//
//resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
//
//libraryDependencies ++= Seq(
//  "joda-time" % "joda-time" % "2.9.2",
//  "org.scala-lang.modules" %% "scala-xml" % "1.0.2",
//  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
//  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
//  "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1"
//)
//
//val sprayGroup = "io.spray"
//val sprayJsonVersion = "1.3.2"
//libraryDependencies ++= List("spray-json") map {c => sprayGroup %% c % sprayJsonVersion}