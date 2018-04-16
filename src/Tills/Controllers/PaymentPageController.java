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
 * Created by sc16jsg on 14/04/18.
 */

public class PaymentPageController {
  //Set FXML variables that match those in PaymentPage.fxml

  @FXML
  Label FilmName;
  @FXML
  Label FilmTime;
  @FXML
  Label Date;
  @FXML
  Label PriceOfTicket;
  @FXML
  Label Seating;
  @FXML
  Button PayByCash;
  @FXML
  Label Screening;


  String time = null;
  String filmName = null;
  String seat = null;
  boolean VIP;

  public JTextArea area = new JTextArea();

  public PaymentPageController( String time, String filmName, String seat) {
    this.time = time;
    this.name = filmName;
    this.seat = seat;
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

/*
The code below is used for printing the
receipt.

*/
/*
buttonOnPrint is for priting the ticket however a PDF will still be needed
*/
  @FXML
  private void buttonOnPrint(ActionEvent event)throws PrintException{
    area.setText("*********************************");
    try{
      area.print();
    }catch(Exception e){
      System.out.println("error in printing " + e.getMessage());
    }
  }

/*
This is used for generating the receipt from the ticket chosen.
variables are still needed to update the fields
*/
  @FXML
  private void showReceipt(){
    Date object = new Date();
    String date = object.toString();

    area.setText("**********************************");
    area.setText(area.getText()+"\n"+date+"\n");
  }
}
