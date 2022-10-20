package com.spark.examples

import java.text.SimpleDateFormat

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types._

import scala.collection.mutable


object ReadFromTxt {


    def main(args: Array[String]): Unit = {

        val spark = SparkSession
            .builder
            .appName("Read from txt file")
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

        // Read data from TXT
        var filePath = "file:///Users/sondywoo/sondy/project/wsJava/JavaStudy/input/sqltable/Person.txt"
//        var filePath = "hdfs://localhost:9000/input/sqltable/Person.txt"
        val rowRDD = spark.sparkContext.textFile(filePath)
            .map(_.split(","))
            .map(p => Row(p(0).trim, p(1).trim, p(2).trim.toInt, p(3).trim, p(4).trim.toDouble))
        // Create spark table
        val personTable = spark.createDataFrame(rowRDD, personSchema)
        personTable.createOrReplaceTempView("Person")

        // Query
        spark.sql("SELECT id, name, age, birthday, indexNum FROM Person order by indexNum").show()
    }
}
