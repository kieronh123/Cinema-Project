
package Tills.Controllers;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;

//
/**

 * Controller class for the PaymentPage.fxml page
 */

public class PaymentPageController {
  //Set FXML variables that match those in PaymentPage.fxml

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
  @FXML
  Button returnHome;

  String time = null;
  String name = null;
  String seat = null;

  boolean VIP;



  public PaymentPageController( String time, String filmName, String seat) {
    this.time = time;
    this.name = filmName;
    this.seat = seat;
  }


  @FXML
  private void initialize() {

//    returnHome.setOnAction((Event) -> {
//      try {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../PaymentPage.fxml"));
//
//        Parent parent = (Parent) loader.load();
//        Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
//        window.setScene(new Scene(parent));
//        window.show();
//      } catch (IOException e) {
//        System.err.println("Could not load page");
//      }
//
//
//    });
  }


  /*
  The code below is used for generating a PDF for the receipt.
  This is used for generating the receipt from the ticket chosen.
  Variables are still needed to update the fields.
  */
  @FXML
  public void PDF()throws IOException{
    Document document = new Document();
    try{
      PdfWriter.getInstance(document, new FileOutputStream("Ticket.pdf"));

      document.open();
      Paragraph ticketText = new Paragraph();
      ticketText.add("\n*********************************");
      ticketText.add("\n*           Ticket              *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*                               *");
      ticketText.add("\n*********************************");
      document.add(ticketText);
      document.close();

    }catch (IOException e){
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }



}


