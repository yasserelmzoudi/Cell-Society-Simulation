package view;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.grid.Grid;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.InputStream;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTestNew extends ApplicationTest{
 private static final ResourceBundle OBJECT_ID_BUNDLE = ResourceBundle.getBundle("StyleResources.ObjectID");
    private static final String DEFAULT_STYLE_FOLDER ="/" + "StyleResources/";
    public static final String STYLE_STYLESHEET = "Normal.css";
    public static final String STYLE_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER + STYLE_STYLESHEET;
    private static final String INITIAL_PATH = "/resources/initi" +
            "alSimulationSettings.properties";

    ResourceBundle resources;
    InputStream data;
    private ScreenVisuals myVisuals;
    private Simulation myTestSimulation;
    private int gridHeight =700;
    private int gridWidth = 700;


    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        resources=  ResourceBundle.getBundle("resources.data");
        data = Grid.class.getClassLoader()
                .getResourceAsStream("Percolation.csv");
        myTestSimulation =new Simulation(stage, gridWidth, gridHeight);
        //myTestSimulation =new Simulation(stage, gridWidth, gridHeight);
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
        setAnimationSpeed();
        stepTest();
        checkSimulationGraph();
        reloadInitialPane();

        pressVariousButtons();


    }

    void stepTest() {

        assertTrue(myTestSimulation.getAnimationSpeed() >0);

        pressOtherButton("LoadButton");

        assertTrue(myTestSimulation.getAnimationSpeed() ==0);

        myVisuals.unitPreformed(myTestSimulation.getSimulationController().getGrid());


    }

    void setAnimationSpeed() {

        assertTrue(myTestSimulation.getAnimationSpeed() >0);

        myTestSimulation.setAnimationSpeed(0);

        assertTrue(myTestSimulation.getAnimationSpeed() ==0);

        myTestSimulation.setAnimationSpeed(4.0);
        assertTrue(myTestSimulation.getAnimationSpeed() ==4.0);

        myTestSimulation.setAnimationSpeed(1.0);
        assertTrue(myTestSimulation.getAnimationSpeed() ==1.0);


    }




    void reloadInitialPane() {

        for(int i=0; i< 5; i++) {
            pressOtherButton("NextButton");
        }
        int previousFrame= myTestSimulation.getFrameCount();
        assertTrue(previousFrame >0);
        pressOtherButton("ResetButton");
        int newFrame = myTestSimulation.getFrameCount();
        assertTrue(newFrame < previousFrame);

    }


    void checkSimulationGraph() {

        SimulationGraph myGraph = myTestSimulation.getSimulationGraph();
        assertTrue(myGraph !=null);

        assertTrue(!myGraph.graphIsShowing());

        pressOtherButton("GraphButton");

        assertTrue(myGraph.graphIsShowing());

    }

    void openNewFile() {
        pressOtherButton("LoadButton");
        assertTrue(myVisuals.wantNewFile());

    }

    void pressVariousButtons() {
        pressOtherButton("ResetButton");
        openNewFile();
        pressOtherButton("SaveButton");
        int previousFrame = myTestSimulation.getFrameCount();
        pressOtherButton("PauseButton");
        assertEquals(previousFrame, myTestSimulation.getFrameCount());
        pressOtherButton("ResumeButton");
        pressOtherButton("SaveButton");
        produceErroroPanel();
       pressOtherButton("QuitButton");
    }

    void produceErroroPanel() {
        press(KeyCode.TAB);
        press(KeyCode.TAB);
        pressOtherButton("OkButton");
    }


    private void chooseUserOptions() {



        ComboBox myShapeBox = lookup("#ShapeTranslation").query();
       /* clickOn(myShapeBox);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);


        while(myShapeBox.getSelectionModel().isEmpty()) {
            continue;
        }*/

        ComboBox myStyleBox = lookup("#StyleTranslation").query();
        /*clickOn(myStyleBox);
        press(KeyCode.DOWN);
        press(KeyCode.ENTER);*/

        while(myStyleBox.getSelectionModel().isEmpty() || myShapeBox.getSelectionModel().isEmpty()) {
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
}