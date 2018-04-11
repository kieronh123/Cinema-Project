package Tills.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MoviePageController {

    @FXML
    Label label1;

    @FXML
    private void initialize(){
        label1.setText("Test text");
    }
}
