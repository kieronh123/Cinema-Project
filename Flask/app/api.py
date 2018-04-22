from app import app
from flask import Flask, g, request, url_for, Response
import sqlite3
import json

DATABASE = 'app/database/cinema.db'

##Function to execute an SQL query
def execute_query(query, method):
    conn = get_db()
    c = conn.cursor()
    try:
        if method == 'GET':
            c.execute(query)
            r = [dict((c.description[i][0], value) \
                       for i, value in enumerate(row)) for row in c.fetchall()]
            json_output = json.dumps(r)
            return json_output
        elif method == 'POST' or method == 'DELETE':
            c.execute(query)
            conn.commit()
            return "{Status: 200}"
    except sqlite3.IntegrityError:
        return "{Status:400}"

##Bookings
#Function to get information on all bookings and add a booking
@app.route('/bookings/', methods=['GET', 'POST'])
def bookings():
    if request.method == 'GET':
        query = "SELECT * FROM Bookings"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'POST':
        conn = get_db()
        c = conn.cursor()
        data = str(request.form['data'])
        query = "INSERT INTO Bookings VALUES(" + data + ");"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

#Function to get information about or remove a particular movie
@app.route('/bookings/<int:key>', methods=['GET', 'DELETE'])
def particularBooking(key):
    if request.method == 'GET':
        query = "SELECT * FROM Bookings WHERE Screening_ID = " + str(key) + ";"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'DELETE':
        query = "DELETE FROM Bookings WHERE Screening_ID = " + str(key) + ";"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

##Movies
#Function to get ALL movies in movie database and add a movie
@app.route('/movies/', methods=['GET', 'POST'])
def movies():
    if request.method == 'GET':
        query = "SELECT * FROM Movies"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'POST':
        conn = get_db()
        c = conn.cursor()
        max_id_list = c.execute("SELECT Max(Movie_ID) from Movies;").fetchall()
        max_id_list2 = max_id_list[0]
        number_of_rows = max_id_list2[0]
        data = str(request.form['data'])
        query = "INSERT INTO Movies VALUES(" + str(number_of_rows + 1) + ", " + data + ");"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

#Function to get information about or remove a particular movie
@app.route('/movies/<int:key>', methods=['GET', 'DELETE'])
def particularMovie(key):
    if request.method == 'GET':
        query = "SELECT * FROM Movies WHERE Movie_ID = " + str(key) + ";"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'DELETE':
        query = "DELETE FROM Movies WHERE Movie_ID = " + str(key) + ";"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

##Whats_On
#Function to get ALL movies currently playing and add a movie
@app.route('/whatson/', methods=['GET', 'POST'])
def whatsOn():
    if request.method == 'GET':

        query = "SELECT * FROM Whats_On"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'POST':
        conn = get_db()
        c = conn.cursor()
        max_id_list = c.execute("SELECT Max(Screening_ID) from Whats_On;").fetchall()
        max_id_list2 = max_id_list[0]
        number_of_rows = max_id_list2[0]
        data = str(request.form['data'])
        query = "INSERT INTO Whats_On VALUES(" + str(number_of_rows + 1) + ", " + data + ");"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

#Function to get information about a particular whatson or remove
@app.route('/whatson/<int:key>', methods=['GET', 'DELETE'])
def whatsOn_In_Particular(key):
    if request.method == 'GET':
        query = "SELECT * FROM Whats_On WHERE Screening_ID = " + str(key) + ";"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'DELETE':
        query = "DELETE FROM Whats_On WHERE Screening_ID = " + str(key) + ";"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

##USERS
#Function for getting all users and adding a user
@app.route('/users/', methods=['GET', 'POST'])
def get_and_add_Users():
    if request.method == 'GET':
        query = "SELECT * FROM Users"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'POST':
        conn = get_db()
        c = conn.cursor()
        max_id_list = c.execute("SELECT Max(User_ID) from Users;").fetchall()
        max_id_list2 = max_id_list[0]
        number_of_rows = max_id_list2[0]
        data = str(request.form['data'])
        query = "INSERT INTO Users VALUES(" + str(number_of_rows + 1) + ", " + data + ");"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

#Function for getting/removing one particular user of a specific User_ID
@app.route('/users/<int:key>', methods=['GET', 'DELETE'])
def users(key):
    if request.method == 'GET':
        query = "SELECT * FROM Users WHERE User_ID = " + str(key) + ";"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'DELETE':
        query = "DELETE FROM Users WHERE User_ID = " + str(key) + ";"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')


#Function for getting one particular user by name
@app.route('/users/name/', methods=['GET'])
def users_by_name():
    if request.method == 'GET':
        data = str(request.form['data'])
        query = "SELECT * FROM Users WHERE Username = " + data + ";"
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
