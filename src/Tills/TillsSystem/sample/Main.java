package Tills.TillsSystem.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage)throws Exception{
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fileInputStream = new FileInputStream(new File("Tills/TillsSystem/sample/resources/selectedTicket.fxml"));
        Parent root = loader.load(fileInputStream);

        //Parent root = FXMLLoader.load(getClass().getResource("selectedTicket.fxml"));
        primaryStage.setTitle("Quail Cinema");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
