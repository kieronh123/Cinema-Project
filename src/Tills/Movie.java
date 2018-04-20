package Tills;

import java.util.List;

public class Movie {
    //fields
    private int Movie_ID;
    private String Movie_Name;
    private String Movie_Rating;
    private String Movie_Runtime;
    private String Movie_Info;
    private String Movie_Image;

    public Movie(int movie_ID, String movie_Name, String movie_Rating, String movie_Runtime, String movie_Info, String movie_Image) {
        Movie_ID = movie_ID;
        Movie_Name = movie_Name;
        Movie_Rating = movie_Rating;
        Movie_Runtime = movie_Runtime;
        Movie_Info = movie_Info;
        Movie_Image = movie_Image;
    }

    public int getMovie_ID() {
        return Movie_ID;
    }

    public String getMovie_Name() {
        return Movie_Name;
    }

    public String getMovie_Rating() {
        return Movie_Rating;
    }

    public String getMovie_Runtime() {
        return Movie_Runtime;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Movie_ID=" + Movie_ID +
                ", Movie_Name='" + Movie_Name + '\'' +
                ", Movie_Rating='" + Movie_Rating + '\'' +
                ", Movie_Runtime='" + Movie_Runtime + '\'' +
                ", Movie_Info='" + Movie_Info + '\'' +
                ", Movie_Image='" + Movie_Image + '\'' +
                '}';
    }
}