package cellsociety;


import java.util.concurrent.TimeUnit;

import model.grid.Grid;
import view.GUI;
import view.GameSimulator;
import java.util.ResourceBundle;
import java.util.Scanner;
import model.grid.Grid;
import model.grid.GridReader;
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

    private int appheight = 600;
    private int appwidth = 600;

    public static void main(String[] args) {
        Main runsimulation = new Main();
        ResourceBundle resources = ResourceBundle.getBundle("resources.data");
        GridReader gridReader = new GridReader(GridReader.class.getClassLoader().getResourceAsStream(resources.getString("DataSource")));
        Grid grid = new Grid(gridReader);
        GridView view = new GridView();
        view.displayGrid(grid);
        int appheight = 600;
        int appwidth = 600;
        JPanel mySimulator = new GameSimulator(grid, appheight, appwidth);
        GUI userinterface = new GUI(grid, mySimulator);
        while (true) {
            boolean newfilechosen = userinterface.wantnewFile();
            if(newfilechosen) {
                String path = userinterface.chooseNewFile();
                gridReader = new GridReader(GridReader.class.getClassLoader().getResourceAsStream(path));
                grid = new Grid(gridReader);
                mySimulator = new GameSimulator(grid, appheight, appwidth);
                userinterface.resetGUI(grid, mySimulator);
            }
            boolean resumesimulation = userinterface.shouldcontinue();
            if(resumesimulation) {
                grid.performNextStep();
                mySimulator = new GameSimulator(grid, appheight, appwidth);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  }
