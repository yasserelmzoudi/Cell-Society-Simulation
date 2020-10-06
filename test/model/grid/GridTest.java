package model.grid;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import model.cell.Cell;
import org.junit.jupiter.api.Test;

class GridTest {

  @Test
  void getNeighbors_center() {
    ResourceBundle resources = ResourceBundle.getBundle("resources.data");
    GridReader gridReader = new GridReader(
        GridReader.class.getClassLoader().getResourceAsStream(resources.getString("DataSource")));
    Grid grid = new Grid(gridReader);

    Cell[][] matrix= grid.copyGrid();
    List<Cell> neighbors = grid.getNeighbors(matrix, 1,1);

    List<Boolean> expectedNeighbors = List.of(true, false, true, false, true, true, true, true);
    List<Boolean> actualNeighbors = new ArrayList<>();
    for (Cell cell : neighbors) {
      actualNeighbors.add(cell.isAlive());
    }

    assertEquals(expectedNeighbors, actualNeighbors);
  }

  @Test
  void getNeighbors_topLeftCorner() {
    ResourceBundle resources = ResourceBundle.getBundle("resources.data");
    GridReader gridReader = new GridReader(
        GridReader.class.getClassLoader().getResourceAsStream(resources.getString("DataSource")));
    Grid grid = new Grid(gridReader);

    Cell[][] matrix= grid.copyGrid();
    List<Cell> neighbors = grid.getNeighbors(matrix, 0,0);

    List<Boolean> expectedNeighbors = List.of(false, false, true);
    List<Boolean> actualNeighbors = new ArrayList<>();
    for (Cell cell : neighbors) {
      actualNeighbors.add(cell.isAlive());
    }

    assertEquals(expectedNeighbors, actualNeighbors);
  }

  @Test
  void getNeighbors_topRightCorner() {
    ResourceBundle resources = ResourceBundle.getBundle("resources.data");
    GridReader gridReader = new GridReader(
        GridReader.class.getClassLoader().getResourceAsStream(resources.getString("DataSource")));
    Grid grid = new Grid(gridReader);

    Cell[][] matrix= grid.copyGrid();
    List<Cell> neighbors = grid.getNeighbors(matrix, 0,2);

    List<Boolean> expectedNeighbors = List.of(false, true, true);
    List<Boolean> actualNeighbors = new ArrayList<>();
    for (Cell cell : neighbors) {
      actualNeighbors.add(cell.isAlive());
    }

    assertEquals(expectedNeighbors, actualNeighbors);
  }

  @Test
  void getNeighbors_bottomLeftCorner() {
    ResourceBundle resources = ResourceBundle.getBundle("resources.data");
    GridReader gridReader = new GridReader(
        GridReader.class.getClassLoader().getResourceAsStream(resources.getString("DataSource")));
    Grid grid = new Grid(gridReader);

    Cell[][] matrix= grid.copyGrid();
    List<Cell> neighbors = grid.getNeighbors(matrix, 2,0);

    List<Boolean> expectedNeighbors = List.of(false, true, true);
    List<Boolean> actualNeighbors = new ArrayList<>();
    for (Cell cell : neighbors) {
      actualNeighbors.add(cell.isAlive());
    }

    assertEquals(expectedNeighbors, actualNeighbors);
  }

  @Test
  void getNeighbors_bottomRightCorner() {
    ResourceBundle resources = ResourceBundle.getBundle("resources.data");
    GridReader gridReader = new GridReader(
        GridReader.class.getClassLoader().getResourceAsStream(resources.getString("DataSource")));
    Grid grid = new Grid(gridReader);

    Cell[][] matrix= grid.copyGrid();
    List<Cell> neighbors = grid.getNeighbors(matrix, 2,2);

    List<Boolean> expectedNeighbors = List.of(true, true, true);
    List<Boolean> actualNeighbors = new ArrayList<>();
    for (Cell cell : neighbors) {
      actualNeighbors.add(cell.isAlive());
    }

    assertEquals(expectedNeighbors, actualNeighbors);
  }
}