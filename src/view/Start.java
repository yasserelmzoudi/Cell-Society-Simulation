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
        launch(args);
    }

}
