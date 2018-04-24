from app import app

#Importing libraries used for database management, SQL and sending of data
from flask import Flask, g, request, Response
import sqlite3
import json

DATABASE = 'app/database/cinema.db'

##Function to execute an SQL query
#Parameters:    SQL query as string
#               The method type as string (e.g 'POST')
def execute_query(query, method):
    #Fetch database
    conn = get_db()
    c = conn.cursor()
    try:
        if method == 'GET':
            #If method is GET, fetch all the database info in a dict
            c.execute(query)
            r = [dict((c.description[i][0], value) \
                       for i, value in enumerate(row)) for row in c.fetchall()]
            json_output = json.dumps(r)
            #Place data in JSON and return
            return json_output
        elif method == 'POST' or method == 'DELETE':
            #Run the query
            c.execute(query)
            #Commit the changes
            conn.commit()
            return "{Status: 200}"
    except sqlite3.IntegrityError:
        #If an error occurs return 400 in JSON
        print(sqlite3.IntegrityError)
        return "{Status:400}"

##Bookings
#Function to get information on all bookings and add a booking
#Parameters:        <NONE>
@app.route('/bookings/', methods=['GET', 'POST'])
def bookings():
    if request.method == 'GET':
        query = "SELECT * FROM Bookings"
        #Return JSON response with pulled information
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'POST':
        #Fetch database
        conn = get_db()
        c = conn.cursor()
        #Pull the parameters passed in the REST request
        data = str(request.form['data'])
        query = "INSERT INTO Bookings VALUES(" + data + ");"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

#Function to get information about or remove a particular movie
#Parameters:        key - a variable that olds the ID of the row you want to access
@app.route('/bookings/<int:key>', methods=['GET', 'DELETE'])
def particularBooking(key):
    if request.method == 'GET':
        query = "SELECT * FROM Bookings WHERE Screening_ID = " + str(key) + ";"
        #Return JSON response with pulled information
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'DELETE':
        query = "DELETE FROM Bookings WHERE Screening_ID = " + str(key) + ";"
        #Return JSON response confirming the deletion
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

##Movies
#Function to get ALL movies in movie database and add a movie
#Parameters:         <NONE>
@app.route('/movies/', methods=['GET', 'POST'])
def movies():
    if request.method == 'GET':
        query = "SELECT * FROM Movies"
        #Return JSON response with pulled information
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'POST':
        #Fetch database
        conn = get_db()
        c = conn.cursor()
        #Get the highest ID value in the database
        max_id_list = c.execute("SELECT Max(Movie_ID) from Movies;").fetchall()
        #Break down the tuple to get the highest value
        max_id_list2 = max_id_list[0]
        number_of_rows = max_id_list2[0]
        #If nothing is in the database, set the number of rows to 0
        if number_of_rows is None:
            number_of_rows = 0
        #Pull the parameters passed in the REST request
        data = str(request.form['data'])
        #Execute the query
        query = "INSERT INTO Movies VALUES(" + str(number_of_rows + 1) + ", " + data + ");"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

#Function to get information about or remove a particular movie
#Parameters:        key - a variable that olds the ID of the row you want to access
@app.route('/movies/<int:key>', methods=['GET', 'DELETE'])
def particularMovie(key):
    if request.method == 'GET':
        query = "SELECT * FROM Movies WHERE Movie_ID = " + str(key) + ";"
        #Return JSON response with pulled information
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'DELETE':
        #Return JSON response confirming the deletion
        query = "DELETE FROM Movies WHERE Movie_ID = " + str(key) + ";"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

##Whats_On
#Function to get ALL movies currently playing and add a movie
#Parameters:        <NONE>
@app.route('/whatson/', methods=['GET', 'POST'])
def whatsOn():
    if request.method == 'GET':
        query = "SELECT * FROM Whats_On"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'POST':
        #Fetch database
        conn = get_db()
        c = conn.cursor()
        #Get the highest ID value in the database
        max_id_list = c.execute("SELECT Max(Screening_ID) from Whats_On;").fetchall()
        #Break down the tuple to get the highest value
        max_id_list2 = max_id_list[0]
        number_of_rows = max_id_list2[0]
        #If nothing is in the database, set the number of rows to 0
        if number_of_rows is None:
            number_of_rows = 0
        #Pull the parameters passed in the REST request
        data = str(request.form['data'])
        #Execute the query
        query = "INSERT INTO Whats_On VALUES(" + str(number_of_rows + 1) + ", " + data + ");"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

#Function to get information about a particular whatson or remove
#Parameters:        key - a variable that olds the ID of the row you want to access
@app.route('/whatson/<int:key>', methods=['GET', 'DELETE'])
def whatsOn_In_Particular(key):
    if request.method == 'GET':
        query = "SELECT * FROM Whats_On WHERE Screening_ID = " + str(key) + ";"
        #Return JSON response with pulled information
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'DELETE':
        query = "DELETE FROM Whats_On WHERE Screening_ID = " + str(key) + ";"
        #Return JSON response confirming the deletion
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

##USERS
#Function for getting all users and adding a user
#Parameters:        <NONE>
@app.route('/users/', methods=['GET', 'POST'])
def get_and_add_Users():
    if request.method == 'GET':
        query = "SELECT * FROM Users"
        #Return JSON response with pulled information
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'POST':
        #Fetch database
        conn = get_db()
        c = conn.cursor()
        #Get the highest ID value in the database
        max_id_list = c.execute("SELECT Max(User_ID) from Users;").fetchall()
        #Break down the tuple to get the highest value
        max_id_list2 = max_id_list[0]
        number_of_rows = max_id_list2[0]
        #If nothing is in the database, set the number of rows to 0
        if number_of_rows is None:
            number_of_rows = 0
        #Pull the parameters passed in the REST request
        data = str(request.form['data'])
        #Execute the query
        query = "INSERT INTO Users VALUES(" + str(number_of_rows + 1) + ", " + data + ");"
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

#Function for getting/removing one particular user of a specific User_ID
#Parameters:        key - a variable that olds the ID of the row you want to access
@app.route('/users/<int:key>', methods=['GET', 'DELETE'])
def users(key):
    if request.method == 'GET':
        query = "SELECT * FROM Users WHERE User_ID = " + str(key) + ";"
        #Return JSON response with pulled information
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')
    elif request.method == 'DELETE':
        query = "DELETE FROM Users WHERE User_ID = " + str(key) + ";"
        #Return JSON response confirming the deletion
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

#Function for getting one particular user by name
#Parameters:        <NONE>
@app.route('/users/name/', methods=['GET'])
def users_by_name():
    if request.method == 'GET':
        #Pull the parameters passed in the REST request
        data = str(request.form['data'])
        query = "SELECT * FROM Users WHERE Username = " + data + ";"
        #Return JSON response with pulled information
        return Response(execute_query(query, request.method), status=200, mimetype='application/json')

##Get the database
#Parameters:        <NONE>
def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = sqlite3.connect(DATABASE, check_same_thread=False)
    return db

##On program close, close db connection
#Parameters:        The exception that occured
@app.teardown_appcontext
def close_connection(exception):
    db = getattr(g, '_database', None)
    if db is not None:
        db.close()

if __name__ == "__main__":
    app.run()
