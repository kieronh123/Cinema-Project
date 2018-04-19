from app import app
import sqlite3
from flask import render_template, g

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
            return r
        elif method == 'POST' or method == 'DELETE':
            c.execute(query)
            conn.commit()
            return "Action successful"
    except sqlite3.IntegrityError:
        return "ERROR"

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
    screenings = []

    #screenings.append();
    #for i in range(0,len(screenings)):
        #Call movie information
        #movie = []
        #movie.append()
        #screenings.append(movie)
    for i in range(0,10):
        number.append(i)
    #return render_template('index.html');
    return render_template('index_old.html',msg="Hello Katie",number=number, smell=False);

@app.route('/dank')
def index2():
    return render_template('index_old.html', msg=execute_query("SELECT * FROM Users;", "GET"), smell=True);

@app.route('/tickets')
def tickets():
    return render_template('tickets.html');
