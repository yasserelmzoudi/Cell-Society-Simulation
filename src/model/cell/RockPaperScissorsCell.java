package model.cell;

import java.util.List;
/**
 * Subclass encapsulating logic to update cells in the Rock Paper Scissors simulation.
 * Checks to see how many neighbors are of a state that can beat the current state and if the
 * number is greater than <code>Threshold</code>, it changes the current cell type using an enum
 * state accordingly.
 *
 * @author Umika Paul
 */

public class RockPaperScissorsCell extends Cell {
  private int state;
  private static final int THRESHOLD = 2;

  /**
   * Constructor for this class.
   *
   * @param row Row of cell.
   * @param column Column of cell.
   * @param state State cell is in.
   */
  public RockPaperScissorsCell(int row, int column, int state) {
    super(row, column, state);
    this.state = state;
  }

  /**
   * Copy constructor.
   *
   * @param copyCell Makes a copy of the cell to preserve original values.
   */
  public RockPaperScissorsCell(Cell copyCell) {
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
    int rock = 0;
    int paper = 0;
    int scissors = 0;

    for (Cell neighbor : neighbors) {
      switch (neighbor.getState().name()) {
        case "ROCK" -> rock++;
        case "PAPER" -> paper++;
        case "SCISSORS" -> scissors++;
      }
    }
    changeCellType(rock, paper, scissors);

  }

  /**
   * Based on the number of rock, paper, or scissors surrounding the cell, if the number of objects
   * that can beat the current cell is greater than the <code>THRESHOLD</code>, then the current
   * cell is replaced by this object.
   *
   * @param rock The number of surrounding rocks.
   * @param paper The number of surrounding rocks.
   * @param scissors The number of surrounding rocks.
   */
  private void changeCellType(int rock, int paper, int scissors) {
    if ((getState().name().equals("ROCK")) && (paper > THRESHOLD)) {
      setCellType(CellType.PAPER);
    } else if ((getState().name().equals("PAPER")) && (scissors > THRESHOLD)) {
      setCellType(CellType.SCISSORS);
    } else if ((getState().name().equals("SCISSORS")) && (rock > THRESHOLD)) {
      setCellType(CellType.ROCK);
    }
  }

  public static int getTHRESHOLD() {
    return THRESHOLD;
  }
}
