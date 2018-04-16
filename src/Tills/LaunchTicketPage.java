package Tills;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * Created by sc16km on 11/04/18.
 */
public class LaunchTicketPage extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ticketType.fxml"));
        primaryStage.setTitle("Quail Cinema");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
