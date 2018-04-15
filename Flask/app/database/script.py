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
                        Movie_Runtime INTEGER,
                        Movie_Info TEXT,
                        Movie_Image TEXT)
''')

cursor.execute('''
    CREATE TABLE Bookings(Screening_ID INTEGER,
                        Row_Num INTEGER,
                        Column_Num INTEGER)
''')



cursor.execute('''
    CREATE TABLE Whats_On(Screening_ID INTEGER PRIMARY KEY,
                          Movie_ID INTEGER,
                          Screen_ID INTEGER,
                          Start_Time DATETIME)

''')


db.commit()


db.close()
