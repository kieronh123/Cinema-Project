from flask import Flask, g, request, url_for, Response
from flask_restful import Api, Resource
from flask_api import FlaskAPI, status, exceptions
import sqlite3
import json

DATABASE = 'database/test.db'


app = FlaskAPI(__name__)
api = Api(app)

##Function to execute an SQL query
def execute_query(query, method):
    conn = get_db()
    c = conn.cursor()
    if method == 'GET':
        c.execute(query)
        r = [dict((c.description[i][0], value) \
                   for i, value in enumerate(row)) for row in c.fetchall()]
        json_output = json.dumps(r)
        return json_output
    elif method == 'POST':
        c.execute(query)
        conn.commit()
        return "{Status: 200}"

##USERS
#Function for getting all users and adding a user
@app.route('/users/', methods=['GET', 'POST'])
def get_and_add_Users():
    if request.method == 'GET':
        query = "SELECT * FROM Users"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'POST':
        data = str(request.data.get('data', ''))
        query = "INSERT INTO Users VALUES(" + data + ");"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

#Function for getting one particular user of a specific User_ID
@app.route('/users/<int:key>', methods=['GET'])
def users(key):
    if request.method == 'GET':
        query = "SELECT * FROM Users WHERE User_ID = " + str(key) + ";"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

##Get the database
def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = sqlite3.connect(DATABASE, check_same_thread=False)
    return db

##On program close, close db connection
@app.teardown_appcontext
def close_connection(exception):
    db = getattr(g, '_database', None)
    if db is not None:
        db.close()

if __name__ == "__main__":
    app.run()
