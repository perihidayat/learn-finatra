organization := "id.co.peper"

name := "learn-finatra"

version := "1.0"

scalaVersion := "2.11.8"

assemblyJarName in assembly := s"${name.value}_${version.value}.jar"

test in assembly := {}

lazy val versions = new {
  val finagle = "6.40.0"
  val finatra = "2.4.0"
  val guice = "4.0"
  val logback = "1.1.+"
  val typesafeConfig = "1.3.0"
  val typesafeConfigGuice = "0.0.3"
  val accord = "0.6"
  val json4s = "3.5.2"
}

libraryDependencies ++= Seq(
  "com.twitter" % "finagle-serversets_2.11" % versions.finagle,
  "com.twitter" % "finagle-redis_2.11" % versions.finagle,
  "com.twitter" % "util-cache_2.11" % versions.finagle,

  "com.twitter" % "finatra-http_2.11" % versions.finatra,

  "ch.qos.logback" % "logback-classic" % versions.logback,
  "com.typesafe" % "config" % versions.typesafeConfig,
  "com.github.racc" % "typesafeconfig-guice" % versions.typesafeConfigGuice,
  "com.wix" %% "accord-core" % versions.accord,
  "org.json4s" % "json4s-jackson_2.11" % versions.json4s
).map(_.exclude("org.slf4j","slf4j-log4j12")).map(_.exclude("org.slf4j","slf4j-jdk14"))