package com.rahasak.sjobs

import java.util.Date

import com.datastax.spark.connector._
import com.datastax.spark.connector.rdd.ReadConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.cassandra._
import org.apache.spark.sql.functions._

object CassandraJob extends App {

  case class Document(id: String, approved: Boolean, dept: String)

  // context for spark
  val spark = SparkSession.builder
    //.master("spark://spark-master-svc:7077")
    .appName("lambda")
    //.config("spark.cassandra.connection.host", "192.168.64.17")
    //.config("spark.cassandra.connection.port", "9042")
    .getOrCreate()

  // SparkSession has implicits

  // documents RDD
  val documentRDD = spark.sparkContext
    .cassandraTable[Document]("rahasak", "documents")
    .select("id", "approved", "dept")

  // view data
  documentRDD.take(10).foreach(println)

  // filter
  documentRDD.filter(_.id.contains("dev")).take(10).foreach(println)

  // documents DF
  val documentDF = spark.read
    .cassandraFormat("documents", "rahasak")
    .options(ReadConf.SplitSizeInMBParam.option(32))
    .load()

  // view schema
  documentDF.printSchema()

  // view data
  documentDF.show(10)

}
