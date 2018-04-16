package Tills;

import java.util.List;

public class Movie {
    //fields
    private int Movie_ID;
    private String Movie_Name;
    private String Movie_Rating;
    private String Movie_Runtime;

//    private String Movie_Director;
//    private List<String> Movie_LeadActors;

    private String Movie_Info;
    private String Movie_Image;

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

//    public String getMovie_Director() {
//        return Movie_Director;
//    }
//
//    public List<String> getMovie_LeadActors() {
//        return Movie_LeadActors;
//    }
//
//    public String getMovie_Blurb() {
//        return Movie_Blurb;
//    }

//    public String getMovie_ImageFilename() {
//        return Movie_ImageFilename;
//    }

    public Movie(int ID, String name, String rating, String runtime, String director, List<String> leadActors, String blurb, String imageFilename) {
        this.Movie_ID = ID;
        this.Movie_Name = name;
        this.Movie_Rating = rating;
        this.Movie_Runtime = runtime;
//        this.Movie_Director = director;
//        this.Movie_LeadActors = leadActors;
//        this.Movie_Blurb = blurb;
//        this.Movie_ImageFilename = imageFilename;
    }

    @Override
    public String toString(){
//        String actors = null;
//        for(String actor: Movie_LeadActors){
//            actors += (Movie_LeadActors + ",");
//        }
        return "ID: " + Movie_ID + "\n" +
               "Name: " + Movie_Name  + "\n" +
               "Rating: " + Movie_Rating  + "\n" +
               "Runtime: " + Movie_Runtime  + "\n";
//               "Director: " + Movie_Director  + "\n" +
//               "Lead Actors: " + Movie_LeadActors + "\n" +
//               "Blurb: " + Movie_Blurb  + "\n" +
//               "Image filename: " + Movie_ImageFilename;
    }
}