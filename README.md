
# Generic Build and Test Instructions

## Setup

To setup, pull the GitLab files from

	git@gitlab.com:comp2931/18/quail.git

and change directory to quail/ using:

	cd quail

## Build

### Setting up Flask and database

In order to setup the Flask environment run

	source Run_Flask.sh

The flask environment will then proceed to setup a virtual environment, install the required libraries, set it up in anaconda3 to run in python3 and finally run the Flask application. If an error occurs regarding the absence of 'qr_code', run the script again in the flask virtual environment and this problem will no longer occur.

The database will then need to be created in order to be accessed by the Tills system and the website. Open up a new terminal and type for the production database:

	source Setup_Database.sh

or for the test database type:

	cd Flask/app/tests
	source Setup_Database_Test.sh
	cd ../../..

This will delete any existing database and recreate it filling it in with the correct data.

### Setting up till system

To build and run the Tills system back in the quail/ directory and with flask running, type:

	ant testTills

This will run the tills system.

### Accessing the website

To run the Website enter localhost:5000/ as the URL in a browser with flask running.

## Testing

### Testing Till System

In order to run the Java testing, in the quail directory, run on a fresh database with a test folder to hold the .class files:
	
	mkdir testBin
	ant runTests

Please ensure that your machine is not running any background programs, as this will slow down the running of the Till System, resulting in the robot being too quick.

Please be aware than the Robot Java test will return as a failure, due to the fact that it is merely testing UI by moving the mouse and clicking and not testing data itself.

### Testing Website

In order to test the website, navigate to the tests directory and run the Flask virtual environment:

	module add anaconda3
	source Flask/flask/bin/activate

Then run the corresponding tests in the flask virtual environment

	cd Flask/app/tests
	./runTests.sh

	