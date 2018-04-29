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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.lang.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**

 * Controller class for the PaymentPage.fxml page
 */

public class PaymentPageController {
  //Set FXML variables that match those in PaymentPage.fxml


  @FXML
  Label Date;
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
  private Button printPDF;

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

    returnHome.setOnAction((Event) -> {
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

    printPDF.setOnAction((Event) -> {
      try {
        keyReleased();
        PDF();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });


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
      ticketText.add("\n******************************************************");
      ticketText.add("\n*                         Ticket                     *");
      ticketText.add("\n*                                                    *");
      ticketText.add(String.format("\n*%s                                    *", time));
      ticketText.add(String.format("\n*%s                                    *", seat));
      ticketText.add(String.format("\n*%s                                    *", total));
      ticketText.add("\n*                                                    *");
      ticketText.add("\n*                                                    *");
      ticketText.add("\n*                                                    *");
      ticketText.add("\n*                                                    *");
      ticketText.add("\n******************************************************");
      document.add(ticketText);
      document.close();
    }catch (IOException e){
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void keyReleased(){
    String text = cashGivenTF.getText();
    boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
    printPDF.setDisable(disableButtons);
  }

}
