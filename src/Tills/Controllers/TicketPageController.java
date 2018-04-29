package Tills.Controllers;


import Tills.Harness;
import Tills.Seat;
import com.sun.org.apache.xpath.internal.SourceTree;
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
    public boolean VIP = false;
    public String ticket = "";
    public String column = "7";
    public String row = "7";
    public String age;

    public int screeningID = 0;
    public boolean selectedVIP = false;
    public boolean selectedTicket = false;
    public boolean selectedSeat = false;




    /**
     * Constructor for this controller
     *
     * @param time          The film time selected on the previous page
     * @param filmName      The film name selected on the previous page
     * @param screeningID   The screening ID of the viewing selected
     * @param Age           The age rating of the film
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
        //Set these labels to the film name and time previously selected
        FilmName.setText(name);
        FilmTime.setText(time);
        //Set action of the button return home
        returnHome.setOnAction((ActionEvent Event) -> {
            //Try and load the movie screen page
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/moviesPage.fxml"));
                Parent parent = (Parent) loader.load();
                Stage windowOld = (Stage) ((Node) Event.getSource()).getScene().getWindow();
                windowOld.close();
                Stage window = new Stage();
                window.setMaximized(true);
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
        //Disable this button until the employee has selected ticket type, VIP and seat
        confirm.setDisable(true);
        //Call payment
        confirm.setOnAction((Event ) -> {
                payment(Event);
        });


        //Create a combo box that allows employee to select the age ticket
        final ComboBox<String> ticketType = new ComboBox<>();
        //If the age rating of the film is 18 then children can not go to watch it
        if (age.equals("18")) {
            ticketType.getItems().addAll("Adult", "Senior");
            //Otherwise allow children tickets to be purchased
        } else {
            ticketType.getItems().addAll("Adult", "Senior", "Child");
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
                VIP = true;
                displaySeats(true);
            } else if (response.equals("No")) {
                VIP = false;
                displaySeats(false);
            }
        });
        //Add the combobox to the fxml page
        vip.add(vipTicket, 0, 0);
    }

    /**
     * Method displays the seat layout and disables those unavailable for putchase
     *
     * @param VIPticket If the client wants a VIP ticket or not
     */
    public void displaySeats(boolean VIPticket) {
        //Clear the grid panel of buttons
        ButtonsSeats.getChildren().clear();

        //Send an API call to get all of the seats that have been booked
        Harness harness = new Harness();
        StringBuffer response = null;
        try {
            //Try and send a get request to receive all information from movies table
            response = harness.sendGet("bookings/");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Store all the seats that have been booked as an array of type Seat
        Seat[] seats = JSON.seatsFromJson(response.toString());

        //Assign column numbers from database to letters as standard in cinema
        String[] columnLetters = {"A", "B", "C", "D", "E"};

        //Columns
        for (int i = 1; i < 6; i++) {
            //Rows
            for (int j = 1; j < 6; j++) {
                //Create a new button
                Button buttonCreate = new Button();
                //Set the text to the row number and which seat in the row (labelled A-E)
                buttonCreate.setText(j + "," + i);
                int finalI = i;
                int finalJ = j;
                //When the employee clicks the button to select the seat
                buttonCreate.setOnAction((ActionEvent) -> {
                    //Save the column and row of the seat selected
                    row = Integer.toString(finalJ);
                    column = Integer.toString(finalI);
                    //If the employee has entered a ticket type, whether it is VIP or not
                    //and selected a seat they can then confirm the purchase
                    selectedSeat = true;
                    if(selectedTicket & selectedSeat & selectedVIP){
                        confirm.setDisable(false);
                    }

                });
                buttonCreate.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                //If the ticket is VIP only allow row 3 to be selected
                if (VIPticket == true & (j != 3)) {
                    buttonCreate.setDisable(true);
                //If the ticket is not VIP do not allow row 3 to be selected
                } else if (VIPticket == false & (j == 3)) {
                    buttonCreate.setDisable(true);
                }

                //Disable the buttons of the seats that have already been booked
                //for this screening.
                for (Seat seat : seats) {
                    if ((seat.Row_Num == j) ) {
                        if((seat.Column_Num == i)){
                            if((seat.Screening_ID == (screeningID))){
                                buttonCreate.setDisable(true);
                            }
                        }
                    }
                }
                //Add the button to the seat selection grid
                ButtonsSeats.add(buttonCreate, i - 1, j - 1);
            }
        }
    }

    /**
     * Method processes the 
     *
     * @param event If the client wants a VIP ticket or not
     */
    public void payment(ActionEvent event) {
        //Calculate the price of the ticket based on the type and if it is a VIP seat
        double price = 0;
        if(ticket.equals("Adult")){
            price = 8.00;
        }else if(ticket.equals("Senior") || ticket.equals("Child") ){
            price = 4.00;
        }

        if (VIP == true){
            price = (price*1.5);
        }

        System.out.println(price);

        //Post the seat selected to the database
        String urlParameters = "data=" + String.valueOf(screeningID) + " , " + row + " , " + column;
        StringBuffer reply = null;
        try {
            //Try and send a get request to receive all information from movies table
            Harness.sendPost("bookings/", urlParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }



        try {
            //Load the ticket page with the selected name and time
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Receipt.fxml"));
            PaymentPageController controller = new PaymentPageController(time, name, (row + " , " + column), price, row, column, screeningID);
            String filename = screeningID+"_"+row+"_"+column;
            controller.generateQRCodeImage(filename);
            loader.setController(controller);
            Parent parent = loader.load();
            Stage windowOld = (Stage) ((Node) event.getSource()).getScene().getWindow();
            windowOld.close();
            Scene scene = new Scene(parent);
            Stage window = new Stage();
            window.setMaximized(true);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not load page");
        }

    }
}
