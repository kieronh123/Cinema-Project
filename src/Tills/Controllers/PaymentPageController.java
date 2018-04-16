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


public class PaymentPageController {
  //Set FXML variables that match those in ticketType.fxml

  @FXML
  Label FilmName;
  @FXML
  Label FilmTime;
  @FXML
  GridPane ButtonsSeats;
  @FXML
  Button returnHome;
  @FXML
  Label PriceOfTicket;


  String time = null;
  String name = null;
  String seat = null;
  boolean VIP;



  public PaymentPageController(Label filmName, String time, String name, String seat, boolean VIP) {
    FilmName = filmName;
    this.time = time;
    this.name = name;
    this.seat = seat;
    this.VIP = VIP;
  }


  @FXML
  private void initialize(){
    FilmName.setText(name);
    FilmTime.setText(time);

    returnHome.setOnAction((Event) ->{
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../PaymentPage.fxml"));

        Parent parent = (Parent)loader.load();
        Stage window = (Stage)((Node)Event.getSource()).getScene().getWindow();
        window.setScene(new Scene(parent));
        window.show();
      }catch(IOException e){
        System.err.println("Could not load page");
      }


    });
  }
}
