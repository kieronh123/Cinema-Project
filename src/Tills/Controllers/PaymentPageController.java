package Tills.Controllers;
//Importing relavent libraries
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
import javafx.event.ActionEvent;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.MalformedURLException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import javafx.beans.value.ChangeListener;


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

  //Setting up of variables
  public String time = null;
  public String name = null;
  public String seat = null;
  public double total = 0;
  public String row = null;
  public String column = null;
  public int screeningID = 0;



  public PaymentPageController( String time, String filmName, String seat, double price, String row, String column, int screeningID) {
    this.time = time;
    this.name = filmName;
    this.seat = seat;
    this.total = price;
    this.row = row;
    this.column = column;
    this.screeningID = screeningID;
  }


      /**
       * Function to initialise page
       *
       */
  @FXML
  private void initialize() {
    printPDF.setDisable(true);
    //Fetching amount and converting it from double to string
    String amount =  new Double(total).toString();
    //Setting text in amount due text box to ticket price
    amountDueTF.setText(amount);
    amountDueTF.setEditable(false);
    changeTF.setEditable(false);

    //When return home button is clicked, return to moviesPage.
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

    //Print the PDF when the print PDF button is selected
    printPDF.setOnAction((Event) -> {
      try {
        keyReleased();
        PDF(screeningID+"_"+row+"_"+column);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    //Calculate the change based on the amount given
    getChange.setOnAction((Event) -> {
      if(isNumeric(new Double(total).toString(),cashGivenTF.getText())) {
        showChange();
        keyReleased();
      }
    });

    amountDueTF.setOnAction((Event) -> {

    });



  }

  /*
  The code below is used for generating a PDF for the receipt.
  This is used for generating the receipt from the ticket chosen.
  Variables are still needed to update the fields.
  */


      /**
       * Generate a PDF using the QR_Code
       *
       * @param qr_code_file  The file name of the qr_code to be embedded in the PDF
       */
  @FXML
  public void PDF(String qr_code_file) throws Exception{
    //Fetching the QR code image
    Image img = Image.getInstance("Flask/app/static/qr_codes/" + qr_code_file + ".png");
    //Setting the position of the QR code in the PDF
    img.setAbsolutePosition(300, 450);
    Document document = new Document();
    try{
      //Writing the text of the PDF
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
      ticketText.add("\n");
      ticketText.add("\n");
      ticketText.add("\n");
      ticketText.add("\n****************************************************************************************************************");
      //Adding text
      document.add(ticketText);
      //Adding QR_Code
      document.add(img);
      document.close();
    }catch (IOException e){
      e.printStackTrace();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

      /**
       * Disables the use of a button to be clicked on.
       *The Print PDF button is only visible when the Produce Change button has been clicked on.
       */
  @FXML
  public void keyReleased(){
    String text = cashGivenTF.getText();
    boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
    printPDF.setDisable(disableButtons);
  }

  /**
   * Allows the change to be viewable in Textfields after purchasing a ticket.
   * (Cash Simulation).
   */
  public void showChange(){
    double holdPayment = Double.valueOf(cashGivenTF.getText().toString());
    double holdingChange = holdPayment - total;
    String holdCash = new Double(holdingChange).toString();

    changeTF.setText(holdCash);

  }

  /**
   * Fetches the time as a string
   * @return String time - the time of the movie
   */
  public String getTime(){
    return time;
  }

  /**
   * Fetches the name as a string
   * @return String name - the name of the movie
   */
  public String getName(){
    return name;
  }

  /**
   * Fetches the seat as a string
   * @return String seat - the time of the movie
   */
  public String getSeat(){
    return seat;
  }

  /**
   * Fetches the total as a double
   * @return Double total - the total cost
   */
  public Double getTotal(){
    return total;
  }

  /**
   * Fetches the row as a string
   * @return String row - the row
   */
  public String getRow(){
    return row;
  }

  /**
   * Fetches the column as a string
   * @return String column - the column
   */
  public String getColumn(){
    return column;
  }

  /**
   * Fetches the screeningID as a string
   * @return int screeningID - the screeningID
   */
  public int getScreeningID(){
    return screeningID;
  }

/**
 * Function to generate qr code and save in qr_codes folder
 *
 * The data that the qr code holds is: scrreningID, row, column of the seat booked
 *
 * @param filename - the filename of the qr code
 *
 *
 */
  public void generateQRCodeImage(String filename){
    String filePath = "Flask/app/static/qr_codes/"+filename+".png";
    int size = 290;
    String fileType = "png";
    File myFile = new File(filePath);
    Hashtable hash = new Hashtable();
    hash.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
    try {


      QRCodeWriter qrCodeWriter = new QRCodeWriter();
      BitMatrix byteMatrix = qrCodeWriter.encode(filename, BarcodeFormat.QR_CODE, size,
      size, hash);

      BufferedImage image = new BufferedImage(size, size,
      BufferedImage.TYPE_INT_RGB);
      image.createGraphics();

      Graphics2D graphics = (Graphics2D) image.getGraphics();
      graphics.setColor(Color.WHITE);
      graphics.fillRect(0, 0, size, size);
      graphics.setColor(Color.BLACK);

      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          if (byteMatrix.get(i, j)) {
            graphics.fillRect(i, j, 1, 1);
          }
        }
      }
      ImageIO.write(image, fileType, myFile);
    } catch (WriterException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("\n\nYou have successfully created QR Code.");
  }

  /**
   * Checks whether or not the input to the amount given text box is correct
   * @param String amountdue - the amount to be paid
   * @param String amountgiven - the amount given to the cashier
   * @return String time - the time of the movie
   */
  public static boolean isNumeric(String amountdue, String amountgiven)
  {
    double d;
    double f;
    try {
      d = Double.parseDouble(amountdue);
      f = Double.parseDouble(amountgiven);
    }
    catch(NumberFormatException nfe) {
      return false;
    }

    if (d > f) {
      return false;
    }
    else {
      return true;
    }
  }
}
