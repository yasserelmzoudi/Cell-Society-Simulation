package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.grid.Grid;
import model.grid.GridReader;

import javax.swing.*;
import java.io.File;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class GameSimulation extends Application {
    private ResourceBundle resources = ResourceBundle.getBundle("resources.data");
    private GridReader gridReader = new GridReader(GridReader.class.getClassLoader().getResourceAsStream(resources.getString("DataSource")));
    private Grid grid = new Grid(gridReader);
    private GamePane myVisual;
    private GUI userinterface;
    private Stage primaryStage;
    private Timeline animation;
    @Override
    public void start(Stage stage) throws Exception {
        Scene myScene = setupScene(grid);
        stage.setTitle("Simulation");
        stage.setScene(myScene);
        primaryStage = stage;
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
        myVisual.setUpPane(grid);
        return scene;
    }

    private void newScene(Grid newgrid) {
        primaryStage.close();
        primaryStage = new Stage();
        primaryStage.setScene(setupScene(newgrid));
        userinterface.resetGUI(newgrid);
        primaryStage.show();
    }

    private void setupGUI() {
        Scene secondScene = new Scene(userinterface, 230, 200);
        Stage newWindow = new Stage();
        newWindow.setTitle("GUI Buttons");
        newWindow.setScene(secondScene);

        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();

    }

    public void step() {
        checkNewFile();
        startSimulation();
    }

    private void checkNewFile() {

        boolean newfilechosen = userinterface.wantnewFile();
        if(newfilechosen) {
            animation.pause();
            String path = chooseNewFile();
            gridReader = new GridReader(GridReader.class.getClassLoader().getResourceAsStream(path));
            grid = new Grid(gridReader);
            newScene(grid);
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
        File file = fileChooser.showOpenDialog(newWindow);
        return ((file).getName());
    }

}
