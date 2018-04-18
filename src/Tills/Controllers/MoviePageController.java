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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


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

    Label[] movieLabels;

    @FXML
    private void initialize() {
        Map<Integer, ArrayList<WhatsOn>> movieMap = new HashMap<>();

        String whatsOnString = null;
        movieLabels = new Label[]{movie1, movie2, movie3, movie4, movie5, movie6};
        try {
            //get the list of screenings from the database
            whatsOnString = Harness.sendGet("whatson").toString();
            WhatsOn[] whatsOn = JSON.whatsOnFromJson(whatsOnString);

            for (int i = 0; i < whatsOn.length; i++) {
                if(!movieMap.containsKey(whatsOn[i].getMovie_ID())){
                    movieMap.put(whatsOn[i].getMovie_ID(),new ArrayList<>(Arrays.asList(whatsOn[i])));
                } else {
                    ArrayList<WhatsOn> list = movieMap.get(whatsOn[i].getMovie_ID());
                    list.add(whatsOn[i]);
                    System.out.println(movieMap.get(whatsOn[i].getMovie_ID()));
                }
            }

            int movieCount = 0;
            for(ArrayList<WhatsOn> list : movieMap.values()){
                int screeningCount = 0;
                setMovieLabel(movieCount, list.get(0));
                movieCount++;
                for (WhatsOn whatson : list){
                    Label label = movieLabels[screeningCount/3];
                    GridPane gridPane = (GridPane)label.getParent().getParent();
                    HBox hbox = (HBox)gridPane.getChildren().get(1);
                    VBox vBox = (VBox)hbox.getChildren().get(screeningCount);
                    Label screenLabel = (Label)vBox.getChildren().get(1);
                    Button button = (Button)vBox.getChildren().get(0);
                    screenLabel.setText("Screen " + whatson.getScreen_ID());
                    button.setText(whatson.getStart_Time().substring(11,16));
                    screeningCount++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Could not recieve data from database");
        }
    }

    public void submitMovieChoice(ActionEvent event) {
        Button button = (Button) event.getSource();
        GridPane movie = (GridPane) button.getParent().getParent().getParent();
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


    public void setMovieLabel(int index, WhatsOn whatsOn){
        try {
            String movieString = Harness.sendGet("movies/" + whatsOn.getMovie_ID()).toString();
            Movie movie = JSON.movieFromJson(movieString);
            movieLabels[index].setText(movie.getMovie_Name());
        }catch (Exception e){
            System.err.println("Failed to retrieve movie");
        }
    }
}
