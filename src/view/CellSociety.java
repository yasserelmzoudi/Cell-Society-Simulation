package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class CellSociety extends Application {
    /**
     * Starts a new Simulation Window
     */
    public void start(Stage stage) {
        new Simulation(stage, 600,600);
    }


    /**
     * Starts the program
     */
    public static void main(String[] args) {
        launch(args);
    }

}
