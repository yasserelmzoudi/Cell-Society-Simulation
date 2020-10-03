package model;

import java.util.List;
import model.cell.Cell;

public interface NeighboringCells {
  List<Cell> getNeighbors();
  void updateNeighbors();
}
