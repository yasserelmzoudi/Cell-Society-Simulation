package cellsociety;

import java.util.Scanner;
import model.grid.Grid;
import view.GridView;

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
    public static void main (String[] args) {
        String filename = "data/simpleGrid";
        Grid grid = new Grid(filename);
        GridView view = new GridView();
        view.displayGrid(grid);

        Scanner myObj = new Scanner(System.in);
        while (true) {
            System.out.println("Press 'N' to compute next step, 'Q' to quit");
            String key = myObj.nextLine();
            if (key.equals("N")) {
                grid.performNextStep();
                view.displayGrid(grid);
            }
            if (key.equals("Q")) {
                System.exit(0);
            }
        }
    }
}
