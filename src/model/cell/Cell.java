package model.cell;

import java.awt.Rectangle;
import java.util.List;
import model.NeighboringCells;

public abstract class Cell implements NeighboringCells {

  private final Rectangle rectangle;

  public Cell(Rectangle rectangle) {

    this.rectangle = rectangle;
  }

  public void update() {
    //TODO
  }

  public String getState() {
    //TODO
    return null;
  }

  public void setState(String state) {
    //TODO
  }

  public int countOfNeighboringStates(List<Cell> neighboringCells, String state) {
    //TODO
    return 0;
  }

  public String getNextState() {
    //TODO
    return null;
  }
}
