import sqlite3
import sys

def query_sql(db_name, query):
    conn = sqlite3.connect(db_name)
    c = conn.cursor()
    c.execute(query)
    print c.fetchall()

query_sql(sys.argv[1], sys.argv[2])
