package Tills.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    Label Child;
    @FXML
    GridPane ButtonsSeats;
    @FXML
    Button returnHome;


    String time = null;
    String name = null;

    private int Age = 0;
    public boolean VIP;
    public String column;
    public String row;

    public TicketPageController(String time, String filmName){
        this.time = time;
        this.name = filmName;
    }

    @FXML
    private void initialize(){
        FilmName.setText(name);
        FilmTime.setText(time);

        returnHome.setOnAction((Event) ->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../moviesPage.fxml"));

                Parent parent = (Parent)loader.load();
                Stage window = (Stage)((Node)Event.getSource()).getScene().getWindow();
                window.setScene(new Scene(parent));
                window.show();
            }catch(IOException e){
                System.err.println("Could not load page");
            }


        });


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
                buttonCreate.setText(i+","+j);
                buttonCreate.setOnAction((ActionEvent) ->{
                    String id = buttonCreate.getText();
                    String[] segmented = id.split(",");
                    column = segmented[0];
                    row = segmented[1];
                    System.out.println(column+row);

                });
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

    public void returnHome(ActionEvent event) {

     }


    }
