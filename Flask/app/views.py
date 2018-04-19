from app import app

from flask import render_template

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


@app.route('/tickets')
def tickets():
    return render_template('tickets.html');
