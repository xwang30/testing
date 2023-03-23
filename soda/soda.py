# Databricks notebook source
# print('Hello World')

# from common_utils.data_quality.soda_runner import BaseSodaRunner

# import json

#

#

# dq_config = json.loads(dbutils.widgets.get("dq_config_json"))

# soda_checks_dict = json.loads(dbutils.widgets.get("soda_checks_json"))

# username = dbutils.secrets.get(scope = "default-iam-user", key = "access_key_id")

# key = dbutils.secrets.get(scope = "default-iam-user", key = "secret_access_key")

# boto3_creds = {'username': username, 'key': key}

#

# print(f'dq_config: {dq_config}')

# print(f'soda_checks_dict: {soda_checks_dict}')

#

# soda_runner = BaseSodaRunner(dq_config, soda_checks_dict=soda_checks_dict, spark_session=spark,

#     boto3_creds=boto3_creds)

# soda_runner.execute_check()

 

# %pip freeze

# !python --version

# from soda import scan

# from soda.scan import Scan

# from common_utils.data_quality.soda_scan import Scan
%pip freeze

!python --version

import soda.scan

from soda import scan

from soda.scan import Scan

from common_utils.data_quality.soda_scan import Scan
