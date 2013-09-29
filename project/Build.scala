import sbt._
import sbt.Keys._

object TypoSafeBuild extends Build {
val default = Seq(
    "org.scalatest" % "scalatest_2.10" % "2.0.M8" % "test",
    "org.seleniumhq.selenium" % "selenium-java" % "2.33.0" % "test",
    "com.google.code.findbugs" % "jsr305" % "1.3.+"
)

lazy val main = Project(
  id = "stat",
  base = file("."),
  settings = Project.defaultSettings ++ Seq(
    name := "stat",
    scalaVersion := "2.10.2",
    version := "1.0",
    resolvers += "scala-tools releases" at "http://scala-tools.org/repo-releases",
    resolvers += "releases" at "http://oss.sonatype.org/content/repositories/releases",
    resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    libraryDependencies := default
    )
  )
}
