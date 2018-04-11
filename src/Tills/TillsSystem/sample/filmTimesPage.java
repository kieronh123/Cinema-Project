package Tills.TillsSystem.sample;

import JSON.JSON;

/**
 * Created by sc16km on 27/03/18.
 */
public class filmTimesPage {
    //Can be used for manipulation of the movies data from movie table in database
    public static void main(String[] args) throws Exception {
        StringBuffer reply = filmNamesTimes();
    }

    public static StringBuffer filmNamesTimes(){
        //Initialise harness class
        Harness harness = new Harness();
        StringBuffer response = null;
        try {
            //Try and send a get request to receive all information from movies table
            response = harness.sendGet("movies/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Use movies method in JSON class to get an array of Movies
        Movie[] movies = JSON.moviesFromJson(response.toString());
        for (Movie movie : movies) {
                System.out.println(movie.toString());
        }
        return response;
    }
}
