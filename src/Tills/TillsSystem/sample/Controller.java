package Tills.TillsSystem.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
  @FXML
  private TextField nameField;
  @FXML
  private Button login;
  @FXML
  private Button logOut;


  @FXML
  public void stayOnPage(ActionEvent event) throws IOException {
    Parent homePage = FXMLLoader.load(getClass().getResource("/sample.fxml"));
    Scene homeScene = new Scene(homePage, 1000, 800);
    Stage userInterface = (Stage)((Node)event.getSource()).getScene().getWindow();
    userInterface.setScene(homeScene);
  }

  @FXML
  public void timesOfFilm(ActionEvent event) throws IOException {
    Parent homePage = FXMLLoader.load(getClass().getResource("/firstFilm.fxml"));
    Scene homeScene = new Scene(homePage, 1000, 800);
    Stage userInterface = (Stage)((Node)event.getSource()).getScene().getWindow();
    userInterface.setScene(homeScene);
  }

  @FXML
  public void backTohomePage(ActionEvent event) throws IOException {
    Parent homePage = FXMLLoader.load(getClass().getResource("/sample.fxml"));
    Scene homeScene = new Scene(homePage, 1000, 800);
    Stage userInterface = (Stage)((Node)event.getSource()).getScene().getWindow();
    userInterface.setScene(homeScene);
  }


  @FXML
  public void buttonClicked(ActionEvent e){
    if (e.getSource().equals(login)){
      System.out.println( nameField.getText()+" is logged in");
    }
    else if (e.getSource().equals(logOut)) {
      System.out.println( nameField.getText()+ " is logged out");
    }
  }

  @FXML
  public void releasedKey(){
    String text = nameField.getText();
    boolean disabledButton = text.isEmpty() || text.trim().isEmpty();
    login.setDisable(disabledButton);
    logOut.setDisable(disabledButton);
  }

  @FXML
  public void useReleasedKeyMethod(){
    login.setDisable(true);
    logOut.setDisable(true);
  }
}
