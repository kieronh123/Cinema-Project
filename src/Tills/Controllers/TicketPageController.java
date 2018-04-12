package Tills.Controllers;

import JSON.JSON;
import Tills.Harness;
import Tills.LaunchTicketPage;
import Tills.Seat;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
    Label Child;
    @FXML
    GridPane ButtonsSeats;

    private int Age = 0;
    public boolean VIP;
    @FXML
    private void initialize(){
        FilmName.setText("Infinity war");
        FilmTime.setText("16:00");
        if(Age >=16){
            Child.setText("Child not available");
        }else {
            Child.setText("Child");
        }

//        Harness harness = new Harness();
//        StringBuffer response = null;
//        try {
//            //Try and send a get request to receive all information from movies table
//            response = harness.sendGet("seats/");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Seat[] seats = JSON.seatsFromJson(response.toString());
//        for (Seat seat : seats) {
        for(int i =0; i<5; i++){
            for(int j=0; j<5; j++) {
                Button buttonCreate = new Button();
                buttonCreate.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                //if(seat.taken == true){
                //buttonCreate.setDisable(true);
                //}else if(seat.vip == true && VIP==true){

                //}else if(seat.vip==true && VIP==false){
                //buttonCreate.setDisable(true);

                //}else if(seat.vip==false && VIP==true){
                //buttonCreate.setDisable(true);

                //}else{
                //}

                ButtonsSeats.add(buttonCreate, i, j);
            }
        }




    }


}