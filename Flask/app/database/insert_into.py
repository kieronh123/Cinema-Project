import sqlite3
db = sqlite3.connect('cinema.db')
cursor = db.cursor()
id1 = 1
username1 = 'calkey'
password1 = 'password'

id2 = 2
username2 = 'miniman'
password2 = 'password2'

movieid1 = 1
moviename1 = 'batman'
movierating1 = '12A'
movieruntime1 = '2h20m'
movieinfo1 = 'Man dresses as bat and beats up the mentally ill'
movieimage1 = 'image_url1'

movieid2 = 2
moviename2 = 'superman'
movierating2 = '15'
movieruntime2 = '2h30m'
movieinfo2 = 'is it a bird? is it a plane?'
movieimage2 = 'image_url2'

screenid1 = 1
seatbooked1 = 'A4'

screenid2 = 2
seatbooked2 = 'b2'


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

db.commit()

db.close()
