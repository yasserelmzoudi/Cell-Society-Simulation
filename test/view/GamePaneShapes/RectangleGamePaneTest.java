package view.GamePaneShapes;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.cell.Cell;
import model.grid.GameOfLifeGrid;
import model.grid.Grid;
import model.grid.PercolationGrid;
import model.grid.SpreadingOfFireGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Shapes.Hexagon;
import view.Shapes.Triangle;

import java.io.InputStream;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class RectangleGamePaneTest {
  private Grid grid;
  ResourceBundle resources = ResourceBundle.getBundle("resources.data");
  InputStream data = Grid.class.getClassLoader()
          .getResourceAsStream("Percolation.csv");
  private RectangleGamePane testPane;
  private int gridHeight =700;
  private int gridWidth = 700;

  @BeforeEach
  public void setup() {

    grid = new PercolationGrid(data, "Finite", "Cardinal");
    testPane = new RectangleGamePane(grid, gridWidth, gridHeight);
  }


  @Test
  public void rectanglesMade() {
    assertTrue(testPane.getInitialArray() !=null);
    testPane.setUpPane(grid);
    assertTrue(testPane.getInitialArray()[0][0] instanceof Rectangle);

    assertEquals(testPane.cellHeight(), ((Rectangle) testPane.getInitialArray()[0][0]).getHeight());
    assertEquals(testPane.cellWidth(), ((Rectangle) testPane.getInitialArray()[0][0]).getWidth());

  }

}