#!/bin/sh

cd Flask
rm -r flask
virtualenv flask
cd flask
module add anaconda3
source bin/activate
cd ../app
echo $'Installing libraries from requirements.txt (this may take a minute!)'
pip install --user -q -r requirements.txt
cd ..
cp ../lib/geckodriver flask/bin
python3 run.py
