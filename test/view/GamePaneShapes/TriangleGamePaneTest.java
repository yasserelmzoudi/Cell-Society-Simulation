package view.GamePaneShapes;

import javafx.scene.shape.Rectangle;
import model.grid.Grid;
import model.grid.PercolationGrid;
import model.grid.PredatorPreyGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Shapes.Triangle;

import java.io.InputStream;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TriangleGamePaneTest {
  private Grid grid;
  ResourceBundle resources = ResourceBundle.getBundle("resources.data");
  InputStream data = Grid.class.getClassLoader()
          .getResourceAsStream("PredatorPrey.csv");
  private TriangleGamePane testPane;
  private int gridHeight =700;
  private int gridWidth = 700;

  @BeforeEach
  public void setup() {

    grid = new PredatorPreyGrid(data, "Finite", "Cardinal");
    testPane = new TriangleGamePane(grid, gridWidth, gridHeight);
  }


  @Test
  public void trianglesMade() {
    assertTrue(testPane.getInitialArray() !=null);
    testPane.setUpPane(grid);
    assertTrue(testPane.getInitialArray()[0][0] instanceof Triangle);

    assertEquals(testPane.cellWidth()/ 1.5, ((Triangle) testPane.getInitialArray()[0][0]).getBase());
    assertEquals(testPane.cellHeight(), ((Triangle) testPane.getInitialArray()[0][0]).getHeight());

  }

}