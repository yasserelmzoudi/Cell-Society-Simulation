package view;

import controller.SimulationInitializer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.grid.Grid;

import java.io.File;

public class Simulation {
    private static final String RESOURCES = "resources/";
    private static final String DEFAULT_STYLE_FOLDER ="/" + "StyleResources/";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
    public static final String PANEL_STYLESHEET = "CellColorStyles.css";
    public static final String CONTROL_STYLESHEET = "UserButtonStyles.css";
    public static final String VISUAL_STYLESHEET = "VisualSceneStyles.css";
    public static final String PANEL_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER + PANEL_STYLESHEET;
    public static final String CONTROL_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER + CONTROL_STYLESHEET;
    public static final String VISUAL_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER + VISUAL_STYLESHEET;
    private static final File DATA_DIRECTORY = new File("src/resources/");
    private static final String EXCEPTION_RESOURCE = "resources.exceptionMessages";
    private static final String INITIAL_PATH = "/resources/initialSimulationSettings.properties";


    private Grid grid;
    private Stage primaryStage;
    private Timeline animation;
    private ScreenVisuals root;

    private String edgePolicy;
    private String neighborhoodPolicy;

    private int windowWidth;
    private int windowHeight;

    private SimulationInitializer simulationController;

    private int frameCount = 0;


    private SimulationGraph simulationGraph;
    private String currentPath = INITIAL_PATH;

    /**
     * Constructor of class
     * @param stage: the window where the application should start
     * @param winWidth: the widthof the window
     * @param winHeight: the height of the window
     *
     */
    public Simulation(Stage stage, int winWidth, int winHeight){
        windowWidth = winWidth;
        windowHeight = winHeight;
        primaryStage = stage;
        start();

    }

    private void start() {
        initializeSimulation();
        grid = simulationController.getGrid();
        setUpVisualScene(grid);
        frameCount = 0;
    }


    private void initializeSimulation() {
        try {
            simulationController = new SimulationInitializer(currentPath);
            grid = simulationController.getGrid();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

    }


    private void setUpKeyFrames() {
        primaryStage.show();
        KeyFrame frame = new KeyFrame(Duration.seconds(1), e -> step());
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }



    private void newSimulationWindow() {
        primaryStage.close();
        //Stage newstage = new Stage();
        primaryStage = new Stage();
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });
    }


    private void setUpVisualScene(Grid newgrid){
        root = new ScreenVisuals(this, newgrid, windowWidth, windowHeight, simulationController.getSimulationSettingsReader().getSimulationTitle());
        root.askForUserDialogs();
    }

    /**
     * Adds the visuals to a scene and then to a window
     * Takes in the path of the style that was chosen
     */
    public void setUpScene(String stylePath) {
        Scene myScene = new Scene (root, windowWidth, windowHeight);
        assignStyleSheet(myScene, PANEL_STYLESHEET_PATH);
        assignStyleSheet(myScene, stylePath);
        assignStyleSheet(myScene, CONTROL_STYLESHEET_PATH);
        primaryStage.setScene(myScene);
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        simulationGraph = new SimulationGraph(grid, simulationController.getSimulationSettingsReader().getSimulationTitle());
        setUpKeyFrames();
    }


    private void assignStyleSheet(Scene scene, String styleSheetPath) {
        scene.getStylesheets().add(getClass().getResource(styleSheetPath).toExternalForm());
    }

    /**
     * Steps through the simulation based on the keyframe
     */
    public void step() {
        checkNewFile();
        root.checkUserOptionsChosen();
        startSimulation();

    }

    /**
     * Sets the animation speed
     * @param speed: the new speed of the animation
     */
    public void setAnimationSpeed(double speed) {
        animation.setRate(speed);
    }

    private void checkNewFile()  {
        boolean chooseNewFile =root.wantNewFile();
        if(chooseNewFile) {
            animation.pause();
            String path = "/resources/" + chooseNewFile();
            if(path.equals("/resources/")){
                root.resetGUI(grid);
                animation.play();
                return;
            }
            /*InputStream newGridData = Grid.class.getClassLoader().getResourceAsStream(path);
            grid = new GameOfLifeGrid(newGridData, edgePolicy, neighborhoodPolicy);
            newSimulationWindow(grid);*/
            currentPath=path;
            loadNewSimulation();
        }

    }

    private void startSimulation() {
        boolean shouldresume = root.shouldContinue();
        if (shouldresume) {
            primaryStage.show();
            grid.performNextStep();
            simulationGraph.updateSimulationGraph(frameCount, grid.getTotalCellTypeCounts());
            grid.resetCellTypeCounts();
            frameCount++;
            root.getMyGamePane().setUpPane(grid);
        }
    }

    /**
     * Getter method for simulation graph
     * Returns the current simulation graph
     */
    public SimulationGraph getSimulationGraph() {
        return simulationGraph;
    }

    private String chooseNewFile() {
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Simulation files (*.properties)", "*.properties");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setInitialDirectory(DATA_DIRECTORY);
        File file = fileChooser.showOpenDialog(new Stage());
        if(file!=null) return ((file).getName());
        return "";
    }

    /**
     * Getter method for the simulation controller
     * Returns the current simulation Controller
     */
    public SimulationInitializer getSimulationController() {
        return simulationController;
    }


    private void loadNewSimulation() {
        simulationGraph.closeGraphWindow();
        newSimulationWindow();
        start();
    }

    /**
     * Reloads the current path so that it restarts from step one without the need to choose a new language or shape
     */
    public void reloadInitialPane() {
        boolean continueShowingGraph = simulationGraph.graphIsShowing();
        simulationGraph.closeGraphWindow();
        initializeSimulation();
        frameCount = 0;
        simulationGraph = new SimulationGraph(grid,simulationController.getSimulationSettingsReader().getSimulationTitle());
        if(continueShowingGraph) simulationGraph.showGraph();
        setUpKeyFrames();
        root.getMyGamePane().setUpPane(grid);
    }



}
