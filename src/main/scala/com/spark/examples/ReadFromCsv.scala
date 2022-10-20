package com.spark.examples

import java.text.SimpleDateFormat

import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}

import scala.collection.mutable


object ReadFromCsv {


    def main(args: Array[String]): Unit = {

        val spark = SparkSession
            .builder
            .appName("Read from csv file")
            .config("spark.master", "local")
            .getOrCreate()

        // Define table structure
        val personSchema: StructType = StructType(mutable.ArraySeq(
            StructField("id", StringType, nullable = false),
            StructField("name", StringType, nullable = false),
            StructField("age", IntegerType, nullable = false),
            StructField("birthday", StringType, nullable = true),
            StructField("indexNum", DoubleType, nullable = true)
        ))

        def getDate(time: String) = {
            val now: Long=System.currentTimeMillis()
            var df: SimpleDateFormat = new SimpleDateFormat(time)
            df.format(now)
        }

        // Read data from CSV
//        var filePath = "file:///Users/sondywoo/sondy/project/wsJava/JavaStudy/input/sqltable/Person.csv"
        var filePath = "hdfs://localhost:9000/input/sqltable/Person.csv"
        val personDataFrame = spark.sqlContext.read
            .format("com.databricks.spark.csv")
            .option("header", "true")
            .schema(personSchema)
            .load(filePath)

        // Create spark table
        personDataFrame.createOrReplaceTempView("Person")

        // Query
        spark.sql("SELECT id, name, age, birthday, indexNum FROM Person order by indexNum").show()
    }
}
