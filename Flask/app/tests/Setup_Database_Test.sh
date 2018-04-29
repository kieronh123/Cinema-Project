#!/bin/sh

cd ../database
rm cinema.db
python create_database.py
python fill_database_test.py
cd ../../..
