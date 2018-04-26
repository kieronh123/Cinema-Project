#!/bin/sh

cd Flask
rm -r flask
virtualenv flask
cd flask
source bin/activate
cd ../app
echo $'Installing libraries from requirements.txt (this may take a minute!)'
pip install -q -r requirements.txt
module add anaconda3
cd ..
cp -r ../lib/geckodriver flask/bin
python3 run.py
