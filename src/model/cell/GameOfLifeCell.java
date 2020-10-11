package model.cell;

import java.util.List;

/**
 * Subclass encapsulating logic to update cells in the Game of Life simulation.
 * Checks to see how many neighbors are alive with <code>numAliveCells</code> and changes the
 * current cell type using an enum state accordingly.
 */

public class GameOfLifeCell extends Cell {
  private int state;

  /**
   * Constructor for this class.
   *
   * @param row Row of cell.
   * @param column Column of cell.
   * @param state State cell is in.
   */
  public GameOfLifeCell(int row, int column, int state) {
    super(row, column, state);
    this.state = state;
  }

  /**
   * Copy constructor.
   *
   * @param copyCell Makes a copy of the cell to preserve original values.
   */
  public GameOfLifeCell(Cell copyCell) {
    super(copyCell);
  }

  /**
   * Updates cell based on the state of neighboring cells.
   *
   * @param neighbors List of neighboring cells
   * @param newNeighbors List of neighboring cells that may have been updated.
   * @param isUpdated Whether the cell has already been updated or not. If it has been updated,
   *                  skip over the cell.
   */
  @Override
  public void update(List<Cell> neighbors, List<Cell> newNeighbors, boolean[][] isUpdated) {
    int numAliveCells = 0;
    for (Cell neighbor : neighbors) {
      if (neighbor.getState().name().equals("ALIVE")) {
        numAliveCells++;
      }
    }
    if (getState().name().equals("ALIVE")) {
      if (numAliveCells == 3 || numAliveCells == 2) {
        return;
      }
      setCellType(CellType.DEAD);
      return;
    }
    else if (numAliveCells == 3) {
      setCellType(CellType.ALIVE);
    }
  }
}
