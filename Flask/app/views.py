from app import app
from datetime import datetime
from datetime import timedelta
import sqlite3
import hashlib
import re
import os
from flask import render_template, g, redirect, request, make_response

import qrcode
from PIL import Image as pimg

from .models import WhatsOn, Movie, Booking, User, CardDetails

import json

import time

import smtplib
import imghdr
from email.message import EmailMessage

DATABASE = 'app/database/cinema.db'

##Function to email ticket to user
#Parameters: email_address - the email address that we are sending to
#            file_name - the file name of the stored qr code to be sent
#            name - the name of the reciever of the email.
#Return: status code
def send_ticket(email_address, file_name, name):
    #Fetch and parse data sent by POST request

    #Forming filename
    split_string = file_name.split("_")
    screening_id_for_api = int(split_string[0])
    info_for_email = getWhatsOnbyID(screening_id_for_api)
    print(info_for_email)
    movie_of_purchase = getMoviebyID(int(info_for_email.Movie_ID))
    date_and_time = info_for_email.Start_Time.split("T")

    #Constructing basics of email
    msg = EmailMessage()
    msg['Subject'] = "Your Team Quail Cinema Ticket"
    msg['From'] = "Team_Quail"
    msg['To'] = email_address
    msg.set_content("Hi "+name+" , \n You are seeing: "+ movie_of_purchase.Movie_Name+"\n on: "+str(date_and_time[0])+"\n at "+str(date_and_time[1])+ "\n in screen: "+ str(info_for_email.Screen_ID)+ "\n in seat: Row "+str(seat[1])+
                    " Column "+str(seat[4])+"\n Your ticket is attatched below,\n Thank you for using Quail Cinemas.")
    # Open the new image to send
    cwd = os.getcwd()
    with open(cwd + "/app/static/qr_codes/" + file_name +".png", 'rb') as fp:
        img_data = fp.read()
        msg.add_attachment(img_data, maintype='image', subtype=imghdr.what(None, img_data))

    # Send the email via our own SMTP server.
    with smtplib.SMTP('localhost') as s:
        s.send_message(msg)
    return "{Status: 200}"

##Function to execute an SQL query
##Parameters: query - the sql query
#             method - the type of request e.g. get, post etc.
#Return: status code
def execute_query(query, method):
    #open connection
    conn = get_db()
    c = conn.cursor()
    try:
        if method == 'GET':
            c.execute(query)
            r = [dict((c.description[i][0], value)
                      for i, value in enumerate(row)) for row in c.fetchall()]
            return r
        elif method == 'POST' or method == 'DELETE':
            c.execute(query)
            conn.commit()
            conn.close()
            return "{Status: 200}"
    except sqlite3.IntegrityError:
        return "{Status:400}"

##function to get all the movies in the database
## returns a list of each movie and it's attributes
#Return: array of movies
def getMovies():
    movies_db = execute_query("SELECT * FROM MOVIES;", "GET")
    movies = []
    for item in movies_db:
        movies.append(
            Movie(item["Movie_ID"], item["Movie_Name"], item["Movie_Rating"], item["Movie_Runtime"], item["Movie_Info"],
                  item["Movie_Image"], item["Movie_Director"], item["Movie_Actors"]))

    return movies

##Function to get a specific movie by it's ID
##Parameters: id - the movie ID
def getMoviebyID(id):
    movie = execute_query("SELECT * FROM MOVIES where Movie_ID=%s" % id, "GET")
    if movie:
        item = movie[0]
        return Movie(item["Movie_ID"], item["Movie_Name"], item["Movie_Rating"], item["Movie_Runtime"],
                     item["Movie_Info"], item["Movie_Image"], item["Movie_Director"], item["Movie_Actors"])

##Function to get all of the whats on table
#Return: array of whats on's
def getWhatsOn():
    screenings = execute_query("SELECT * FROM Whats_On;", "GET")
    whatson = []

    for idScreening in screenings:
        whatson.append(getWhatsOnbyID(idScreening["Screening_ID"]))

    return whatson

##Get a specific whats on based on a given movie ID
##Parameters: id - the movie ID
##Return: whatson - a specific whats on in the table
def getWhatsOnByMovieID(id):
    screenings = execute_query("SELECT * FROM Whats_On where Movie_ID=%s;" % id, "GET")
    whatson = []

    for idScreening in screenings:
        whatson.append(getWhatsOnbyID(idScreening["Screening_ID"]))
    return whatson


##Get a specific whats on via it's screening ID
##parameters: id - the screening ID
##Return: specific whatson
def getWhatsOnbyID(id):
    whatson = execute_query("SELECT * FROM Whats_On where Screening_ID=%s;" % id, "GET")
    if whatson:
        item = whatson[0]
        return WhatsOn(item["Screening_ID"], item["Movie_ID"], item["Screen_ID"], item["Start_Time"])

##Get a specific user by their ID
##Parameters: id - the users ID
##Return: user - the specific users information from the database
def getUserbyID(id):
    user = execute_query("SELECT * FROM Users where User_ID=%s;" % id, "GET")
    if user:
        item = user[0]
        return User(item["User_ID"], item["Username"], item["Password"])

##Get a users infomration by their username
##Parameters: Username - the username of the user
##Return: specific users information
def getUserByUsername(username):
    user = execute_query("SELECT * FROM Users where Username=%s;" % ("\"" + username + "\""), "GET")
    if user:
        item = user[0]
        return User(item["User_ID"], item["Username"], item["Password"])

##Add a user to the database, with their username (email) and password
##Parameters: username - the username
#            password - the users password
def addUser(username, password):
    conn = get_db()
    c = conn.cursor()
    max_id_list = c.execute("SELECT Max(User_ID) from Users;").fetchall()
    max_id_list2 = max_id_list[0]
    number_of_rows = max_id_list2[0]
    query = "INSERT INTO Users VALUES(" + str(
        number_of_rows + 1) + ", " + "\"" + username + "\"" + "," + "\"" + password + "\"" + ");"
    execute_query(query, "POST")

##Function to save a users card details.
##parameters: user_id - the id of the user
#             name - the name of the user
#             cardNumber - the card number
#             sortCode - the expiryDate
#             securityCode - the three digit security code

def saveCardDetails(user_id, name, cardNumber, sortCode, securityCode):
    conn = get_db()
    c = conn.cursor()
    query = "INSERT INTO Card_Details VALUES(" + str(user_id) + ", '" + str(name) + "','" + encyptInt(cardNumber) + "', '" + str(sortCode) + "', '" + encyptInt(securityCode) + "');"
    execute_query(query, "POST")

##Function to get a users card details based on their ID
##Parameters: id - the user ID
##return - specific users card details from database
def getCardDetails(id):
    carddetail = execute_query("SELECT * FROM Card_Details where User_ID=%s;" % id, "GET")
    carddetails = []
    for item in carddetail:
        carddetails.append(CardDetails(item["User_ID"], item["Card_Name"], decryptInt(item["Card_Number"]), item["Card_SortCode"], decryptInt(item["Card_SecurityCode"])))
    return carddetails

#Simple caesarcypher
##Parameters: string - the int to be converted to string, then encrypted
##return: cypher
def encyptInt(string):
    string = str(string)
    cypher = ""
    for c in string:
        cypher = cypher + str(chr(int(c) + 65))
    return cypher

##Simple decryption
##Parameters: String - the string to be decrypted
#Return: the decyphered string
def decryptInt(string):
    decypher = ""
    for c in string:
        decypher = decypher + str((ord(c) - 65))
    return decypher


##Get a specific Screening (booking) by screening ID
##Parameters: id - the screening ID
##return: all of the bookings that are in the specified screening ID
def getBookingbyID(id):
    booking = execute_query("SELECT * FROM Bookings where Screening_ID=%s;" % id, "GET")
    bookings = []
    for item in booking:
        bookings.append(Booking(item["Screening_ID"], item["Row_Num"], item["Column_Num"]))
    return bookings

##Function to add a booking, takes in the screening, row and column
##Parameters: screening - the screening ID
#             row - the row of the seat booked
#             column - the column of the seat booked
def addBooking(screening, row, column):
    conn = get_db()
    c = conn.cursor()
    print(str(row))
    print(str(column))
    query = "INSERT INTO Bookings VALUES(" + str(screening) + ", " + "\"" + str(row) + "\"" + "," + "\"" + str(
        column) + "\"" + ");"
    execute_query(query, "POST")

##Get the database
#return - the databse
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

# Global variables
seat = (7, 7)
vip = False
bookingID = 0
row = 7
column = 7
LOGIN = False
REGISTER = False
USER =""
USER_ID = 0


##Function to launch index.html page of website.
##This is the first page containing the list of movies on today.
##return: the index page with today
@app.route('/')
def index():
    #Use today's date to get the next week of days.
    now = datetime.now()
    days = []
    days.append(("Today", now))
    for i in range(1, 7):
        date = now + timedelta(days=i)
        #Convert date time object to string of the day.
        day = date.strftime("%A")
        days.append((day, date))

    #Get all available movies.
    movies = getMovies()
    whatsons = []
    present = False
    #Get all screenings of each movie that are playing in the future from the current time.
    for m in movies:
        futureWhatsOn = []
        whatsOn = getWhatsOnByMovieID(m.Movie_ID)
        for w in whatsOn:
            #Convert string to date time object
            startTime = datetime.strptime(w.Start_Time, "%Y-%m-%dT%H:%M:%S")
            #Only append the movies after the current time, do not want ones in the past today
            if (startTime > now):
                present = True
                futureWhatsOn.append(w)
        #If the movie has viewings in the future append to whatsons.
        if (present):
            whatsons.append((m, futureWhatsOn))
    #Display index.html page with the days of the upcoming week and the current date.
    return render_template('index.html', whatsons=whatsons, daysOfWeek=days, msg="Today",
                           date=now.strftime("%Y-%m-%d %H:%M:%S"));

##Function to display web page with movies on the selected day from nav bar
##parameters: choiceDay - the day that the user clicked on
#             choiceDate - the date corresponding to the day the user chose.
##return: the index page with the movies for the selected day.
@app.route('/day/<choiceDay>/<choiceDate>')
def day(choiceDay, choiceDate):
    #Use today's date to get the next week of days.
    now = datetime.now()
    days = []
    days.append(("Today", now))
    for i in range(1, 7):
        date = now + timedelta(days=i)
        day = date.strftime("%A")
        days.append((day,date))

    for d in days:
        #If the day chosen is equal to one of the days available set the date.
        if (choiceDay == d[0]):
            chosenDate = d[1]

    #Get all movies
    movies = getMovies()
    whatsons = []
    present=False
    #Append all screenings of each movie
    for m in movies:
        whatsOn = getWhatsOnByMovieID(m.Movie_ID)
        whatsons.append((m, whatsOn))
    #Display index.html page with the days of the upcoming week and the current date.
    return render_template('index.html', whatsons=whatsons, msg=choiceDay, daysOfWeek=days, date=chosenDate.strftime("%Y-%m-%d %H:%M:%S"));

##Function to display seats layout with seats available and not available on the selected Screening
##parameters: id- the screening id of the timing of the movie they selected.
##return: The seats page with the layout of available/booked seats
@app.route('/seatselect/<id>')
def tickets(id):
    #Set the booking id to the screening id selected
    global bookingID
    bookingID = id
    #If the person is not logged in/has not joined they can not book a seat
    if(LOGIN == False):
        #Redirect to the login page and ask them to login or join
        return render_template('login.html', msg=None, header=True)

    #If they are logged in
    else:
        #Get all seats booked in the cinema
        seats = getBookingbyID(id)
        allSeats = []
        #If there are no seats booked
        if not seats:
            #5 rows
            for i in range(1, 6):
                #5 columns
                for j in range(1, 6):
                    #Create all 25 seats with False value for booked
                    allSeats.append((i, j, False, (i, j)))
        #If there are some seats booked
        else:
            #5 rows
            for i in range(1, 6):
                #5 columns
                for j in range(1, 6):
                    booked = False
                    #For each booked seat
                    for seat in seats:
                        #Check if the row and column number match the seat being created and if it is the same screening id
                        if ((int(seat.Row_Num) == i) and (int(seat.Column_Num) == j) and (int(seat.Screening_ID) == int(id))):
                            #Add this seat with value True for the booked element
                            allSeats.append((i, j, True, (i, j)))
                            booked = True
                    #If the current seat being generated was not booked
                    if booked == False:
                        #Add this seat with value False for the booked element
                        allSeats.append((i, j, False, (i, j)))
        #Display seat select page with the seats array of available/booked seats
        return render_template('seatselect.html', allSeats=allSeats)

##Function to store the seat the user has selected
##This is updated whenever the user clicks a button so they can change their mind.
##parameters: seatSelected - the seat the user chose
#             Row - the row the user chose to sit on
#             Column - the column the user chose to sit on
##return: the same page
@app.route('/storeSeat/<seatSelected>/<Row>/<Column>')
def storeSeats(seatSelected, Row, Column):
    #Set global variables of seat, row and column
    global seat
    seat = seatSelected
    global row
    row = Row
    global column
    column = Column
    #If the user selects a VIP seat
    if int(Row) == 3:
        #Set global variable of vip to true
        global vip
        vip = True
    #Return to same page in case they want to change the seat
    return "", 204

##Function to go to login page
##return: either the login page or the error page
@app.route('/login')
def login():
    global LOGIN
    #If the user is not already logged in/joined
    if(LOGIN == False):
        #Display login page as is
        return render_template('login.html', msg=None, header=False)
    #If the user has already logged in
    else:
        #Display error message telling them they have already logged in
        return render_template('error.html', error="ALREADY LOGGED IN")

##Function to go to log the person out
##return: the login page
@app.route('/logout')
def logout():
    #Set the global login variable to false and return them to login page
    global LOGIN
    LOGIN = False
    return render_template('login.html', msg=None, header=False)

##Function to check if someone can be logged in
##return: the index page if successful or the login page if there is an error
@app.route('/loginrequest', methods=['POST'])
def loginRequest():
    # Get the details from the form
    username = request.form.get('Username')
    password = request.form.get('Password')
    password = password + "saltyquail"
    
    if type(password) == str:
        password = str.encode(password)
    passwordHashed = hashlib.sha256()
    passwordHashed.update(password)

    user = getUserByUsername(username)

    # Check they are the details of a known user
    if (user):
        global USER
        USER = username
        global USER_ID
        USER_ID = user.User_ID
        if (user.Password == passwordHashed.hexdigest()):
            global LOGIN
            LOGIN = True
            return redirect('/')
        else:
            return render_template('login.html', msg="Incorrect Username/Password combination", header=False)
    else:
        return render_template('login.html', msg="Username not recognised", header=False)

@app.route('/register')
def register():
    global LOGIN
    if(LOGIN == False):
        return render_template('register.html', msg=None)
    else:
        return render_template('error.html', error="ALREADY LOGGED IN")

@app.route('/registerrequest', methods=['POST'])
def registerRequest():
    # Get the login details from the form
    username = request.form.get('Username')
    password = request.form.get('Password')
    passwordConfirm = request.form.get('Confirm Password')

    # Check if the details are valid
    if username == "":
        return render_template('register.html', msg="No username entered")

    if password != passwordConfirm:
        return render_template('register.html', msg="Passwords do not match")

    # Salt and hash the password
    password = password + "saltyquail"
    if type(password) == str:
        password = str.encode(password)
    passwordHashed = hashlib.sha256()
    passwordHashed.update(password)
    global LOGIN
    LOGIN = True
    global REGISTER
    REGISTER = True
    global USER
    USER = username
    # Add the details to the database
    addUser(username, passwordHashed.hexdigest())

    return render_template('register.html', msg="Registration Successful")

@app.route('/payment', methods=['POST'])
def paymentNoType():
    # Get the ticket type from the drop down box
    ticketType = request.form.get("selectTicket")
    global seat
    if (bookingID != 0 and seat != (7, 7)):
        addBooking(bookingID, row, column)
    if (seat == (7, 7)):
        return "", 204
    else:
        return redirect('/payment/' + ticketType.lower())

@app.route('/payment/<ticketType>')
def payment(ticketType):
    # Set the price according to the ticket type
    if ticketType == "adult":
        price = 8
    elif ticketType == "child" or ticketType == "senior":
        price = 4
    if vip == True:
        price *= 1.5

    global REGISTER
    if(REGISTER == True):
        return render_template('payment.html', ticketType=ticketType.title(), price=price, msg=None, Login=False)
    else:
        global USER_ID
        card = getCardDetails(USER_ID)
        print(len(card))
        return render_template('payment.html', ticketType=ticketType.title(), price=price,
            msg=None, Login=True, Name=card[0].Card_Name, CardNumber=card[0].Card_Number, ExpiryDate =card[0].Card_SortCode, SecurityCode = card[0].Card_SecurityCode )

@app.route('/processPayment/<ticketType>/<price>', methods=['POST'])
def processPayment(ticketType, price):

    # Get card details from the form
    name = request.form.get('Name')
    cardNumber = request.form.get('Card Number')
    expiryDate = request.form.get('Expiry Date')
    securityCode = request.form.get('Security Code')

    # Check the validity of the card details and return an appropriate message if any are wrong
    if name != "":
        if cardNumber and len(cardNumber) <= 19:
            if re.match('[0-9]{2}/[0-9]{2}', expiryDate):
                if re.match('^[0-9]{3,4}$', securityCode):
                    img = qr_code(ticketType.title(), price, name)
                    global REGISTER
                    if REGISTER==True:
                        user = getUserByUsername(USER)
                        saveCardDetails(user.User_ID, name, cardNumber, expiryDate, securityCode)
                        REGISTER=False
                    return render_template('payment.html', ticketType=ticketType.title(), price=price,
                                           msg="Payment Confirmed, Your ticket will arrive via email shortly")
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

@app.route('/<ticketType>/<price>/<name>')
def qr_code(ticketType, price, name):
    global USER
    global seat
    img = qrcode.make(str(bookingID) + " " +str(seat) + " "+str(price))
    file_name = str(bookingID) +"_"+ seat[1] +"_"+ seat[4]
    img.save('app/static/qr_codes/'+file_name+'.png')
    img.close()
    send_ticket(USER, file_name, name)
    return img
