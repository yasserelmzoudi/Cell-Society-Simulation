package model.cell;

import java.util.List;

public class Cell {

  private final Integer row;
  private final Integer column;
  private boolean isAlive;

  /**
   * Constructor for this class.
   *
   * @param row Row in the grid
   * @param column Column in the grid
   * @param alive Boolean specifying if cell is alive or dead.
   */
  public Cell(Integer row, Integer column, boolean alive) {
    this.row = row;
    this.column = column;
    this.isAlive = alive;
  }

  /**
   * Copy constructor that makes a copy of a <code>Cell</code>.
   *
   * @param copyCell A <code>Cell</code> that is copied.
   */
  public Cell(Cell copyCell) {
    this.row = copyCell.row;
    this.column = copyCell.column;
    this.isAlive = copyCell.isAlive;
  }

  /**
   * Updates a <code>Cell</code> based on the neighboring cells. Follows the Game of Life rules.
   * The rules can be found at: https://en.wikipedia.org/wiki/Conway's_Game_of_Life.
   *
   * @param neighbors List of neighboring cells
   */
  public void update(List<Cell> neighbors) {
    Integer numAliveCells = 0;
    for (Cell neighbor : neighbors) {
      if (neighbor.isAlive) {
        numAliveCells++;
      }
    }
    if (isAlive && (numAliveCells > 3 || numAliveCells < 2)) {
      isAlive = false;
    }
     if (!isAlive && numAliveCells == 3) {
       isAlive = true;
     }
    }

  /**
   * Returns the current state of a cell.
   * If the cell is alive, it returns true.
   * If the cell is dead, it returns false.
   *
   * @return Boolean stating if cell is alive.
   */
  public boolean isAlive() {
      return isAlive;
    }

}
