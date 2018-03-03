package Tills.TillsSystem.sample;

import java.util.List;

public class Movie {
    //fields
    public int ID;
    public String name;
    public int rating;
    public int runtime;

    private String director;
    public List<String> leadActors;

    private String blurb;
    private String imageFilename;

    public Movie()
    {}

    @Override
    public String toString(){
        String actors = null;
        for(String actor: leadActors){
            actors += (leadActors + ",");
        }
        return "ID: " + ID + "\n" +
               "Name: " + name  + "\n" +
               "Rating: " + name  + "\n" +
               "Runtime: " + name  + "\n" +
               "Director: " + name  + "\n" +
               "Lead Actors: " + leadActors + "\n" +
               "Blurb: " + name  + "\n" +
               "Image filename: " + name;
    }
}