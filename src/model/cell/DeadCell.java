package model.cell;

import java.awt.Rectangle;
import java.util.List;

public class DeadCell extends Cell {
  public DeadCell(int minX, int minY, int width, int height) {
    super(new Rectangle(minX, minY, width, height));
  }

  @Override
  public List<Cell> getNeighbors() {
    return null;
  }

  @Override
  public void updateNeighbors() {

  }
}
