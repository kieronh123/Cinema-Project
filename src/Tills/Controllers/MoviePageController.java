package Tills.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import JSON.JSON;
import Tills.Harness;
import Tills.Movie;
import javafx.stage.Stage;

import java.io.IOException;


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
        //get movies list from the database
        try {
             moviesString = harness.sendGet("movies").toString();
        }catch (Exception e){
            System.err.println("Could not recieve data from database");
        }
        Movie[] movies = JSON.moviesFromJson(moviesString);

        //set the text of each label to the title of the movie
        for (int i = 0; i < movies.length; i++) {
            labels[i].setText(movies[i].getMovie_Name());
        }
    }

    public void submitMovieChoice(ActionEvent event){
        Button button = (Button)event.getSource();

        switch (button.getParent().getParent().getId()){
            case "movie1Grid":
                System.out.println(movie1.getText());
                break;
            case "movie2Grid":
                System.out.println(movie2.getText());
                break;
            case "movie3Grid":
                System.out.println(movie3.getText());
                break;
            case "movie4Grid":
                System.out.println(movie4.getText());
                break;
            case "movie5Grid":
                System.out.println(movie5.getText());
                break;
            case "movie6Grid":
                System.out.println(movie6.getText());
                break;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ticketType.fxml"));
            TicketPageController controller = new TicketPageController(button.getText());
            loader.setController(controller);
            Parent parent = (Parent)loader.load();
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(new Scene(parent));
            window.show();
        }catch(IOException e){
            System.err.println("Could not load page");
        }

    }
}
