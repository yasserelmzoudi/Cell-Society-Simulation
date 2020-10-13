package view;

import java.io.InputStream;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.exceptions.InvalidSimulationTypeException;
import model.grid.GameOfLifeGrid;
import model.grid.Grid;
import model.grid.SimulationSettingsReader;

import java.io.File;
import java.util.ResourceBundle;

public class StartSimulation extends Application {
    private static final String RESOURCES = "resources/";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
    public static final String PANEL_STYLESHEET = "PanelStyles.css";
    public static final String CONTROL_STYLESHEET = "Control_Styles.css";
    public static final String PANEL_STYLESHEET_PATH = DEFAULT_RESOURCE_FOLDER + PANEL_STYLESHEET;
    public static final String CONTROL_STYLESHEET_PATH = DEFAULT_RESOURCE_FOLDER + CONTROL_STYLESHEET;
    private static final File DATA_DIRECTORY = new File("./data/");
    public static final String VISUAL_STYLESHEET = "VisualSceneStyles.css";
    public static final String VISUAL_STYLESHEET_PATH = DEFAULT_RESOURCE_FOLDER + VISUAL_STYLESHEET;
    private static final String EXCEPTION_RESOURCE = "resources.exceptionMessages";

   private Grid grid;
    private Stage primaryStage;
    private Timeline animation;
    private ScreenVisuals root;
    private SimulationSettingsReader simulationSettingsReader;
    private InputStream simulationData;
    private Class<?> gridType;
    private ResourceBundle errorMessageSource;

    private int  i =0;
    private int windowWidth =600;
    private int windowHeight =600;
    @Override
    public void start(Stage stage) {
        errorMessageSource = ResourceBundle.getBundle(EXCEPTION_RESOURCE);
        simulationSettingsReader = new SimulationSettingsReader();
        simulationData = Grid.class.getClassLoader()
                .getResourceAsStream(simulationSettingsReader.getSimulationDataSourceCSV());
        System.out.println(simulationSettingsReader.getSimulationType());
        try {
            gridType = Class.forName("model.grid." + simulationSettingsReader.getSimulationType() + "Grid");
            Object gridInstance = gridType.getDeclaredConstructor(InputStream.class).newInstance(simulationData);
            grid = (Grid) gridInstance;
        } catch (Exception e) {
            throw new InvalidSimulationTypeException(errorMessageSource.getString("InvalidSimulation"));

        }
        Scene myScene = setUpVisualScene(grid, windowWidth,windowHeight);
        stage.setTitle("Simulation");
        stage.setScene(myScene);
        //root.getMyGamePane().setUpPane(grid);
        primaryStage = stage;
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        KeyFrame frame = new KeyFrame(Duration.seconds(1), e -> step());
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
        stage.show();
    }


    private void newSimulationWindow(Grid newgrid) { //TODO: delete current and change back to uncommented
        //primaryStage.close();
        Stage newstage = new Stage();
        //primaryStage = new Stage();

        newstage.setOnCloseRequest(e -> {
            System.exit(0);
        });
        newstage.setScene(setUpVisualScene(newgrid, windowWidth,windowHeight));
        newstage.show();
    }

    public Scene setUpVisualScene(Grid newgrid, int width, int height) {
        root = new ScreenVisuals(this, "GAME_OF_LIFE", newgrid, width, height);
        Scene myscene = new Scene (root, width, height);
        assignStyleSheet(myscene, PANEL_STYLESHEET_PATH);
        assignStyleSheet(myscene, VISUAL_STYLESHEET_PATH);
        assignStyleSheet(myscene, CONTROL_STYLESHEET_PATH);
        return myscene;
    }

    private void assignStyleSheet(Scene scene, String styleSheetPath) {
        scene.getStylesheets().add(getClass().getResource(styleSheetPath).toExternalForm());
    }

    public void step() {
        checkNewFile();
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
            if(path.isEmpty()) {
                root.getMyButtonDisplay().resetGUI(grid);
                animation.play();
                return;
            }
            InputStream newGridData = Grid.class.getClassLoader().getResourceAsStream(path);
            grid = new GameOfLifeGrid(newGridData);
            newSimulationWindow(grid);
            animation.play();
        }

    }

    public void startSimulation(){
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
