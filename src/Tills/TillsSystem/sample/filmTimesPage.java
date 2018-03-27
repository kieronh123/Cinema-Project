package Tills.TillsSystem.sample;

import JSON.JSON;

/**
 * Created by sc16km on 27/03/18.
 */
public class filmTimesPage {

    public static void main(String[] args) throws Exception {
        StringBuffer reply = filmNamesTimes();
    }

    public static StringBuffer filmNamesTimes(){
        Harness harness = new Harness();
        StringBuffer response = null;
        try {
            System.out.println("here");
            response = harness.sendGet("movies/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Movie[] movies = JSON.moviesFromJson(response.toString());
        for (Movie movie : movies) {
                System.out.println(movie.toString());
        }
        return response;
    }
}
