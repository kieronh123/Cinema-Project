from app import app
import sqlite3
from flask import render_template, g

from .models import WhatsOn, Movie

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


def getMoviebyID(id):
    movie = execute_query("SELECT * FROM MOVIES where Movie_ID=%s" % id, "GET")
    if movie:
        item = movie[0]
        return Movie(item["Movie_ID"], item["Movie_Name"], item["Movie_Rating"],item["Movie_Runtime"],item["Movie_Info"], item["Movie_Image"])


def getMovies():
    screenings = execute_query("SELECT * FROM Whats_On;", "GET")
    movies = []

    for idScreening in screenings:
        movies.append(getMoviebyID(idScreening["Movie_ID"]))

    return movies

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

def getBookingbyID(id):
    booking= execute_query("SELECT * FROM Bookings where Screening_ID=%s;" % id, "GET")
    if booking:
        item = booking[0]
        return Booking(item["Screening_ID"], item["Row_Num"], item["Column_Num"])

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
    print("HERE")
    number = []
    #input from screening table

    movies = getMovies()
    print(movies)
    screenings = []

    for screening in movie:
        print(screening)


    #screenings.append();
    #for i in range(0,len(screenings)):
        #Call movie information
        #movie = []
        #movie.append()
        #screenings.append(movie)

    #return render_template('index.html');
    return render_template('index_old.html', number=whatson, smell=False);

@app.route('/dank')
def index2():
    return render_template('index_old.html', msg=execute_query("SELECT * FROM Users;", "GET"), smell=True);

@app.route('/tickets')
def tickets():
    return render_template('tickets.html');
