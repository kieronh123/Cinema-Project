package JSON;

import com.google.gson.*;
import com.google.gson.reflect.*;

import Tills.TillsSystem.sample.Movie;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSON {
    public static List<Integer> getMovies(String json){
//        Gson gson = new Gson();
//        List<Movie> movies = new ArrayList<>();
//        Type listType = new TypeToken<List<Movie>>(){}.getType();
//        movies = gson.fromJson(json, listType);

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Movie>>(){}.getType();
        List<Integer> vals = gson.fromJson(json, listType);
        return vals;
    }

    public static void main(String[] args) {
        ArrayList<Integer> vals = (ArrayList<Integer>)JSON.getMovies("[{\"int1\":1}, {\"int2\":2}, {\"int3\":3}, {\"int4\":4}, {\"int5\":5}, {\"int6\":6}, {\"int7\":7}, {\"int8\":8}]");
        for(Integer i : vals){
            System.out.println(i);
        }
    }
}
