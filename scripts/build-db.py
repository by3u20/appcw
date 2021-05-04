#!/usr/bin/env python3
# Script to build the SQLite database from schema output by Android Room ORM
# Usage: build-db.py <db_version>

import argparse
import json
import sqlite3
from string import Template

argparser = argparse.ArgumentParser(description="Script to build the SQLite database from schema output by Android Room ORM")
argparser.add_argument("version", metavar="VER", type=int, help="Database version defined in the @Database annotation")
argparser.add_argument("output", metavar="DB", type=str, help="Path to the output database file")
argparser.add_argument("--extra", "-e", type=str, help="Extra SQL statements to execute")
args = argparser.parse_args()

schema_file = open("../app/schemas/com.example.delivery1.data.LocalDatabase/{}.json".format(args.version), "r")
schema_json = json.load(schema_file)
schema_file.close()
del schema_file

sqls_setup = schema_json["database"]["setupQueries"]
# The placeholders are exactly in the string.Template style!
sqls_table = [Template(entity["createSql"]).substitute(TABLE_NAME=entity["tableName"]) for entity in schema_json["database"]["entities"]]

with sqlite3.connect(args.output) as db:
  cur = db.cursor()

  for sql in sqls_setup + sqls_table:
    print("Executing: {}".format(sql))
    cur.execute(sql)

  if args.extra is not None:
    print("Executing extra SQL script from: {}".format(args.extra))
    with open(args.extra, "r") as extra_sqls_file:
      cur.executescript(extra_sqls_file.read())
