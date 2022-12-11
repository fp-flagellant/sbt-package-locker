
val CirceVersion = "0.14.3"

lazy val root = (project in file("."))
  .settings(
    organization := "fp-flagellant",
    name := "sbt-package-locker"
  ).settings(libraryDependencies += "dev.zio" %% "zio-json" % "0.4.2"
).enablePlugins(SbtPlugin)