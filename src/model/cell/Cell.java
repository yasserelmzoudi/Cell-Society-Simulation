package model.cell;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;

public abstract class Cell {

  private int row;
  private int column;
  private CellType state;

  /**
   * Constructor for this class.
   *
   * @param row Row in the grid
   * @param column Column in the grid
   * @param state int specifying state of Cell.
   */
  public Cell(int row, int column, int state) {
    this.row = row;
    this.column = column;
    this.state = CellType.values()[state];
  }

  /**
   * Copy constructor that makes a copy of a <code>Cell</code>.
   *
   * @param copyCell A <code>Cell</code> that is copied.
   */
  public Cell(Cell copyCell) {
    this.row = copyCell.row;
    this.column = copyCell.column;
    this.state = copyCell.state;
  }

  /**
   * Updates a <code>Cell</code> based on the neighboring cells. Follows the Game of Life rules.
   * The rules can be found at: https://en.wikipedia.org/wiki/Conway's_Game_of_Life.
   *
   * @param neighbors List of neighboring cells
   */
  public abstract void update(List<Cell> neighbors, List<Cell> newNeighbors, boolean[][] isUpdated);

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public CellType getState() {
    return state;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setColumn(int row) {
    this.column = column;
  }

  public void setCellType(CellType state) {
    this.state = state;
  }



  public Paint stringStatus() {
    if (state.name().equalsIgnoreCase("ALIVE")) {
      return Color.RED;
    }
    else if (state.name().equalsIgnoreCase("DEAD")) {
      return Color.BLACK;
    }
    else if (state.name().equalsIgnoreCase("EMPTY_OPEN")) {
      return Color.WHITE;
    }
    else if (state.name().equalsIgnoreCase("FULL_OPEN")) {
      return Color.BLUE;
    }
    else if (state.name().equalsIgnoreCase("CLOSED")) {
      return Color.BLACK;
    }
    else if (state.name().equalsIgnoreCase("ROCK")) {
      return Color.RED;
    }
    else if (state.name().equalsIgnoreCase("PAPER")) {
      return Color.GREEN;
    }
    else if (state.name().equalsIgnoreCase("O")){
      return Color.RED;
    }
    else if (state.name().equalsIgnoreCase("X")){
      return Color.BLUE;
    }
    else if (state.name().equalsIgnoreCase("NO_RACE")){
      return Color.GREEN;
    }
    else if (state.name().equalsIgnoreCase("TREE")) {
      return Color.GREEN;
    }
    else if (state.name().equalsIgnoreCase("EMPTY")) {
      return Color.YELLOW;
    }
    else if (state.name().equalsIgnoreCase("BURNING")) {
      return Color.RED;
    }
    else if(state.name().equalsIgnoreCase("WATER")) {
      return Color.BLACK;
    }
    else if(state.name().equalsIgnoreCase("FISH")) {
      return Color.TURQUOISE;
    }
    else if(state.name().equalsIgnoreCase("SHARK")) {
      return Color.BLUE;
    }
    return Color.BLACK;
  }
}
