// Databricks notebook source
// MAGIC %sh
// MAGIC cat /databricks/hive/conf/hive-site.xml

// COMMAND ----------

import org.apache.spark.sql.functions._ 
spark.sql("set spark.sql.shuffle.partitions=20000")
val dis = spark.sql("select * from xin_test_table")
val unionDF = dis.union(dis).union(dis).union(dis).union(dis).union(dis).union(dis).union(dis).union(dis).union(dis).union(dis).union(dis).union(dis).union(dis).union(dis).union(dis)
val df = unionDF.withColumn("id",monotonically_increasing_id())
val result = df.where(($"id"%2 === 0) || ($"id"%2 === 1)).sort($"_c0".asc)
result.repartition(2000)
result.write.mode("overwrite").csv("/tmp/csv")

// COMMAND ----------

dbutils.fs.put("/databricks/test/openssl.sh","""
#!/bin/bash
/databricks/python/bin/pip install pyOpenSSL==19.0.0""")

// COMMAND ----------

import sqlContext.implicits._
import org.apache.spark.sql.Row
import org.apache.spark.sql.functions.abs

val matches = sqlContext.sparkContext.parallelize(Row(1, "John Wayne", "John Doe"), Row(2, "Ive Fish", "San Simon")))

val players = sqlContext.sparkContext.parallelize(Seq(
  Row("John Wayne", 1986),
  Row("Ive Fish", 1990),
  Row("San Simon", 1974),
  Row("John Doe", 1995)
))

val matchesDf = sqlContext.createDataFrame(matches, StructType(Seq(
  StructField("matchId", IntegerType, nullable = false),
  StructField("player1", StringType, nullable = false),
  StructField("player2", StringType, nullable = false)))
).as('matches)

val playersDf = sqlContext.createDataFrame(players, StructType(Seq(
  StructField("player", StringType, nullable = false),
  StructField("birthYear", IntegerType, nullable = false)
))).as('players)

matchesDf
  .join(playersDf, $"matches.player1" === $"players.player")
  .select($"matches.matchId" as "matchId", $"matches.player1" as "player1", $"matches.player2" as "player2", $"players.birthYear" as "player1BirthYear")
  .join(playersDf, $"player2" === $"players.player")
  .select($"matchId" as "MatchID", $"player1" as "Player1", $"player2" as "Player2", $"player1BirthYear" as "BYear_P1", $"players.birthYear" as "BYear_P2")
  .withColumn("Diff", abs('BYear_P2.minus('BYear_P1)))
  .show()

// COMMAND ----------

// MAGIC %scala
// MAGIC 
// MAGIC commit new 4

// COMMAND ----------
