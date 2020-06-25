import sbt.Package._
import sbt._

scalaVersion := "2.13.2"

enablePlugins(JibPlugin)

libraryDependencies ++= Vector (
  Library.vertx_lang_scala,
  Library.vertx_web,
  Library.vertx_web_client  % "test",
  Library.scalaTest         % "test",
  // Uncomment for clustering
  // Library.vertx_hazelcast,

)

packageOptions += ManifestAttributes(
  ("Main-Verticle", "scala:com.github.aesteve.HttpVerticle"))

mainClass in (Compile, packageBin) := Some("io.vertx.core.Launcher")

jibBaseImage := "adoptopenjdk/openjdk11:ubi-minimal-jre"
jibJvmFlags := List("-noverify", "-Djava.security.egd=file:/dev/./urandom")
jibArgs := List("run", "scala:com.github.aesteve.HttpVerticle") // taken from vertx-in-action source code
