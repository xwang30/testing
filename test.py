import pyodbc
from sqlalchemy import column
from datetime import datetime
import time
from datetime import datetime
import time

print("hello")

host="az-commercial-db-obu.cloud.databricks.com" ## 
token = "dapi5d4ee8099385e66e0f235a93d14b2757" ## test 

#http_path = "sql/1.0/endpoints/559f8d0edf86116d" ## sql endpoint

http_path = "sql/protocolv1/o/1209392739395394/0429-161325-ytc9m9nd" ## fix cluster

conn = pyodbc.connect("Driver=/Library/simba/spark/lib/libsparkodbc_sbu.dylb;" +
                      "HOST=" + host +";"
                      "PORT=443;" +
                      "Schema=o2_prod_app_commons;" +
                     # "SparkServerType=3;" +
                      "AuthMech=3;" +
                      "UID=token;" +
                      "PWD=" + token +";" 
                      "ThriftTransport=2;" +
                      "SSL=1;" +
                      "HTTPPath="+ http_path +";"
                      "LogPath=/tmp/simba_logs_1;LogLevel=6;SocketTimeout=60" ,
                    #  "HTTPPath=sql/protocolv1/o/0/1018-042223-label635",
                      autocommit=True)


# Run a SQL query by using the preceding connection.
cursor = conn.cursor()
start = time.time()
try: 
  print("calling fetchall at " + str(datetime.now()))


  #rows = cursor.tables(table='%',catalog='%',schema='gbl_ref_ops_asset_team_scorecard_dev')
  rows = cursor.tables(table='%',catalog='%',schema='%')
  
  nrows = len(cursor.fetchall())
  print("number of tables:" + str(nrows))

  for row in cursor.tables(table='%',catalog='%',schema='%'):
    print("row:"+str(row))

  
  ##rows = cursor.columns(table='%',catalog='%', schema ='%')
  ##nrows = len(rows)

  #for row in cursor.columns(table='%',catalog='%', schema ='%'):
  #    print("column:"+str(row.column_name))
  print("completed fetchall at " + str(datetime.now()))
  end = time.time()

except Exception as e: 
  print(e)
  print("Errored at " + str(datetime.now()))
  end = time.time()

print("time taken "+ str(end - start))
