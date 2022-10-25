name := "sjobs"

version := "1.0"

scalaVersion := "2.12.7"

val grpcVersion = "1.41.0"

libraryDependencies ++= {
  Seq(
    "org.apache.spark"                %% "spark-core"                     % "3.2.0",
    "org.apache.spark"                %% "spark-sql"                      % "3.2.0",
    "com.datastax.spark"              %% "spark-cassandra-connector"      % "3.1.0"
  )
}

assemblyMergeStrategy in assembly := {
  case PathList(ps @ _*) if ps.last endsWith "reflection-config.json" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith "AuthenticationType.class" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith "git.properties" => MergeStrategy.discard
  case x if x.contains("javax/inject") => MergeStrategy.first
  case x if x.contains("org/aopalliance") => MergeStrategy.first
  case x if x.contains("org/apache/commons") => MergeStrategy.first
  case x if x.contains("org/apache/hadoop") => MergeStrategy.first
  case x if x.contains("org/apache/spark/unused") => MergeStrategy.first
  case x if x.contains("org/slf4j") => MergeStrategy.first
  case x if x.contains("io/netty") => MergeStrategy.first
  case x if x.contains("io.netty") => MergeStrategy.first
  case "module-info.class" => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
