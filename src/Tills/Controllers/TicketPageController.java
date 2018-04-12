package Tills.Controllers;

import JSON.JSON;
import Tills.Harness;
import Tills.LaunchTicketPage;
import Tills.Seat;
import Tills.Ticket;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Created by sc16km on 11/04/18.
 */
public class TicketPageController {

    @FXML
    Label FilmName;
    @FXML
    Label FilmTime;
    @FXML
    Label Child;
    @FXML
    GridPane ButtonsSeats;

    String time = null;

    private int Age = 0;
    public boolean VIP;

    public TicketPageController(String time){
        this.time = time;
    }

    @FXML
    private void initialize(){
        System.out.println(time);
        FilmName.setText("Infinity war");
        FilmTime.setText(time);
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
