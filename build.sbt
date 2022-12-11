
val CirceVersion = "0.14.3"

lazy val root = (project in file("."))
  .settings(
    name := "sbt-package-locker"
  ).settings(libraryDependencies += "dev.zio" %% "zio-json" % "0.4.2"
).enablePlugins(SbtPlugin)


ThisBuild / organization := "io.github.fp-flagellant"
ThisBuild / organizationName := "fp-flagellant"
ThisBuild / organizationHomepage := Some(url("https://github.com/fp-flagellant"))

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/fp-flagellant/sbt-package-locker"),
    "scm:git@github.fp-flagellant/sbt-package-locker.git"
  )
)

ThisBuild / developers := List(
  Developer(
    id    = "fp-flagellant",
    name  = "fp-flagellant",
    email = "brandn3wl1f3@gmail.com",
    url   = url("https://github.com/fp-flagellant")
  )
)

ThisBuild / description := "sbt plugin to lock lib versions"
ThisBuild / licenses := List("The Unlicense" -> new URL("https://unlicense.org/"))
ThisBuild / homepage := Some(url("https://github.com/fp-flagellant/sbt-package-locker"))

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }

ThisBuild / publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

ThisBuild / publishMavenStyle := true

ThisBuild / versionScheme := Some("early-semver")