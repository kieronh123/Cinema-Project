##Classes that provide objects for the SQL tables to access
#WhatsOn table
class WhatsOn ():
    def __init__(self, Screening_ID, Movie_ID, Screen_ID, Start_Time,):
        self.Screening_ID = Screening_ID
        self.Movie_ID = Movie_ID
        self.Screen_ID = Screen_ID
        self.Start_Time = Start_Time


    def __str__(self):
        return "%s %s %s %s" % (self.Screening_ID,self.Movie_ID,self.Screen_ID,self.Start_Time)

#Movie table
class Movie ():
    def __init__(self, Movie_ID, Movie_Name, Movie_Rating, Movie_Runtime, Movie_Info, Movie_image, Movie_Director, Movie_Actors):
        self.Movie_ID = Movie_ID
        self.Movie_Name = Movie_Name
        self.Movie_Rating = Movie_Rating
        self.Movie_Runtime = Movie_Runtime
        self.Movie_Info = Movie_Info
        self.Movie_Image = Movie_image
        self.Movie_Director = Movie_Director
        self.Movie_Actors = Movie_Actors

#Users table
class User ():
    def __init__(self, User_ID, Username, Password):
        self.User_ID = User_ID
        self.Username = Username
        self.Password = Password

#Bookings table
class Booking ():
    def __init__(self, Screening_ID,Row_Num,Column_Num):
        self.Screening_ID = Screening_ID
        self.Row_Num = Row_Num
        self.Column_Num = Column_Num

#Card details table
class CardDetails ():
    def __init__(self, User_ID,Card_Name,Card_Number,Card_SortCode,Card_SecurityCode):
        self.User_ID = User_ID
        self.Card_Name = Card_Name
        self.Card_Number = Card_Number
        self.Card_SortCode = Card_SortCode
        self.Card_SecurityCode = Card_SecurityCode
    def __str__(self):
        return "%s %s %s %s %s" % (self.User_ID,self.Card_Name,self.Card_Number,self.Card_SortCode, self.Card_SecurityCode)
