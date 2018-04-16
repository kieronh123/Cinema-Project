package Tills.Controllers;

import Tills.WhatsOn;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private void initialize() {
        Harness harness = new Harness();
        String whatsOnString = null;
        Label[] labels = {movie1, movie2, movie3, movie4, movie5, movie6};
        //get movies list from the database
        try {
            whatsOnString = harness.sendGet("whatson").toString();
            WhatsOn[] whatsOn = JSON.whatsOnFromJson(whatsOnString);

            //set the text of each label to the title of the movie
            for (int i = 0; i < 6; i++) {
                String movieString =  harness.sendGet("movies/" + whatsOn[i].getScreening_ID()).toString();
                Movie movie = JSON.movieFromJson(movieString);
                System.out.println(movie.getMovie_Name());
                labels[i].setText(movie.getMovie_Name());
            }
        } catch (Exception e) {
            System.err.println("Could not recieve data from database");
        }
    }

    public void submitMovieChoice(ActionEvent event) {
        Button button = (Button) event.getSource();
        GridPane movie = (GridPane) button.getParent().getParent();
        String name = null;

        //retrieve the name of the selected movie
        HBox hbox = (HBox) movie.getChildren().get(0);
        Label label = (Label) hbox.getChildren().get(0);
        name = label.getText();


        try {
            //Load the ticket page with the selected name and time
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ticketType.fxml"));
            TicketPageController controller = new TicketPageController(button.getText(), name);
            loader.setController(controller);
            Parent parent = loader.load();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(parent));
            window.show();
        } catch (IOException e) {
            System.err.println("Could not load page");
        }

    }
}
