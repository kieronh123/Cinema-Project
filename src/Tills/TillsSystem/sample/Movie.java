package Tills.TillsSystem.sample;

import java.util.List;

public class Movie {
    //fields
    private int ID;
    private String name;
    private int rating;
    private int runtime;

    private String director;
    private List<String> leadActors;

    private String blurb;
    private String imageFilename;

    public Movie(int ID, String name, int rating, int runtime, String director,
                 List<String> leadActors, String blurb, String imageFilename)
    {
        this.ID = ID;
        this.name = name;
        this.rating = rating;
        this.runtime = runtime;
        this.director = director;
        this.leadActors = leadActors;
        this.blurb = blurb;
        this.imageFilename = imageFilename;
    }

    @Override
    public String toString(){
        return "ID: " + ID + "\n" +
               "Name: " + name  + "\n" +
               "Name: " + name  + "\n" +
               "Name: " + name  + "\n" +
               "Name: " + name  + "\n" +
               "Name: " + name  + "\n" +
               "Name: " + name;
    }
}