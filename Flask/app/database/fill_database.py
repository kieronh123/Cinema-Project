#Alexander Hoare
#Placing start information into database

#Importing sqlite3
import sqlite3

#Connecting to database
db = sqlite3.connect('cinema.db')
cursor = db.cursor()

##Initial user information
id1 = 1
username1 = 'kieron.hushon@gmail.com'
#SHA256 hash of 'password'
password1 = '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8'

id2 = 2
username2 = 'alexander_hoare@homail.co.uk'
#SHA256 hash of 'hunter2'
password2 = 'F52FBD32B2B3B86FF88EF6C490628285F482AF15DDCB29541F94BCF526A3F6C7'

##Initial movie information
movie_Id1 = 1
movie_Name1 = 'Batman Begins'
movie_Rating1 = '12A'
movie_Runtime1 = 7200
movie_Info1 = 'Man scared of bats dresses up to beat up people'
movie_Image1 = 'image_url1'

movie_Id2 = 2
movie_Name2 = 'Superman: Man of Steel'
movie_Rating2 = '15'
movie_Runtime2 = 8400
movie_Info2 = 'Is it a bird, is it a plane? No its Superman!'
movie_Image2 = 'image_url2'

##Initial whats on movie information
wo_Id = 1
wo_Movie = 1
wo_Screen = 2
#Time must be in format 'YYYY-MM-DD:HH:MM:SS'
wo_Time = "1996-09-29T11:55:00"

wo_Id2 = 2
wo_Movie2 = 2
wo_Screen = 1
wo_Time2 = "1996-09-30T14:00:00"

##Initial bookings information
b_Id = 1
b_Col = 0 #Bookings column
b_Row = 0 #Bookings row

#Entering values into cinema.db tables
cursor.execute('''INSERT INTO Users(User_ID,Username,Password)
                VALUES(?,?,?)''', (id1,username1,password1))

cursor.execute('''INSERT INTO Users(User_ID,Username,Password)
                VALUES(?,?,?)''', (id2,username2,password2))

cursor.execute('''INSERT INTO Movies(Movie_ID,Movie_Name,Movie_Rating,Movie_Runtime,
                                        Movie_Info,Movie_Image)
                                    VALUES(?,?,?,?,?,?)''',(movie_Id1,movie_Name1,
                                    movie_Rating1,movie_Runtime1,movie_Info1,movie_Image1))

cursor.execute('''INSERT INTO Movies(Movie_ID,Movie_Name,Movie_Rating,Movie_Runtime,
                                        Movie_Info,Movie_Image)
                                    VALUES(?,?,?,?,?,?)''',(movie_Id2,movie_Name2,
                                    movie_Rating2,movie_Runtime2,movie_Info2,movie_Image2))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id, wo_Movie, wo_Screen, wo_Time))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id2, wo_Movie2, wo_Screen2, wo_Time2))

cursor.execute('''INSERT INTO Bookings(Screening_ID, Row_Num, Column_Num)
                                    VALUES(?,?,?)''', (b_Id, b_Row, b_Col))

#Committing changes to cinema.db
db.commit()
db.close()
