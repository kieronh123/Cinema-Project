package Tills.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
  public Label FilmName;

    @FXML
    public void stayOnPage(ActionEvent event) throws IOException {
//      //  Window owner = login.getScene().getWindow();
//        System.out.println("here");
//
//        String username = "calkey";
//        String password = "password";
//        System.out.println(username);
//        boolean logIn = loginPage.loginButton(username, password);
//        if (logIn == false){
//            System.out.println("Try again");
//        }else{
//            StringBuffer movies =  filmTimesPage.filmNamesTimes();
//            Movie[] films = JSON.moviesFromJson(movies.toString());
//
//
//
//            for(Movie movie: films){
//
//            }
//
//            Parent homePage = FXMLLoader.load(getClass().getResource("sample.fxml"));
//            Scene homeScene = new Scene(homePage, 1000, 800);
//            Stage userInterface = (Stage)((Node)event.getSource()).getScene().getWindow();
//            userInterface.setScene(homeScene);
//
//           }
      }

      @FXML
      public void bookTickets(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ticketType.fxml")) ;
        loader.setController(this);
        Parent homePage = loader.load();

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
      public void useReleasedKeyMethod(){
        login.setDisable(true);
        logOut.setDisable(true);
      }


    }
