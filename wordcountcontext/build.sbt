name := "Word Count With Context"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.10" % "1.0.1",
  "org.specs2" %% "specs2" % "2.3.13" % "test"
)

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"
