# Databricks notebook source
import urllib.request
urllib.request.urlretrieve("https://drive.google.com/file/d/1GWIeUziUwKJw141UwvkBr0JuR7MNHnSv/view?usp=sharing","/dbfs/databricks/init_scripts/ntp.conf")

# COMMAND ----------

import urllib.request
urllib.request.urlretrieve("https://drive.google.com/file/d/1GWIeUziUwKJw141UwvkBr0JuR7MNHnSv/view?usp=sharing","/dbfs/databricks/init_scripts/ntp.conf")

# COMMAND ----------

dbutils.fs.put("/databricks/init_scripts/ntp.conf","""# NTP configuration
server time.windows.com""", True)

# COMMAND ----------

# MAGIC %sh ls -al /dbfs/databricks/init_scripts/

# COMMAND ----------

dbutils.fs.put("/databricks/init_scripts/ntp.sh","""
#!/bin/bash
echo "13.86.101.172 time.windows.com" >> /etc/hosts
cp /dbfs/databricks/init_scripts/ntp.conf /etc/
sudo service ntp restart""",True)

# COMMAND ----------

display(dbutils.fs.ls("dbfs:/databricks/init_scripts/ntp.sh"))

# COMMAND ----------

# MAGIC %sh cat /etc/ntp.conf

# COMMAND ----------

# MAGIC %sh ntpq -p

# COMMAND ----------

