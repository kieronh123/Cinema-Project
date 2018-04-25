#!/bin/sh

cd Flask
rm -r flask
virtualenv flask
cd flask
source bin/activate
cd ../app
module add anaconda3
echo $'Installing libraries from requirements.txt (this may take a minute!)'
pip install -q -r requirements.txt
cd ..
python3 run.py
