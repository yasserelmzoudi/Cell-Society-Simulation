package cellsociety;

import java.util.ResourceBundle;
import java.util.Scanner;
import model.grid.Grid;
import model.grid.GridReader;
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
  public static void main(String[] args) {
    ResourceBundle resources = ResourceBundle.getBundle("resources.data");
    GridReader gridReader = new GridReader(
        GridReader.class.getClassLoader().getResourceAsStream(resources.getString("DataSource")));
    Grid grid = new Grid(gridReader);

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
