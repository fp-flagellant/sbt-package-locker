
val CirceVersion = "0.14.3"

lazy val root = (project in file("."))
  .settings(
    organization := "fp-flagellant",
    githubOwner := "fp-flagellant",
    githubRepository := "sbt-package-locker",
    name := "sbt-package-locker"
  ).settings(libraryDependencies ++= Seq(
    "io.circe" %% "circe-core" % CirceVersion,
    "io.circe" %% "circe-parser" % CirceVersion,
    "io.circe" %% "circe-generic" % CirceVersion
  )
).enablePlugins(SbtPlugin)