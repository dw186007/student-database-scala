name := """student-database-scala"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  "org.webjars" % "jquery" % "2.1.1",
  "org.webjars" % "bootstrap" % "3.3.1",
  "mysql"       % "mysql-connector-java" % "5.1.18"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)