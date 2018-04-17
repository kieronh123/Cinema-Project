#!/bin/sh

cd Flask/app/database
rm cinema.db
python script.py
python insert_into.py
