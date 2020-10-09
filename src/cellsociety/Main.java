package cellsociety;


import java.awt.*;
import java.util.concurrent.TimeUnit;

import model.grid.Grid;
import view.GUI;
import view.GamePanel;
import view.GameWindow;
import java.util.ResourceBundle;

import model.grid.GridReader;

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
        ResourceBundle resources = ResourceBundle.getBundle("resources.data");
        GridReader gridReader = new GridReader(GridReader.class.getClassLoader().getResourceAsStream(resources.getString("DataSource")));
        Grid grid = new Grid(gridReader);
        Dimension appdimensions = new Dimension(600,600);
        GamePanel myPanel = new GamePanel(grid, appdimensions);
        JFrame mySimWindow = new GameWindow(appdimensions);
        mySimWindow.add(myPanel);
        GUI userinterface = new GUI(grid, mySimWindow, myPanel);
        while (true) {
            boolean newfilechosen = userinterface.wantnewFile();
            if(newfilechosen) {
                String path = userinterface.chooseNewFile();
                gridReader = new GridReader(GridReader.class.getClassLoader().getResourceAsStream(path));
                grid = new Grid(gridReader);
                myPanel = new GamePanel(grid, appdimensions);
                mySimWindow.dispose();
                mySimWindow = new GameWindow(appdimensions);
                mySimWindow.add(myPanel);
                userinterface.resetGUI(grid, mySimWindow);
            }
            boolean resumesimulation = userinterface.shouldcontinue();
            //System.out.println(resumesimulation); //Sometimes doesn't work without this line
            if(resumesimulation) {
                grid.performNextStep();
                myPanel.updategrid(grid);
                mySimWindow.add(myPanel);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  }
