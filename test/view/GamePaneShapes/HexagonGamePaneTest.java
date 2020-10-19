package view.GamePaneShapes;

import model.grid.Grid;
import model.grid.PredatorPreyGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Shapes.Hexagon;
import view.Shapes.Triangle;

import java.io.InputStream;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HexagonGamePaneTest {
  private static final double HEIGHT_FACTOR = 1.60;
  private static final double WIDTH_FACTOR = 1.3;
  private Grid grid;
  ResourceBundle resources = ResourceBundle.getBundle("resources.data");
  InputStream data = Grid.class.getClassLoader()
          .getResourceAsStream("PredatorPrey.csv");
  private HexagonGamePane testPane;
  private int gridHeight =700;
  private int gridWidth = 700;

  @BeforeEach
  public void setup() {

    grid = new PredatorPreyGrid(data, "Finite", "Cardinal");
    testPane = new HexagonGamePane(grid, gridWidth, gridHeight);
  }


  @Test
  public void hexagonsMade() {
    assertTrue(testPane.getInitialArray() !=null);
    testPane.setUpPane(grid);
    assertTrue(testPane.getInitialArray()[3][2] instanceof Hexagon);

    assertEquals(testPane.cellWidth()/WIDTH_FACTOR, ((Hexagon) testPane.getInitialArray()[0][0]).getHexWidth());
    assertEquals(testPane.cellHeight()/HEIGHT_FACTOR, ((Hexagon) testPane.getInitialArray()[0][0]).getHexHeight());

  }

}