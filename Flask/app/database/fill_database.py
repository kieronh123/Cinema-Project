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

movie_Id3 = 3
movie_Name3 = 'Frozen'
movie_Rating3 = 'U'
movie_Runtime3 = 7200
movie_Info3 = 'Princess with hypothermia estranges her family'
movie_Image3 = 'image_url3'


##Initial whats on movie information
#Information for movie 1 on one day
wo_Id = 1
wo_Movie = 1
wo_Screen = 1
#Time must be in format 'YYYY-MM-DD:HH:MM:SS'
wo_Time = "2018-04-26T11:55:00"

wo_Id2 = 2
wo_Movie2 = 1
wo_Screen2 = 1
wo_Time2 = "2018-04-26T14:00:00"

wo_Id3 = 3
wo_Movie3 = 1
wo_Screen3 = 1
wo_Time3 = "2018-04-26T16:30:00"
#Day 2
wo_Id4 = 4
wo_Movie4 = 1
wo_Screen4 = 1
#Time must be in format 'YYYY-MM-DD:HH:MM:SS'
wo_Time4 = "2018-04-27T11:55:00"

wo_Id5 = 5
wo_Movie5 = 1
wo_Screen5 = 1
wo_Time5 = "2018-04-27T14:00:00"

wo_Id6 = 6
wo_Movie6 = 1
wo_Screen6 = 1
wo_Time6 = "2018-04-27T16:30:00"
#Movie 2
#Information for movie 2 on one day
wo_Id7 = 7
wo_Movie7 = 2
wo_Screen7 = 3
#Time must be in format 'YYYY-MM-DD:HH:MM:SS'
wo_Time7 = "2018-04-26T09:00:00"

wo_Id8 = 8
wo_Movie8 = 2
wo_Screen8 = 3
wo_Time8 = "2018-04-26T12:00:00"

wo_Id9 =9
wo_Movie9 = 2
wo_Screen9 = 3
wo_Time9 = "2018-04-26T15:30:00"
#Day 2
wo_Id10 = 10
wo_Movie10 = 2
wo_Screen10 = 2
#Time must be in format 'YYYY-MM-DD:HH:MM:SS'
wo_Time10 = "2018-04-27T10:30:00"

wo_Id11 = 11
wo_Movie11 = 2
wo_Screen11 = 2
wo_Time11 = "2018-04-27T13:00:00"

wo_Id12 = 12
wo_Movie12 = 2
wo_Screen12 = 2
wo_Time12 = "2018-04-27T16:30:00"

#Movie 3
#Information for movie 3 on one day
wo_Id13 = 13
wo_Movie13 = 3
wo_Screen13 = 4
#Time must be in format 'YYYY-MM-DD:HH:MM:SS'
wo_Time13 = "2018-04-26T09:00:00"

wo_Id14 = 14
wo_Movie14 = 3
wo_Screen14 = 4
wo_Time14 = "2018-04-26T12:00:00"

wo_Id15 =15
wo_Movie15 = 3
wo_Screen15 = 4
wo_Time15 = "2018-04-26T15:30:00"
#Day 2
wo_Id16 = 16
wo_Movie16 = 3
wo_Screen16 = 5
#Time must be in format 'YYYY-MM-DD:HH:MM:SS'
wo_Time16 = "2018-04-27T10:30:00"

wo_Id17 = 17
wo_Movie17 = 3
wo_Screen17 = 4
wo_Time17 = "2018-04-27T13:15:00"

wo_Id18 = 18
wo_Movie18 = 3
wo_Screen18 = 4
wo_Time18 = "2018-04-27T17:45:00"


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

cursor.execute('''INSERT INTO Movies(Movie_ID,Movie_Name,Movie_Rating,Movie_Runtime,
                                        Movie_Info,Movie_Image)
                                    VALUES(?,?,?,?,?,?)''',(movie_Id3,movie_Name3,
                                    movie_Rating3,movie_Runtime3,movie_Info3,movie_Image3))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id, wo_Movie, wo_Screen, wo_Time))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id2, wo_Movie2, wo_Screen2, wo_Time2))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id3, wo_Movie3, wo_Screen3, wo_Time3))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id4, wo_Movie4, wo_Screen4, wo_Time4))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id5, wo_Movie5, wo_Screen5, wo_Time5))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id6, wo_Movie6, wo_Screen6, wo_Time6))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id7, wo_Movie7, wo_Screen7, wo_Time7))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id8, wo_Movie8, wo_Screen8, wo_Time8))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id9, wo_Movie9, wo_Screen9, wo_Time9))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id10, wo_Movie10, wo_Screen10, wo_Time10))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id11, wo_Movie11, wo_Screen11, wo_Time11))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id12, wo_Movie12, wo_Screen12, wo_Time12))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id13, wo_Movie13, wo_Screen13, wo_Time13))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id14, wo_Movie14, wo_Screen14, wo_Time14))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id15, wo_Movie15, wo_Screen15, wo_Time15))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id16, wo_Movie16, wo_Screen16, wo_Time16))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id17, wo_Movie17, wo_Screen17, wo_Time17))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_Id18, wo_Movie18, wo_Screen18, wo_Time18))

cursor.execute('''INSERT INTO Bookings(Screening_ID, Row_Num, Column_Num)
                                    VALUES(?,?,?)''', (b_Id, b_Row, b_Col))

#Committing changes to cinema.db
db.commit()
db.close()
