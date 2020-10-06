package cellsociety;


import java.util.concurrent.TimeUnit;

import model.grid.Grid;
import view.GUI;
import view.GameSimulator;
import view.GridView;

import javax.swing.*;

/**
 * Gets data from a given file and prints the current state of the cells.
 *
 * <p> When user presses 'N', each cell in the grid updates to the next value based on the Game of
 * Life rules. </p>
 *
 * <p> When user presses 'Q', program quits. </p>
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main(String[] args) {
        String filename = "data/simpleGrid";
        Grid grid = new Grid(filename);
        GridView view = new GridView();
        view.displayGrid(grid);
        JPanel mySimulator = new GameSimulator(grid, 500, 500);
        GUI userinterface = new GUI(grid, mySimulator);
        while (true) {
            if(userinterface.shouldcontinue()) {
                grid.performNextStep();
                mySimulator = new GameSimulator(grid, 500, 500);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(userinterface.wantnewFile()) {
                grid = new Grid(userinterface.chooseNewFile());
                mySimulator = new GameSimulator(grid, 500, 500);
                userinterface.resetGUI(grid, mySimulator);
            }
        }
    }
}
