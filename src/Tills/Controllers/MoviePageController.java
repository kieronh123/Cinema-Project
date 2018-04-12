package Tills.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import JSON.JSON;
import Tills.Harness;
import Tills.Movie;


public class MoviePageController {

    @FXML
    private Label movie1;
    @FXML
    private Label movie2;
    @FXML
    private Label movie3;
    @FXML
    private Label movie4;
    @FXML
    private Label movie5;
    @FXML
    private Label movie6;
    
    @FXML
    private void initialize(){
        Harness harness = new Harness();
        String moviesString = null;
        Label[] labels = {movie1, movie2,movie3,movie4,movie5,movie6};
        try {
             moviesString = harness.sendGet("movies").toString();
        }catch (Exception e){
            System.err.println("Could not recieve data from database");
        }
        Movie[] movies = JSON.moviesFromJson(moviesString);

        for (int i = 0; i < movies.length; i++) {
            labels[i].setText(movies[i].getMovie_Name());
        }
    }
}
