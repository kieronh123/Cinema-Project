import sqlite3

db = sqlite3.connect('cinema.db')



cursor = db.cursor()
cursor.execute('''
    CREATE TABLE Users(User_ID INTEGER PRIMARY KEY,
                    Username TEXT UNIQUE,
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
                        Column_Num INTEGER,
                        PRIMARY KEY(Screening_ID, Row_Num, Column_Num),
                        FOREIGN KEY (Screening_ID) REFERENCES Whats_On(Screening_ID))
''')



cursor.execute('''
    CREATE TABLE Whats_On(Screening_ID INTEGER UNIQUE,
                          Movie_ID INTEGER,
                          Screen_ID INTEGER,
                          Start_Time DATETIME,
                          PRIMARY KEY (Screen_ID, Start_Time),
                          FOREIGN KEY (Movie_ID) REFERENCES Movies(Movie_ID))
''')

db.commit()


db.close()
