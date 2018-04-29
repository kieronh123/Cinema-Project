#!/bin/bash

cd ..
source Setup_Database.sh
cd Tests
javac RobotTest.java
java RobotTest
