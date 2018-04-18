from app import app

from flask import render_template

@app.route('/')
def index():

    number = []
    for i in range(0,10):
        number.append(i)
    return render_template('index.html');
    #return render_template('index.html',msg="Hello Katie",number=number, smell=False);


@app.route('/tickets')
def tickets():
    return render_template('tickets.html');
