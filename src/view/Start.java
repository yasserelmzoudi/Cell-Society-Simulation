package view;

import java.util.Arrays;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {
    /**
     * Starts a new Simulation Window
     */
    public void start(Stage stage) {
        new StartSimulation(stage, 600,600);
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
