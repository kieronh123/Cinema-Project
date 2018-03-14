package JSON;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import Tills.TillsSystem.sample.Movie;

public class JSON {
    public static Movie[] getMovies(String json){
        Gson gson = new Gson();
        Movie[] movies = gson.fromJson(json, Movie[].class);
        return movies;
    }

    public static void addMovies(){
        Gson gson = new Gson();
        Movie[] movies = {new Movie(1, "movie 1", 5, 120, "director1", new ArrayList<String>(Arrays.asList("Actor1", "Actor 2")), "blurb1", "image1"), new Movie(2, "movie 2", 6, 110, "director2", new ArrayList<String>(Arrays.asList("Actor3", "Actor4")), "blurb2", "image2")};
        String json =  gson.toJson(movies);
        System.out.println(json);
    }

    public static void main(String[] args) {
        JSON.addMovies();
    }
}
