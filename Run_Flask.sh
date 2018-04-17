#!/bin/sh

cd Flask
rm -r flask
virtualenv flask
cd flask
source bin/activate
cd ../app
pip install -r requirements.txt
python run.py
