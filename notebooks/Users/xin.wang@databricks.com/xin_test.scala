// Databricks notebook source
// MAGIC %sh
// MAGIC 
// MAGIC wget https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js

// COMMAND ----------

from_github = "Previdência"

// COMMAND ----------

dbutils.secrets.get("xin", "password")

// COMMAND ----------

val db = spark.sql("show tables")
val db_name = db.select("tableName").rdd.map(r => r(0)).collect()
for (name <- db_name) spark.sql(s"""insert into $name values(16,17)""")

// COMMAND ----------

// MAGIC %sql
// MAGIC SELECT * FROM global_temp.people 

// COMMAND ----------

val db = spark.sql("show tables")
db.createGlobalTempView("people")

// COMMAND ----------

// MAGIC %sql
// MAGIC select * from global_temp.people

// COMMAND ----------

spark.sql("show databases").show(1000,false)

// COMMAND ----------

db_name.write.format("parquet").saveAsTable("defaultname")

// COMMAND ----------

spark.sql(s"""insert into $name values(13,14)""")
spark.sql("insert into test1 values(14,15)")

// COMMAND ----------

val mount = dbutils.fs.ls(s"/mnt/").toDF

// COMMAND ----------



// COMMAND ----------

var seq:Seq[Int] = Seq(52,85,1,8,3,2,7)
val df = seq.toDF

// COMMAND ----------

// MAGIC %sh
// MAGIC pip install -U databricks-connect==5.5.*

// COMMAND ----------

display(dbutils.fs.ls("/"))

// COMMAND ----------

// MAGIC %sh
// MAGIC java -version

// COMMAND ----------

