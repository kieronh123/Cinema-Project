package Tills.Controllers;


import Tills.Harness;
import Tills.Seat;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import JSON.JSON;
import java.io.IOException;

/**
 * Created by sc16km on 11/04/18.
 */

/**
 * Controller class for the ticketType.fxml page
 */
public class TicketPageController {
    //Set FXML variables that match those in ticketType.fxml
    @FXML
    Label FilmName;
    @FXML
    Label FilmTime;
    @FXML
    GridPane vip;
    @FXML
    GridPane ButtonsSeats;
    @FXML
    Button returnHome;
    @FXML
    GridPane Ticket;
    @FXML
    Button confirm;
    @FXML
    Label ticketBlank;
    @FXML
    HBox Home;
    @FXML
    GridPane Main;


    //Variables that will be used throughout this controller
    public String time = null;
    public String name = null;
    public int screeningID = 0;
    public boolean selectedVIP = false;
    public boolean selectedTicket = false;
    public boolean selectedSeat = false;

    public String column = "7";
    public String row = "7";
    public String ticket = "none";
    public String age;


    /**
     * Constructor for this controller
     *
     * @param time     The film time selected on the previous page
     * @param filmName
     */
    public TicketPageController(String time, String filmName, int screeningID, String Age) {
        this.time = time;
        this.name = filmName;
        this.screeningID = screeningID;
        this.age = Age;
    }

    /**
     * Method that initializes values of labels and creates combo boxes and
     * buttons to be displayed on the tickets and seating page.
     */
    @FXML
    private void initialize() {
        System.out.println(screeningID);
        //Set these labels to the film name and time previously selected
        FilmName.setText(name);
        FilmTime.setText(time);
        //Set action of the button return home
        returnHome.setOnAction((ActionEvent Event) -> {
            //Try and load the movie screen page
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../moviesPage.fxml"));
                Parent parent = (Parent) loader.load();
                Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
                window.setScene(new Scene(parent));
                window.show();
            } catch (IOException e) {
                System.err.println("Could not load page");
            }
        });

        //Set button to maximum possible size
        returnHome.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        returnHome.prefHeightProperty().bind(Home.heightProperty());
        returnHome.prefWidthProperty().bind(Home.widthProperty());


        //Set button to maximum possible size
        confirm.prefHeightProperty().bind(Home.heightProperty());
        confirm.prefWidthProperty().bind(Home.widthProperty());
        confirm.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        confirm.setDisable(true);
        confirm.setOnAction((Event ) -> {

                payment(Event);

        });

        //Create a combo box that allows employee to select the age ticket
        final ComboBox<String> ticketType = new ComboBox<>();
        //If the age rating of the film is >16 then children can not go to watch it
        if (age.equals("18")) {
            ticketType.getItems().addAll("Adult", "Senior");
            //Otherwise allow children tickets to be purchased
        } else {
            ticketType.getItems().addAll("Adults", "Senior", "Child");
        }
        //Maximise size of combo box
        ticketType.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //When the employee selects the ticket type, set the variable ticket to the selection
        ticketType.setOnAction(e -> {
            selectedTicket = true;
            if(selectedTicket & selectedSeat & selectedVIP){
                confirm.setDisable(false);
            }
            ticket = ticketType.getValue();
            System.out.println(ticket);
        });
        //Add the combobox to the fxml page
        Ticket.add(ticketType, 0, 0);

        //Create a combo box that allows employee to select the VIP or not
        final ComboBox<String> vipTicket = new ComboBox<>();

        vipTicket.getItems().addAll("Yes", "No");

        vipTicket.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //When the employee selects VIP or not set boolean variable to true or false respectively
        vipTicket.setOnAction(e -> {
            selectedVIP = true;
            if(selectedTicket & selectedSeat & selectedVIP){
                confirm.setDisable(false);
            }
            String response = vipTicket.getValue();
            System.out.println(response);
            if (response.equals("Yes")) {
                displaySeats(true);
            } else if (response.equals("No")) {
                displaySeats(false);
            }
        });
        //Add the combobox to the fxml page
        vip.add(vipTicket, 0, 0);


    }

    public void displaySeats(boolean VIPticket) {
        Harness harness = new Harness();
        StringBuffer response = null;
        try {
            //Try and send a get request to receive all information from movies table
            response = harness.sendGet("bookings/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Seat[] seats = JSON.seatsFromJson(response.toString());
        for(Seat seat: seats){
            System.out.println(seat.toString());
        }
        //column
        for (int i = 1; i < 6; i++) {
            //row
            for (int j = 1; j < 6; j++) {
                Button buttonCreate = new Button();
                buttonCreate.setText(j + "," + i);
                buttonCreate.setOnAction((ActionEvent) -> {
                    String id = buttonCreate.getText();
                    String[] segmented = id.split(",");
                    row = segmented[0];
                    column = segmented[1];
                    System.out.println(row + column);
                    selectedSeat = true;
                    if(selectedTicket & selectedSeat & selectedVIP){
                        confirm.setDisable(false);
                    }

                });
                buttonCreate.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                if (VIPticket == true & (j != 3)) {
                    buttonCreate.setDisable(true);
                } else if (VIPticket == false & (j == 3)) {
                    buttonCreate.setDisable(true);
                }

                for (Seat seat : seats) {
                    if ((seat.Row_Num == j) ) {
                        if((seat.Column_Num == i)){
                            if((seat.Screening_ID == (screeningID-3))){
                                buttonCreate.setDisable(true);
                                System.out.println(j + " " + i);
                            System.out.println(seat.Screening_ID);
                            System.out.println(screeningID);
                            }

                        }
                    }
                }
                ButtonsSeats.add(buttonCreate, i - 1, j - 1);

            }
        }
    }


    public void payment(ActionEvent event) {
        String urlParameters = "data=" + String.valueOf(screeningID -3) + " , " + row + " , " + column;
        StringBuffer reply = null;
        try {
            //Try and send a get request to receive all information from movies table
            Harness.sendPost("bookings/", urlParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //Load the ticket page with the selected name and time
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Receipt.fxml"));
            PaymentPageController controller = new PaymentPageController(time, name, (row + " , " + column));
            loader.setController(controller);
            Parent parent = loader.load();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not load page");
        }

    }
}