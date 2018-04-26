from app import app
import sqlite3
import hashlib
import re
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
            # json_output = json.dumps(r)
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
        movies.append(
            Movie(item["Movie_ID"], item["Movie_Name"], item["Movie_Rating"], item["Movie_Runtime"], item["Movie_Info"],
                  item["Movie_Image"], item["Movie_Director"], item["Movie_Actors"]))

    return movies

def getMoviebyID(id):
    movie = execute_query("SELECT * FROM MOVIES where Movie_ID=%s" % id, "GET")
    if movie:
        item = movie[0]
        return Movie(item["Movie_ID"], item["Movie_Name"], item["Movie_Rating"], item["Movie_Runtime"],
                     item["Movie_Info"], item["Movie_Image"])


def getWhatsOn():
    screenings = execute_query("SELECT * FROM Whats_On;", "GET")
    whatson = []

    for idScreening in screenings:
        whatson.append(getWhatsOnbyID(idScreening["Screening_ID"]))

    return whatson


def getWhatsOnByMovieID(id):
    screenings = execute_query("SELECT * FROM Whats_On where Movie_ID=%s;" % id, "GET")
    whatson = []

    for idScreening in screenings:
        whatson.append(getWhatsOnbyID(idScreening["Screening_ID"]))
    return whatson


def getWhatsOnbyID(id):
    whatson = execute_query("SELECT * FROM Whats_On where Screening_ID=%s;" % id, "GET")
    if whatson:
        item = whatson[0]
        return WhatsOn(item["Screening_ID"], item["Movie_ID"], item["Screen_ID"], item["Start_Time"])


def getUserbyID(id):
    user = execute_query("SELECT * FROM Users where User_ID=%s;" % id, "GET")
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
    query = "INSERT INTO Users VALUES(" + str(
        number_of_rows + 1) + ", " + "\"" + username + "\"" + "," + "\"" + password + "\"" + ");"
    execute_query(query, "POST")


def getBookingbyID(id):
    booking = execute_query("SELECT * FROM Bookings where Screening_ID=%s;" % id, "GET")
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


#####################################################################################################################

@app.route('/')
def index():
    number = []
    # input from screening table
    movies = getMovies()
    whatsons = []
    for m in movies:
        whatsons.append((m, getWhatsOnByMovieID(m.Movie_ID)))

    return render_template('index.html', whatsons=whatsons);


@app.route('/seatselect/<id>')
def tickets(id):
    seats = getBookingbyID(id)
    allSeats = []
    if not seats:
        for i in range(1, 6):
            for j in range(1, 6):
                allSeats.append((i, j, False, (i, j)))
    else:
        for i in range(1, 6):
            for j in range(1, 6):
                for seat in seats:
                    if (int(seat.Column_Num) == i) and (int(seat.Row_Num) == j):
                        allSeats.append((i, j, True, (i, j)))
                    else:
                        allSeats.append((i, j, False, (i, j)))

    return render_template('seatselect.html', allSeats=allSeats)


@app.route('/login')
def login():
    return render_template('login.html', msg=None)


@app.route('/loginrequest', methods=['POST'])
def loginRequest():
    username = request.form.get('Username')
    password = request.form.get('Password')
    password = password + "saltyquail"
    if type(password) == str:
        password = str.encode(password)
    passwordHashed = hashlib.sha256()
    passwordHashed.update(password)

    user = getUserByUsername(username)

    if (user):
        if (user.Password == passwordHashed.hexdigest()):
            return redirect('/')
        else:
            return render_template('login.html', msg="Incorrect Username/Password combination")
    else:
        return render_template('login.html', msg="Username not recognised")


@app.route('/register')
def register():
    return render_template('register.html', msg=None)


@app.route('/registerrequest', methods=['POST'])
def registerRequest():
    username = request.form.get('Username')
    password = request.form.get('Password')
    passwordConfirm = request.form.get('Confirm Password')

    if username == "":
        return render_template('register.html', msg="No username entered")

    if password != passwordConfirm:
        return render_template('register.html', msg="Passwords do not match")

    password = password + "saltyquail"
    if type(password) == str:
        password = str.encode(password)
    passwordHashed = hashlib.sha256()
    passwordHashed.update(password)

    addUser(username, passwordHashed.hexdigest())

    return render_template('register.html', msg="Registration Successful")


@app.route('/payment', methods=['POST'])
def paymentNoType():
    ticketType = request.form.get("selectTicket")
    return redirect('/payment/' + ticketType.lower())


@app.route('/payment/<ticketType>')
def payment(ticketType):
    if ticketType == "adult":
        price = 8
    elif ticketType == "child" or ticketType == "senior":
        price = 5

    return render_template('payment.html', ticketType=ticketType.title(), price=price, msg=None)


@app.route('/processPayment/<ticketType>/<price>', methods=['POST'])
def processPayment(ticketType, price):
    name = request.form.get('Name')
    cardNumber = request.form.get('Card Number')
    expiryDate = request.form.get('Expiry Date')
    securityCode = request.form.get('Security Code')

    if name != "":
        if cardNumber and len(cardNumber) <= 19:
            if re.match('[0-9]{2}/[0-9]{2}', expiryDate):
                if re.match('^[0-9]{3,4}$', securityCode):
                    return render_template('payment.html', ticketType=ticketType.title(), price=price,
                                           msg="Payment Confirmed")
                else:
                    return render_template('payment.html', ticketType=ticketType.title(), price=price,
                                           msg="Security code was not in the correct format")
            else:
                return render_template('payment.html', ticketType=ticketType.title(), price=price,
                                       msg=" Expiry date was not in the correct format")
        else:
            return render_template('payment.html', ticketType=ticketType.title(), price=price,
                                   msg="Card number must be less than 19 digits")
    else:
        return render_template('payment.html', ticketType=ticketType.title(), price=price, msg="A name must be entered")