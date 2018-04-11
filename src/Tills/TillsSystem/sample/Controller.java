package Tills.TillsSystem.sample;

import JSON.JSON;
import Tills.TillsSystem.sample.Movie;
import Tills.TillsSystem.sample.filmTimesPage;
import Tills.TillsSystem.sample.loginPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
  @FXML
  private TextField nameField;
  @FXML
  private TextField Username;
  @FXML
  private Button login;
  @FXML
  private Button logOut;

    @FXML
    public void stayOnPage(ActionEvent event) throws IOException {
      //  Window owner = login.getScene().getWindow();
        System.out.println("here");

        String username = "calkey";
        String password = "password";
        System.out.println(username);
        boolean logIn = loginPage.loginButton(username, password);
        if (logIn == false){
            System.out.println("Try again");
        }else{
            StringBuffer movies =  filmTimesPage.filmNamesTimes();
            Movie[] films = JSON.moviesFromJson(movies.toString());



            for(Movie movie: films){

            }

            Parent homePage = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene homeScene = new Scene(homePage, 1000, 800);
            Stage userInterface = (Stage)((Node)event.getSource()).getScene().getWindow();
            userInterface.setScene(homeScene);

           }
      }

      @FXML
      public void timesOfFilm(ActionEvent event) throws IOException {

        Parent homePage = FXMLLoader.load(getClass().getResource("firstFilm.fxml"));
        Scene homeScene = new Scene(homePage, 1000, 800);
        Stage userInterface = (Stage)((Node)event.getSource()).getScene().getWindow();
        userInterface.setScene(homeScene);
      }

      @FXML
      public void backTohomePage(ActionEvent event) throws IOException {
        Parent homePage = FXMLLoader.load(getClass().getResource("sample.fxml"));
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
