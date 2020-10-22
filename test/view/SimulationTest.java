/*
package view;

import controller.SimulationInitializer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.grid.Grid;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testng.annotations.AfterTest;
import view.GamePaneShapes.RectangleGamePane;

import java.io.InputStream;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest extends ApplicationTest{
 private static final ResourceBundle OBJECT_ID_BUNDLE = ResourceBundle.getBundle("StyleResources.ObjectID");
    private static final String DEFAULT_STYLE_FOLDER ="/" + "StyleResources/";
    public static final String STYLE_STYLESHEET = "Normal.css";
    public static final String STYLE_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER + STYLE_STYLESHEET;
    private static final String INITIAL_PATH = "/resources/initi" +
            "alSimulationSettings.properties";

    // Grid grid;
    ResourceBundle resources;
    InputStream data;
    //private RectangleGamePane testPane;
    private ScreenVisuals myVisuals;
    private Simulation myTestSimulation;
    private int gridHeight =700;
    private int gridWidth = 700;

   // private ComboBox myLangBox;
   // private ComboBox myStyleBox;
   // private ComboBox myShapeBox;


    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        resources=  ResourceBundle.getBundle("resources.data");
        data = Grid.class.getClassLoader()
                .getResourceAsStream("Percolation.csv");
        myTestSimulation =new Simulation(stage, gridWidth, gridHeight);
        // myVisuals = new ScreenVisuals(new Simulation(stage, g
        // ridWidth, gridHeight), grid, gridWidth, gridHeight,"");

    }




    @Test
    void setUpScene() {

        chooseLang();
        myVisuals = myTestSimulation.getRoot();

        assertTrue(myVisuals.getMyGamePane() ==null);

        chooseUserOptions();

        assertTrue(myVisuals.getMyGamePane() !=null);
       for(int i = 100; i <30; i++ ) {
           continue;
       }

    }

    @Test
    void stepTest() {
        chooseLang();
        chooseUserOptions();

        assertTrue(myTestSimulation.getAnimationSpeed() >0);

        pressOtherButton("LoadButton");

        assertTrue(myTestSimulation.getAnimationSpeed() ==0);

    }


    private void chooseUserOptions() {


        ComboBox myStyleBox = lookup("#StyleTranslation").query();
        clickOn(myStyleBox);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);//*

        while(myStyleBox.getSelectionModel().isEmpty()) {
            continue;
        }

        ComboBox myShapeBox = lookup("#ShapeTranslation").query();
        clickOn(myShapeBox);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);
//*
        while(myShapeBox.getSelectionModel().isEmpty()) {
            continue;
        }
        Button okButton = lookup("#"+OBJECT_ID_BUNDLE.getString("OkButton")).queryButton();
        clickOn(okButton);
    }

    private void chooseLang() {
        ComboBox myLangBox = lookup("#Language").queryComboBox();
        clickOn(myLangBox);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);

        Button okButton = lookup("#"+OBJECT_ID_BUNDLE.getString("OkButton")).queryButton();
        clickOn(okButton);

        while( myTestSimulation.getRoot() ==null || myTestSimulation.getRoot().getMyOtherOptions() ==null || myTestSimulation.getRoot().getMyOtherOptions().size() <2 ) {
            continue;
        }

    }

    private void pressOtherButton(String queryLabel) {
        Button newButton = lookup("#"+OBJECT_ID_BUNDLE.getString(queryLabel)).queryButton();
        clickOn(newButton);
    }

    private void setUpOptions() {
        chooseLang();
        chooseUserOptions();
    }
}*/
