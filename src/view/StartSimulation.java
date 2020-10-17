package view;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.exceptions.InvalidSimulationTypeException;
import model.grid.GameOfLifeGrid;
import model.grid.Grid;
import model.grid.SimulationSettingsReader;

import java.io.File;
import java.util.Arrays;
import java.util.ResourceBundle;

public class StartSimulation  {
    private static final String RESOURCES = "resources/";
    private static final String DEFAULT_STYLE_FOLDER ="/" + "styleresources/";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
    public static final String PANEL_STYLESHEET = "PanelStyles.css";
    public static final String CONTROL_STYLESHEET = "Control_Styles.css";
    public static final String VISUAL_STYLESHEET = "VisualSceneStyles.css";
    public static final String PANEL_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER + PANEL_STYLESHEET;
    public static final String CONTROL_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER + CONTROL_STYLESHEET;
    public static final String VISUAL_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER + VISUAL_STYLESHEET;
    private static final File DATA_DIRECTORY = new File("./data/");
    private static final String EXCEPTION_RESOURCE = "resources.exceptionMessages";
    private static final String PATH = "/resources/randomizedSimulation.properties";



    private SimulationSettingsReader simulationSettingsReader;
    private InputStream simulationData;
    private Class<?> gridType;
    private ResourceBundle errorMessageSource;
    private Grid grid;
    private Stage primaryStage;
    private Timeline animation;
    private ScreenVisuals root;

    private int windowWidth =0;
    private int windowHeight =0;

    private String edgePolicy;
    private String neighborhoodPolicy;

    private Class<?> gridParameters;

    public StartSimulation(Stage stage, int winWidth, int winHeight) {
        windowWidth = winWidth;
        windowHeight = winHeight;
        start(stage);

    }

    public void start(Stage stage){
        errorMessageSource = ResourceBundle.getBundle(EXCEPTION_RESOURCE);
        simulationSettingsReader = new SimulationSettingsReader(PATH);
        simulationData = Grid.class.getClassLoader()
                .getResourceAsStream(simulationSettingsReader.getSimulationDataSourceCSV());
        edgePolicy = simulationSettingsReader.getSimulationEdgePolicy();
        neighborhoodPolicy = simulationSettingsReader.getSimulationNeighborhoodPolicy();

        try {
            gridType = Class.forName("model.grid." + simulationSettingsReader.getSimulationType() + "Grid");
            Object gridInstance = gridType.getDeclaredConstructor(
                new Class[]{InputStream.class, String.class, String.class}).newInstance(simulationData, edgePolicy, neighborhoodPolicy);
            grid = (Grid) gridInstance;
        } catch (Exception e) {
            throw new InvalidSimulationTypeException(errorMessageSource.getString("InvalidSimulation"));
        }

        try {
            Method randomizeType = Grid.class.getMethod(simulationSettingsReader.getSimulationRandomization());
            randomizeType.invoke(grid);
        } catch(Exception e) {
            throw new InvalidSimulationTypeException(errorMessageSource.getString("InvalidSimulation"));
        }

        Scene myScene = setUpVisualScene(grid, windowWidth,windowHeight);
        //stage.setTitle(simulationSettingsReader.getSimulationTitle());
        stage.setScene(myScene);
        primaryStage = stage;
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
        KeyFrame frame = new KeyFrame(Duration.seconds(1), e -> {
            try {
                step();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }


    private void newSimulationWindow(Grid newgrid) { //TODO: delete current and change back to uncommented
        primaryStage.close();
        //Stage newstage = new Stage();
        primaryStage = new Stage();
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });
        primaryStage.setScene(setUpVisualScene(newgrid, windowWidth,windowHeight));
        primaryStage.show();
    }


    public Scene setUpVisualScene(Grid newgrid, int width, int height) {
        root = new ScreenVisuals(this, newgrid, width, height, simulationSettingsReader.getSimulationTitle(), "Triangle");
        System.out.println(simulationSettingsReader.getSimulationTitle());
        Scene myscene = new Scene (root, width, height);
        assignStyleSheet(myscene, PANEL_STYLESHEET_PATH);
        assignStyleSheet(myscene, VISUAL_STYLESHEET_PATH);
        assignStyleSheet(myscene, CONTROL_STYLESHEET_PATH);
        return myscene;
    }

    private void assignStyleSheet(Scene scene, String styleSheetPath) {
        scene.getStylesheets().add(getClass().getResource(styleSheetPath).toExternalForm());
    }

    public void step() throws Exception {
        checkNewFile();
        root.checkUserChanges();
        startSimulation();

    }

    public void setAnimationSpeed(double speed) {
        animation.setRate(speed);
    }

    private void checkNewFile() {
        boolean chooseNewFile = root.getMyButtonDisplay().wantNewFile();
        if(chooseNewFile) {
            animation.pause();
            String path = chooseNewFile();
            System.out.println(path);
            if(path.isEmpty()) {
                root.getMyButtonDisplay().resetGUI(grid);
                animation.play();
                return;
            }
            /*InputStream newGridData = Grid.class.getClassLoader().getResourceAsStream(path);
            grid = new GameOfLifeGrid(newGridData);
            newSimulationWindow(grid);
            animation.play();*/
        }

    }

    public void startSimulation() throws Exception {
        boolean shouldresume = root.getMyButtonDisplay().shouldcontinue();
        if (shouldresume) {
            grid.performNextStep();
            root.getMyGamePane().setUpPane(grid);
        }
    }

    private String chooseNewFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(DATA_DIRECTORY);
        File file = fileChooser.showOpenDialog(new Stage());
        if(file!=null) return ((file).getName());
        return "";
    }

}
