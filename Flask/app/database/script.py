import sqlite3

db = sqlite3.connect('cinema.db')



cursor = db.cursor()
cursor.execute('''
    CREATE TABLE Users(User_ID INTEGER PRIMARY KEY,
                    Username TEXT,
                    Password TEXT)
''')


cursor.execute('''
    CREATE TABLE Movies(Movie_ID INTEGER PRIMARY KEY,
                        Movie_Name TEXT,
                        Movie_Rating TEXT,
                        Movie_Runtime BLOB,
                        Movie_Info TEXT,
                        Movie_Image TEXT)
''')

cursor.execute('''
    CREATE TABLE Screens(Screen_ID INTEGER PRIMARY KEY,
                        Seat_Booked TEXT)
''')


cursor.execute('''
    CREATE TABLE Seats(Letter TEXT,
                        Num TEXT,
                        PRIMARY KEY (Letter, Num))
''')

cursor.execute('''
    CREATE TABLE Whats_On(Movie_ID INTEGER,
                          Screen_ID INTEGER,
                          Start_Time BLOB)

''')


db.commit()


db.close()
