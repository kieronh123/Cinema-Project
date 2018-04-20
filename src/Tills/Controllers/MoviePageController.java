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


/** Controller class for the moviesPage.fxml file
 *
 */
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

    private Movie[] movies;
    private Label[] movieLabels;
    private int[] screeningIDs;


    @FXML
    /** This method is run when the moviesPage.fxml file is loaded. It sets the text of the buttons and labels.
     */
    private void initialize() {
        Map<Integer, ArrayList<WhatsOn>> movieMap = new HashMap<>();

        String whatsOnString = null;
        movieLabels = new Label[]{movie1, movie2, movie3, movie4, movie5, movie6};
        screeningIDs = new int[18];
        movies = new Movie[6];

        try {
            //get the list of screenings from the database
            whatsOnString = Harness.sendGet("whatson").toString();
            WhatsOn[] whatsOn = JSON.whatsOnFromJson(whatsOnString);

            //loop through the WhatsOn objects adding them to the hashmap
            int moviecount1 = 0;
            for (int i = 0; i < whatsOn.length; i++) {
                if(!movieMap.containsKey(whatsOn[i].getMovie_ID())){
                    movieMap.put(whatsOn[i].getMovie_ID(),new ArrayList<>(Arrays.asList(whatsOn[i])));
                    //Store the different movies in an array
                    movies[moviecount1] = JSON.movieFromJson(Harness.sendGet("movies/" + whatsOn[i].getMovie_ID()).toString());
                    moviecount1++;
                } else {
                    ArrayList<WhatsOn> list = movieMap.get(whatsOn[i].getMovie_ID());
                    list.add(whatsOn[i]);
                }
            }

            //Loop through the lists of WhatsOn objects for each movie
            int movieCount2 = 0;
            int totalScreeningCount = 0;
            for(ArrayList<WhatsOn> list : movieMap.values()){
                int screeningCount = 0;
                setMovieLabel(movieCount2, movies[movieCount2]);
                //Loop through each WhatsOn object
                for (WhatsOn whatson : list){
                    //set the screening id
                    screeningIDs[movieCount2 * 3 + screeningCount] = whatson.getScreening_ID();

                    //Set the text of the labels and button
                    Label label = movieLabels[totalScreeningCount/3];
                    GridPane gridPane = (GridPane)label.getParent().getParent();
                    HBox hbox = (HBox)gridPane.getChildren().get(1);
                    VBox vBox = (VBox)hbox.getChildren().get(screeningCount);
                    Label screenLabel = (Label)vBox.getChildren().get(1);
                    Button button = (Button)vBox.getChildren().get(0);
                    screenLabel.setText("Screen " + whatson.getScreen_ID());
                    button.setText(whatson.getStart_Time().substring(11,16));
                    screeningCount++;

                }

                totalScreeningCount = (int)(Math.ceil((double)screeningCount/3) * 3);
                movieCount2++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Could not recieve data from database");
        }
    }

    /** Run when a button is pressed on the moviesPage. Works out which movie is being selected as well as the
     * time and screen and passes this information to the ticket page controller
     *
     * @param event The event that took place
     */
    public void submitMovieChoice(ActionEvent event) {
        Button button = (Button) event.getSource();
        GridPane movie = (GridPane) button.getParent().getParent().getParent();
        String name = null;

        // retrieve the name of the selected movie
        HBox hbox = (HBox) movie.getChildren().get(0);
        Label label = (Label) hbox.getChildren().get(0);
        name = label.getText();

        //Get the rating for the selected movie
        String age = movies[Integer.parseInt(movie.getId().substring(movie.getId().length() - 1))].getMovie_Rating();

        //Get the screening ID;
        int screeningID;
        if(button.getId().length() == 7) {
            screeningID = screeningIDs[Integer.parseInt(button.getId().substring(button.getId().length() - 1))];
        }
        else
            screeningID = screeningIDs[Integer.parseInt(button.getId().substring(button.getId().length() - 2, button.getId().length() - 1 ))];
        try {
            //Load the ticket page with the selected name and time
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ticketType.fxml"));
            TicketPageController controller = new TicketPageController(button.getText(), name, screeningID, age);
            loader.setController(controller);
            Parent parent = loader.load();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(parent));
            window.show();
        } catch (IOException e) {
            System.err.println("Could not load page");
        }
    }


    /** Takes a given index and Movie object and sets the required label's text to the title of the movie
     *
     * @param index The index of the label that is to be set
     * @param movie The Movie object that contains the title of the movie
     */
    public void setMovieLabel(int index, Movie movie){
        try {
            movieLabels[index].setText(movie.getMovie_Name());
        }catch (Exception e){
            System.err.println("Failed to retrieve movie");
        }
    }
}
