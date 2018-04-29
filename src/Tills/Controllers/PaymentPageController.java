package Tills.Controllers;
//package com.concretepage; //Alex

//import java.awt.image.BufferedImage;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.lang.*;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;



/**

 * Controller class for the PaymentPage.fxml page
 */

public class PaymentPageController {
  //Set FXML variables that match those in PaymentPage.fxml


  @FXML
  Label FilmName;
  @FXML
  Label FilmTime;
  @FXML
  Label CashGiven;
  @FXML
  Label amountDue;
  @FXML
  Label change;
  @FXML
  TextField cashGivenTF;
  @FXML
  TextField amountDueTF;
  @FXML
  TextField changeTF;
  @FXML
  Button returnHome;
  @FXML
  Button getChange;
  @FXML
  Button printPDF;

  public String time = null;
  public String name = null;
  public String seat = null;
  public double total = 0;


  public PaymentPageController( String time, String filmName, String seat, double price) {
    this.time = time;
    this.name = filmName;
    this.seat = seat;
    this.total = price;
  }


  @FXML
  private void initialize() {
//    FilmName.setText(name);
//    FilmTime.setText(time);
    printPDF.setDisable(true);

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

    printPDF.setOnAction((Event) -> {
      try {
        keyReleased();
        PDF("38_4_2");
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    getChange.setOnAction((Event) -> {
      showChange();
      keyReleased();
    });


  }

  /*
  The code below is used for generating a PDF for the receipt.
  This is used for generating the receipt from the ticket chosen.
  Variables are still needed to update the fields.
  */


  @FXML
  public void PDF(String qr_code_file) throws Exception{
    Image img = Image.getInstance("Flask/app/static/qr_codes/" + qr_code_file + ".png");
    img.setAbsolutePosition(300, 500);
    Document document = new Document();
   // byte[] res  = image1.toByteArray();
    try{
      PdfWriter.getInstance(document, new FileOutputStream("Flask/app/static/pdf_tickets/" + qr_code_file + ".pdf"));
      document.open();
      Paragraph ticketText = new Paragraph();

      ticketText.add("\n****************************************************************************************************************");
      ticketText.add("\n                                              QUAIL CINEMA                                                      ");
      ticketText.add("\n                                                                                                                ");
      ticketText.add(String.format("\nMovie:                       %s", name));
      ticketText.add(String.format("\nTime:                         %s", time));
      ticketText.add(String.format("\nSeat (Row/Column):  %s", seat));
      ticketText.add(String.format("\nTotal:                         %s", total));
      ticketText.add("\n");
      ticketText.add("\n");
      ticketText.add("\n");
      ticketText.add("\n");
      ticketText.add("\n");
      ticketText.add("\n");
      ticketText.add("\n");
      ticketText.add("\n");
      ticketText.add("\n");
      ticketText.add("\n");
      ticketText.add("\n****************************************************************************************************************");
      document.add(ticketText);
      document.add(img);
      document.close();
    }catch (IOException e){
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }


  //Disables the use of a button to be clicked on.
  // The Print PDF button is only visible when the Produce Change button has been clicked on.
  @FXML
  public void keyReleased(){
    String text = cashGivenTF.getText();
    boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
    printPDF.setDisable(disableButtons);
  }

  //Allows the change to be viewable in Textfields after purchasing a ticket.
  //(Cash Simulation).
  public void showChange(){
    double holdPayment = Double.valueOf(cashGivenTF.getText().toString());
    double holdingChange = holdPayment - total;
    String holdCash = new Double(holdingChange).toString();
    String amount =  new Double(total).toString();
    changeTF.setText(holdCash);
    amountDueTF.setText(amount);
  }

  public String getTime(){
    return time;
  }
  public String getName(){
    return name;
  }
  public String getSeat(){
    return seat;
  }
  public Double getTotal(){
    return total;
  }

}
