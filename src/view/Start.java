package view;

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

    }

    /**
     * Starts the program
     */
    public static void main(String[] args) {
        launch(args);
    }

}
