package model.cell;

import java.util.List;
/**
 * Subclass encapsulating logic to update cells in the Percolation simulation.
 * Checks to see if any neighboring cells are of the state <code>FULL_OPEN</code> and changes the
 * current cell type to <code>FULL_OPEN</code> if the current cell type is <code>EMPTY_OPEN</code>
 * using an enum state accordingly.
 */

public class PercolationCell extends Cell {
  private int state;

  /**
   * Constructor for this class.
   *
   * @param row Row of cell.
   * @param column Column of cell.
   * @param state State cell is in.
   */
  public PercolationCell(int row, int column, int state) {
    super(row, column, state);
    this.state = state;
  }

  /**
   * Copy constructor.
   *
   * @param copyCell Makes a copy of the cell to preserve original values.
   */
  public PercolationCell(Cell copyCell) {
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
    boolean changeState = false;
    for (Cell neighbor : neighbors) {
      if (neighbor.getState().name().equals("FULL_OPEN")) {
        changeState = true;
      }
    }

    if ((getState().name().equals("EMPTY_OPEN")) && changeState) {
      setCellType(CellType.FULL_OPEN);
    }
  }
}
