#!/bin/sh

cd Flask/app/database
rm cinema.db
python create_database.py
python fill_database.py
cd ../../..
