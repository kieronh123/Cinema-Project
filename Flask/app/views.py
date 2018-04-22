from app import app
import sqlite3
from flask import render_template, g, redirect, request

from .models import WhatsOn, Movie, Booking, User

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
            #json_output = json.dumps(r)
            return r
        elif method == 'POST' or method == 'DELETE':
            c.execute(query)
            conn.commit()
            return "{Status: 200}"
    except sqlite3.IntegrityError:
        return "{Status:400}"

def getMovies():
    movies_db = execute_query("SELECT * FROM MOVIES;", "GET")
    movies = []
    for item in movies_db:
        movies.append(Movie(item["Movie_ID"], item["Movie_Name"], item["Movie_Rating"],item["Movie_Runtime"],item["Movie_Info"], item["Movie_Image"]))

    return movies


def getMoviebyID(id):
    movie = execute_query("SELECT * FROM MOVIES where Movie_ID=%s" % id, "GET")
    if movie:
        item = movie[0]
        return Movie(item["Movie_ID"], item["Movie_Name"], item["Movie_Rating"],item["Movie_Runtime"],item["Movie_Info"], item["Movie_Image"])

def getWhatsOn():
    screenings = execute_query("SELECT * FROM Whats_On;", "GET")
    whatson = []

    for idScreening in screenings:
        whatson.append(getWhatsOnbyID(idScreening["Screening_ID"]))

    return whatson

def getWhatsOnByMovieID(id):
    screenings = execute_query("SELECT * FROM Whats_On where Movie_ID=%s;"%id, "GET")
    whatson = []

    for idScreening in screenings:
        whatson.append(getWhatsOnbyID(idScreening["Screening_ID"]))
    return whatson

def getWhatsOnbyID(id):
    whatson= execute_query("SELECT * FROM Whats_On where Screening_ID=%s;" % id, "GET")
    if whatson:
        item = whatson[0]
        return WhatsOn(item["Screening_ID"], item["Movie_ID"], item["Screen_ID"], item["Start_Time"])

def getUserbyID(id):
    user= execute_query("SELECT * FROM Users where User_ID=%s;" % id, "GET")
    if user:
        item = user[0]
        return User(item["User_ID"], item["Username"], item["Password"])

def getUserByUsername(username):
    user = execute_query("SELECT * FROM Users where Username=%s;" % ("\"" + username + "\""), "GET")
    if user:
        item = user[0]
        return User(item["User_ID"], item["Username"], item["Password"])

def addUser(username, password):
    conn = get_db()
    c = conn.cursor()
    max_id_list = c.execute("SELECT Max(User_ID) from Users;").fetchall()
    max_id_list2 = max_id_list[0]
    number_of_rows = max_id_list2[0]
    query = "INSERT INTO Users VALUES(" + str(number_of_rows + 1) + ", " + "\"" + username + "\"" + "," + "\"" + password + "\"" + ");"
    execute_query(query, "POST")

def getBookingbyID(id):
    booking= execute_query("SELECT * FROM Bookings where Screening_ID=%s;" % id, "GET")
    bookings = []
    for item in booking:
        bookings.append(Booking(item["Screening_ID"], item["Row_Num"], item["Column_Num"]))
    return bookings

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


@app.route('/')
def index():
    number = []
    #input from screening table
    movies = getMovies()
    whatsons = []
    for m in movies:
        whatsons.append((m,getWhatsOnByMovieID(m.Movie_ID)))

    return render_template('index.html',whatsons=whatsons);

@app.route('/seatselect/<id>')
def tickets(id):
    seats = getBookingbyID(id)
    allSeats = []
    for i in range(1,6):
        for j in range(1,6):
            for seat in seats:
                if (int(seat.Column_Num) == i) and (int(seat.Row_Num) == j):
                    allSeats.append((i,j,True))
                else:
                    allSeats.append((i,j,False))



    return render_template('seatselect.html', allSeats = allSeats)

@app.route('/login')
def login():
    return render_template('login.html')

@app.route('/loginrequest', methods=['POST'])
def loginRequest():
    username = request.form.get('Username')
    password = request.form.get('Password')

    user = getUserByUsername(username)

    if(user):
        if(user.Password == password):
            return redirect('/')
        else:
            return "", 204
    else:
        return "", 204


@app.route('/register')
def register():
    return render_template('register.html')

@app.route('/registerrequest', methods =['POST'])
def registerRequest():
    username = request.form.get('Username')
    password = request.form.get('Password')

    app.logger.info(username)
    app.logger.info(password)

    addUser(username, password)

    return "", 204