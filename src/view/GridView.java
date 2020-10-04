package view;

import model.cell.Cell;
import model.grid.Grid;


public class GridView {

  /**
   * Displays the current grid.
   *
   * @param grid Grid that is displayed
   */
  public void displayGrid(Grid grid) {

    Cell[][] newGrid = grid.getAllCells();
    int numRows =  newGrid.length;
    int numColumns = newGrid[0].length;
    for (int row = 0; row < numRows; row++) {
      for (int column = 0; column < numColumns; column++) {
        System.out.print(newGrid[row][column].isAlive()+ " ");
      }
      System.out.println("");
    }
  }

}
