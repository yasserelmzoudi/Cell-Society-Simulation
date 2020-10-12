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
import model.grid.GameOfLifeGrid;
import model.grid.Grid;

import java.io.File;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import model.grid.PercolationGrid;
import model.grid.PredatorPreyGrid;
import model.grid.RockPaperScissorsGrid;
import model.grid.SegregationGrid;
import model.grid.SpreadingOfFireGrid;

public class GameSimulation extends Application {
    private static final String RESOURCES = "resources/";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
    public static final String PANEL_STYLESHEET = "PanelStyles.css";
    public static final String GUI_STYLESHEET = "GUI_Styles.css";
    public static final String PANEL_STYLESHEET_PATH = DEFAULT_RESOURCE_FOLDER + PANEL_STYLESHEET;
    public static final String GUI_STYLESHEET_PATH = DEFAULT_RESOURCE_FOLDER + GUI_STYLESHEET;


    private ResourceBundle resources = ResourceBundle.getBundle("resources.data");
    private InputStream data = Grid.class.getClassLoader().getResourceAsStream(resources.getString("DataSource"));
    //private Grid grid = new GameOfLifeGrid(data);
    private Grid grid = new SegregationGrid(data);
    private GamePane myVisual;
    private GUI userinterface;
    private Stage primaryStage;
    private Timeline animation;
    @Override
    public void start(Stage stage) {
        Scene myScene = setupScene(grid);
        stage.setTitle("Simulation");
        stage.setScene(myScene);
        primaryStage = stage;
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
        userinterface = new GUI(myVisual, grid);
        setupGUI();
        KeyFrame frame = new KeyFrame(Duration.seconds(1), e -> step());
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }


    private Scene setupScene(Grid grid){
        myVisual = new GamePane(grid, 500,500);
        Scene scene = new Scene(myVisual, 500, 500);
        scene.getStylesheets().add(getClass().getResource(PANEL_STYLESHEET_PATH).toExternalForm());
        myVisual.setUpPane(grid);
        return scene;
    }

    private void newSimulationWindow(Grid newgrid) {
        primaryStage.close();
        primaryStage = new Stage();
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });
        primaryStage.setScene(setupScene(newgrid));
        userinterface.resetGUI(newgrid);
        primaryStage.show();
    }

    private void setupGUI() {
        Scene secondScene = new Scene(userinterface, 230, 200);
        secondScene.getStylesheets().add(getClass().getResource(GUI_STYLESHEET_PATH).toExternalForm());
        Stage guiWindow = new Stage();
        guiWindow.setOnCloseRequest(e -> {
            System.exit(0);
        });
        guiWindow.setTitle("GUI Buttons");
        guiWindow.setScene(secondScene);

        guiWindow.setX(primaryStage.getX() + 600);
        guiWindow.setY(primaryStage.getY() + 50);

        guiWindow.show();

    }

    public void step() {
        checkNewFile();
        startSimulation();
    }

    private void checkNewFile() {
        boolean chooseNewFile = userinterface.wantNewFile();
        if(chooseNewFile) {
            animation.pause();
            String path = chooseNewFile();
            if(path.isEmpty()) {
                userinterface.resetGUI(grid);
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
        boolean shouldresume = userinterface.shouldcontinue();
           if (shouldresume) {
               grid.performNextStep();
               myVisual.setUpPane(grid);
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }

    private String chooseNewFile() {
        Stage newWindow = new Stage();
        newWindow.setTitle("File Dialog");
        FileChooser fileChooser = new FileChooser();
        File file;
        file = fileChooser.showOpenDialog(newWindow);
        if(file!=null) return ((file).getName());
        return "";
    }

}
