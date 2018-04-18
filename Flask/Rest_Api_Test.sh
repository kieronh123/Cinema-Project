#!/bin/bash

echo $'\n\nGetting all users'
curl -X GET localhost:5000/users/
echo $'\n\nGetting specific user'
curl -X GET localhost:5000/users/1
echo $'\n\nAdding a user'
curl -X POST localhost:5000/users/ -d "data='danny123@hotmail.co.uk', 'hunter2'"
echo $'\n\nRemoving a user'
curl -X DELETE localhost:5000/users/1

echo $'\n\nGetting all movies'
curl -X GET localhost:5000/movies/
echo $'\n\nAdding a movie'
curl -X POST localhost:5000/movies/ -d "data='Batman Begins', '18+', 7200, 'Rich man beats up mentally ill', 'BATMAN.jpg'"
echo $'\n\nGetting specific movie'
curl -X GET localhost:5000/movies/1
echo $'\n\nRemoving a movie'
curl -X DELETE localhost:5000/movies/1

echo $'\n\nGetting all bookings'
curl -X GET localhost:5000/bookings/
echo $'\n\nAdding a bookings'
curl -X POST localhost:5000/bookings/ -d "data=0, 1"
echo $'\n\nGetting specific bookings'
curl -X GET localhost:5000/bookings/1
echo $'\n\nRemoving a booking'
curl -X DELETE localhost:5000/bookings/1

echo $'\n\nGetting all Whats_On'
curl -X GET localhost:5000/whatson/
echo $'\n\nAdding a whatson'
curl -X POST localhost:5000/whatson/ -d "data=1, 2, '1996-09-29'"
echo $'\n\nGetting specific whatson'
curl -X GET localhost:5000/whatson/1
echo $'\n\nRemoving a whatson'
curl -X DELETE localhost:5000/whatson/1


echo $'\n---END OF TESTS---\n'
