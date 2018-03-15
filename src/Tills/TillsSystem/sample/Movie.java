package Tills.TillsSystem.sample;

import java.util.List;

public class Movie {
    //fields
    private int Movie_ID;
    private String Movie_Name;
    private int Movie_Rating;
    private int Movie_Runtime;

    private String Movie_Director;
    private List<String> Movie_LeadActors;

    private String Movie_Blurb;
    private String Movie_ImageFilename;

    public Movie(int ID, String name, int rating, int runtime, String director, List<String> leadActors, String blurb, String imageFilename) {
        this.Movie_ID = ID;
        this.Movie_Name = name;
        this.Movie_Rating = rating;
        this.Movie_Runtime = runtime;
        this.Movie_Director = director;
        this.Movie_LeadActors = leadActors;
        this.Movie_Blurb = blurb;
        this.Movie_ImageFilename = imageFilename;
    }

    @Override
    public String toString(){
        String actors = null;
        for(String actor: Movie_LeadActors){
            actors += (Movie_LeadActors + ",");
        }
        return "ID: " + Movie_ID + "\n" +
               "Name: " + Movie_Name  + "\n" +
               "Rating: " + Movie_Rating  + "\n" +
               "Runtime: " + Movie_Runtime  + "\n" +
               "Director: " + Movie_Director  + "\n" +
               "Lead Actors: " + Movie_LeadActors + "\n" +
               "Blurb: " + Movie_Blurb  + "\n" +
               "Image filename: " + Movie_ImageFilename;
    }
}