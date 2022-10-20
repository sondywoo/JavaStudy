package com.spark.examples

import java.text.SimpleDateFormat

import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}

import scala.collection.mutable


object ReadFromJdbc {
    def main(args: Array[String]): Unit = {

        val spark = SparkSession
            .builder
            .appName("Read from MySQL via JDBC")
            .config("spark.master", "local")
            .getOrCreate()

        def getDate(time: String) = {
            val now: Long=System.currentTimeMillis()
            var df: SimpleDateFormat = new SimpleDateFormat(time)
            df.format(now)
        }

        val jdbcDF = spark.read.format("jdbc")
            .options(Map(
//                "driver" -> "com.mysql.cj.jdbc.Driver",
//                "url" -> "jdbc:mysql://40.73.247.165:3306",
//                "dbtable" -> "terrysql.t_traits",
//                "user" -> "sondy",
//                "password" -> "pswd123",
                "driver" -> "com.microsoft.sqlserver.jdbc.SQLServerDriver",
                "url" -> "jdbc:sqlserver://abisql.database.chinacloudapi.cn:1433;database=analyticshub;",
                "dbtable" -> "dbo.baidu_poc_all",
                "user" -> "abisql",
                "password" -> "abi_china_09",
                "fetchsize" -> "50"))
            .load()

        jdbcDF.show
    }
}
