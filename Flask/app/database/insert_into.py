import sqlite3
db = sqlite3.connect('cinema.db')
cursor = db.cursor()
id1 = 1
username1 = 'calkey@hotmail.co.uk'
#password
password1 = '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8'

id2 = 2
username2 = 'miniman@gmail.com'
#hunter2
password2 = 'F52FBD32B2B3B86FF88EF6C490628285F482AF15DDCB29541F94BCF526A3F6C7'

movieid1 = 1
moviename1 = 'Batman Begins'
movierating1 = '12A'
movieruntime1 = 7200
movieinfo1 = 'Man scared of bats dresses up to beat up people'
movieimage1 = 'image_url1'

movieid2 = 2
moviename2 = 'Superman: Man of Steel'
movierating2 = '15'
movieruntime2 = 8400
movieinfo2 = 'Is it a bird, is it a plane? No its superman!'
movieimage2 = 'image_url2'

screenid1 = 1
column1 = 0
row1 = 1

column2 = 2
row2 = 2

wo_id = 1
wo_movie = 1
wo_screen = 2
wo_time = "1996-09-29T11:55:01"

b_id = 1
b_col = 0
b_row = 0


cursor.execute('''INSERT INTO Users(User_ID,Username,Password)
                VALUES(?,?,?)''', (id1,username1,password1))

cursor.execute('''INSERT INTO Users(User_ID,Username,Password)
                VALUES(?,?,?)''', (id2,username2,password2))


cursor.execute('''INSERT INTO Movies(Movie_ID,Movie_Name,Movie_Rating,Movie_Runtime,
                                        Movie_Info,Movie_Image)
                                    VALUES(?,?,?,?,?,?)''',(movieid1,moviename1,
                                    movierating1,movieruntime1,movieinfo1,movieimage1))

cursor.execute('''INSERT INTO Movies(Movie_ID,Movie_Name,Movie_Rating,Movie_Runtime,
                                        Movie_Info,Movie_Image)
                                    VALUES(?,?,?,?,?,?)''',(movieid2,moviename2,
                                    movierating2,movieruntime2,movieinfo2,movieimage2))

cursor.execute('''INSERT INTO Whats_On(Screening_ID, Movie_ID, Screen_ID, Start_Time)
                                    Values(?,?,?,?)''',(wo_id, wo_movie, wo_screen, wo_time))

cursor.execute('''INSERT INTO Bookings(Screening_ID, Row_Num, Column_Num)
                                    VALUES(?,?,?)''', (b_id, b_row, b_col))

db.commit()

db.close()
