// Databricks notebook source
dbutils.fs.ls("dbfs:/databricks/init/")

// COMMAND ----------

dbutils.fs.put("/databricks/init/global.sh","""
echo $DB_IS_DRIVER
if [[ $DB_IS_DRIVER = "TRUE" ]]; then
  echo driver >> test
else
  echo worker >> test
fi
echo “we work together” >>test""")

// COMMAND ----------

dbutils.fs.rm("/databricks/init/global.sh")

// COMMAND ----------

// MAGIC %sh cat /test

// COMMAND ----------

// MAGIC %sh echo testing master
