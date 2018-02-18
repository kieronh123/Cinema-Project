from flask import Flask, g
import sqlite3
import json

DATABASE = 'database/test.db'

app = Flask(__name__)

@app.route("/")
def hello():
    cur = get_db().cursor()
    cur.execute("SELECT * FROM test1;")
    to_Return = str(cur.fetchall())
    return to_Return

@app.route('/post/<data>', methods=['GET', 'POST'])
def post_route(data):
    query = data.replace("_", " ")
    cur = get_db().cursor()
    cur.execute(query)
    r = [dict((cur.description[i][0], value) \
               for i, value in enumerate(row)) for row in cur.fetchall()]
    cur.connection.close()
    json_output = json.dumps(r)
    return json_output
    #return query

def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = sqlite3.connect(DATABASE)
    return db

@app.teardown_appcontext
def close_connection(exception):
    db = getattr(g, '_database', None)
    if db is not None:
        db.close()

if __name__ == "__main__":
    app.run()
