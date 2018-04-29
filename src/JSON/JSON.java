package JSON;

import Tills.Movie;
import Tills.Seat;

import com.google.gson.*;


import Tills.*;

/**
 * Class used to serialize and deserialize objects
 */
public class JSON {
    /**
     * Converts a json string in to an array of Movie objects
     *
     * @param json The json string to be converted
     * @return The array of movie objects
     */
    public static Movie[] moviesFromJson(String json) {
        Gson gson = new Gson();
        Movie[] movies = gson.fromJson(json, Movie[].class);
        return movies;
    }


    /**
     * Converts a json string in to a single Movie object
     *
     * @param json The json string to be converted
     * @return The movie objecta
     */
    public static Movie movieFromJson(String json) {
        Gson gson = new Gson();
        Movie[] movie = moviesFromJson(json);
        return movie[0];
    }

    /**
     * Converts an array of Movie objects to a json string
     *
     * @param movies The array to be convetred
     * @return The json String
     */
    public static String moviesToJson(Movie[] movies) {
        Gson gson = new Gson();
        String json = gson.toJson(movies);
        return json;
    }


    /**
     * Converts a json string in to an array of Seat objects
     *
     * @param json The json string to be converted
     * @return The array of Seat objects
     */
    public static Seat[] seatsFromJson(String json) {
        Gson gson = new Gson();
        Seat[] seats = gson.fromJson(json, Seat[].class);
        return seats;
    }

    /**
     * Converts an array of Seat objects to a json string
     *
     * @param seats The array to be convetred
     * @return The json String
     */
    public static String seatsToJson(Seat[] seats) {
        Gson gson = new Gson();
        String json = gson.toJson(seats);
        return json;
    }


    /**
     * Converts a json string in to an array of WhatsOn objects
     *
     * @param json The json string to be converted
     * @return The array of WhatsOn objects
     */
    public static WhatsOn[] whatsOnFromJson(String json) {
        Gson gson = new Gson();
        WhatsOn[] whatsOn = gson.fromJson(json, WhatsOn[].class);
        return whatsOn;
    }

    /**
     * Converts an array of WhatsOn objects to a json string
     *
     * @param whatsOn The array to be convetred
     * @return The json String
     */
    public static String whatsOnToJson(WhatsOn[] whatsOn) {
        Gson gson = new Gson();
        String json = gson.toJson(whatsOn);
        return json;
    }
}
