package view;

import java.util.Arrays;
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
import java.io.InputStream;
import java.util.ResourceBundle;

public class Start extends Application {
    /**
     * Starts a new Simulation Window
     */
    public void start(Stage stage) {
        new StartSimulation(stage, 600,600);
        Stage newStage = new Stage();
        new StartSimulation(newStage, 600,600);
    }

    /**
     * Starts the program
     */
    public static void main(String[] args) {

        int[][] grid = new int[30][30];
        for (int i=0; i<grid.length; i++) {
            for (int j=0;j<grid[0].length;j++) {
                grid[i][j] = (int) (Math.random() * 3);
            }
        }
        String str = (Arrays.deepToString(grid).replace("], ", "]\n"));
        str = str.replace("[[","");
        str = str.replace("[","");
        str = str.replace("[[","");
        str = str.replace(" ","");
        str = str.replace("]","");
        System.out.println(str);

        launch(args);
    }

}
