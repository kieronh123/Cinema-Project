package JSON;

import Tills.Movie;
import Tills.Seat;
import Tills.Ticket;
import Tills.User;
import com.google.gson.*;


import Tills.*;

public class JSON {
    public static Movie[] moviesFromJson(String json){
        Gson gson = new Gson();
        Movie[] movies = gson.fromJson(json, Movie[].class);
        return movies;
    }

    public static Movie movieFromJson(String json){
        Gson gson = new Gson();
        Movie movie = gson.fromJson(json, Movie.class);
        return movie;
    }

    public static void moviesToJson(Movie[] movies){
        Gson gson = new Gson();
        String json =  gson.toJson(movies);
    }

    public static Seat[] seatsFromJson(String json){
        Gson gson = new Gson();
        Seat[] seats = gson.fromJson(json, Seat[].class);
        return seats;
    }

    public static void seatsToJson(Seat[] seats){
        Gson gson = new Gson();
        String json =  gson.toJson(seats);
        System.out.println(json);
    }

    public static Ticket[] ticketsFromJson(String json){
        Gson gson = new Gson();
        Ticket[] tickets = gson.fromJson(json, Ticket[].class);
        return tickets;
    }

    public static void ticketsToJson(Ticket[] tickets){
        Gson gson = new Gson();
        String json =  gson.toJson(tickets);
        System.out.println(json);
    }

    public static User[] usersFromJson(String json){
        Gson gson = new Gson();
        User[] users = gson.fromJson(json, User[].class);
        return users;
    }

    public static void usersToJson(User[] users){
        Gson gson = new Gson();
        String json =  gson.toJson(users);
        System.out.println(json);
    }

    public static WhatsOn[] whatsOnFromJson(String json){
        Gson gson = new Gson();
        WhatsOn[]whatsOn = gson.fromJson(json, WhatsOn[].class);
        return whatsOn;
    }

    public static void whatsOnToJson(WhatsOn[] whatsOn){
        Gson gson = new Gson();
        String json = gson.toJson(whatsOn);
        System.out.println(json);
    }
}
