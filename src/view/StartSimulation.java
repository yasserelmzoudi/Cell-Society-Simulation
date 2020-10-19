package view;

import controller.SimulationInitializer;
import java.io.InputStream;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.exceptions.InvalidCSVFileException;
import model.exceptions.InvalidSimulationSettingsFileException;
import model.exceptions.InvalidSimulationTypeException;
import model.grid.GameOfLifeGrid;
import model.grid.Grid;

import java.io.File;

public class StartSimulation  {
    private static final String RESOURCES = "resources/";
    private static final String DEFAULT_STYLE_FOLDER ="/" + "StyleResources/";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
    public static final String PANEL_STYLESHEET = "CellColorStyles.css";
    public static final String CONTROL_STYLESHEET = "UserButtonStyles.css";
    public static final String VISUAL_STYLESHEET = "VisualSceneStyles.css";
    public static final String PANEL_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER + PANEL_STYLESHEET;
    public static final String CONTROL_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER + CONTROL_STYLESHEET;
    public static final String VISUAL_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER + VISUAL_STYLESHEET;
    private static final File DATA_DIRECTORY = new File("./data/");
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

    private Class<?> gridParameters;

    public StartSimulation(Stage stage, int winWidth, int winHeight)
        throws ReflectiveOperationException {
        windowWidth = winWidth;
        windowHeight = winHeight;
        start(stage);

    }

    public void start(Stage stage) throws ReflectiveOperationException {
        /*errorMessageSource = ResourceBundle.getBundle(EXCEPTION_RESOURCE);
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
        }*/
       /* try {
            simulationController = new CellSocietyController();
        } catch (InvalidSimulationTypeException | InvalidCSVFileException | InvalidSimulationSettingsFileException e){
            new ErrorPanel();
        }
        grid = simulationController.getGrid();
        primaryStage =stage;
        setUpVisualScene(grid);*/
        simulationController = initializeSimulation(INITIAL_PATH);
        grid = simulationController.getGrid();
        primaryStage = stage;
        setUpVisualScene(grid);
    }

    private SimulationInitializer initializeSimulation(String path) throws ReflectiveOperationException {
        try {
            simulationController = new SimulationInitializer(path);
        } catch (InvalidSimulationTypeException | InvalidCSVFileException | InvalidSimulationSettingsFileException e){
            new ErrorPanel();
        }
        return simulationController;
    }

    private void setUpKeyFrames() {
        primaryStage.show();
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
    


    private void newSimulationWindow(Grid newGrid) {
        primaryStage.close();
        //Stage newstage = new Stage();
        primaryStage = new Stage();
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });
        setUpVisualScene(newGrid);
    }


    public void setUpVisualScene(Grid newgrid){
        root = new ScreenVisuals(this, newgrid, windowWidth, windowHeight, simulationController.getSimulationTitle());
    }

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
        simulationGraph = new SimulationGraph(grid, simulationController.getSimulationTitle());
        setUpKeyFrames();
}
    

    private void assignStyleSheet(Scene scene, String styleSheetPath) {
        scene.getStylesheets().add(getClass().getResource(styleSheetPath).toExternalForm());
    }

    public void step() throws ReflectiveOperationException {
            checkNewFile();
            root.checkUserOptionsChosen();
            startSimulation();

    }

    public void setAnimationSpeed(double speed) {
        animation.setRate(speed);
    }

    private void checkNewFile() throws ReflectiveOperationException {
        boolean chooseNewFile =root.wantNewFile();
        if(chooseNewFile) {
            animation.pause();
            String path = "/resources/" + chooseNewFile();
            System.out.println(path);
            if(path.isEmpty()) {
                root.resetGUI(grid);
                animation.play();
                return;
            }
            /*InputStream newGridData = Grid.class.getClassLoader().getResourceAsStream(path);
            grid = new GameOfLifeGrid(newGridData, edgePolicy, neighborhoodPolicy);
            newSimulationWindow(grid);*/

            simulationController = initializeSimulation(path);
            grid = simulationController.getGrid();
            newSimulationWindow(grid);
            frameCount = 0;
        }

    }

    public void startSimulation() {
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

    private String chooseNewFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(DATA_DIRECTORY);
        File file = fileChooser.showOpenDialog(new Stage());
        if(file!=null) return ((file).getName());
        return "";
    }

    public SimulationInitializer getSimulationController() {
        return simulationController;
    }

}
