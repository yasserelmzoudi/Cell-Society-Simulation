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

    private Class<?> gridParameters;
    private String currentPath = INITIAL_PATH;

    public Simulation(Stage stage, int winWidth, int winHeight)
            throws ReflectiveOperationException {
        windowWidth = winWidth;
        windowHeight = winHeight;
        primaryStage = stage;
        start();

    }

    public void start() throws ReflectiveOperationException {
        simulationController = initializeSimulation(currentPath);
        grid = simulationController.getGrid();
        setUpVisualScene(grid);
        frameCount = 0;
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
                new ErrorPanel();
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
        root = new ScreenVisuals(this, newgrid, windowWidth, windowHeight, simulationController.getSimulationSettingsReader().getSimulationTitle());
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
        simulationGraph = new SimulationGraph(grid, simulationController.getSimulationSettingsReader().getSimulationTitle());
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
            if(path.isEmpty()) {
                root.resetGUI(grid);
                animation.play();
                return;
            }
            /*InputStream newGridData = Grid.class.getClassLoader().getResourceAsStream(path);
            grid = new GameOfLifeGrid(newGridData, edgePolicy, neighborhoodPolicy);
            newSimulationWindow(grid);*/
            currentPath=path;
            reload();
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

    public SimulationInitializer getSimulationController() {
        return simulationController;
    }

    public void reload() throws ReflectiveOperationException {
        simulationGraph.closeGraphWindow();
        start();
        newSimulationWindow(grid);
    }

}
