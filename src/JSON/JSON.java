package JSON;

import com.google.gson.*;
import com.google.gson.reflect.*;

import Tills.TillsSystem.sample.Movie;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSON {
    public static Movie[] getMovies(String json){
        Gson gson = new Gson();
        Movie[] movies = gson.fromJson(json, Movie[].class);
        return movies;
//        Gson gson = new Gson();
//        int[] vals = gson.fromJson(json, int[].class);
//        return vals;
    }

    public static void main(String[] args) {
        Movie[] parsed = JSON.getMovies("[{\"ID\":1, \"rating\":2, \"name\":name, \"runtime\":3, \"director\":director, \"leadActors\":[actor1, actor2], \"blurb\":blurb1, \"imageFilename\":image1}," +
                "{\"ID\":45, \"rating\":55, \"name\":name2, \"runtime\":67, \"director\":director2, \"leadActors\":[actor3, actor4], \"blurb\":blurb2, \"imageFilename\":image2}]");
        for(Movie movie : parsed){
            System.out.println(movie.toString());
        }
    }
}
