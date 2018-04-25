package Tills;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import sun.security.tools.keytool.Main;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MainTest extends ApplicationTest {

    @Override
    public void start (Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("Receipt.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    //Before and after fields for what testFX should do before and after the tests have ran
    @Before
    public void setUp () throws Exception {
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


//    @Test
//    public void testTimeButton () {
//        Label label = (Label) GuiTest.find("#label");
//
//        clickOn("#button0");
//        write("Test Button");
//        clickOn("#");
//        assertThat(label.getText(), is("Test Button"));
//    }


    //Testing that the field takes Numerical values
    @Test
    public void testPrice () {
        Label label = (Label) GuiTest.find("#label");

        clickOn("#cashToGive");
        write("12345678910");
        clickOn("#PDFButton");
        assertThat(label.getText(), is("12345678910"));
    }

}