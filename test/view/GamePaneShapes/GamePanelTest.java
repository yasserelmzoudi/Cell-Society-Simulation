package view.GamePaneShapes;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.cell.Cell;
import model.cell.GameOfLifeCell;
import model.grid.GameOfLifeGrid;
import model.grid.Grid;
import model.grid.SpreadingOfFireGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.GamePaneShapes.GamePane;
import view.GamePaneShapes.RectangleGamePane;
import view.Shapes.Hexagon;
import view.Shapes.Triangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class GamePanelTest {
  private Grid grid;
  ResourceBundle resources = ResourceBundle.getBundle("resources.data");
  InputStream data = Grid.class.getClassLoader()
          .getResourceAsStream(resources.getString("SpreadingOfFireGridTest"));
  private GamePane testPane;
  private int gridHeight = 600;
  private int gridWidth = 600;
  private Shape[][] initialArray;

  @BeforeEach
  public void setup() {

    grid = new SpreadingOfFireGrid(data, "Finite", "Cardinal");
    testPane = new RectangleGamePane(grid, gridWidth, gridHeight);
  }


  @Test
  public void arrayWasMade() {
    assertTrue(testPane.getInitialArray() !=null);
    assertEquals(testPane.getInitialArray()[0].length, grid.gridColumns());
    assertEquals(testPane.getInitialArray().length, grid.gridRows());

  }

  @Test
  public void containsCorrectShapes() {

    testPane = new RectangleGamePane(grid, gridWidth, gridHeight);
    assertTrue(testPane.getInitialArray()[0][0] instanceof Rectangle);

    testPane = new TriangleGamePane(grid, gridWidth, gridHeight );
    assertTrue(testPane.getInitialArray()[0][0] instanceof Triangle);

    testPane = new HexagonGamePane(grid, gridWidth, gridHeight );
    assertTrue(testPane.getInitialArray()[0][0] instanceof Hexagon);

  }

  @Test
  public void shapeIdUpdated() {



    Shape cellShape = testPane.getInitialArray()[0][0];
    Cell myCell = grid.getCell(0,0);

    String cellType = myCell.getState().toString();
    testPane.setUpPane(grid);
    assertTrue(cellType == cellShape.getId());

    testPane.setNewColor(cellType, "PINK");
    testPane.setUpPane(grid);
    assertTrue(cellShape.getId() == "PINK");


  }

  @Test
  public void testCorrectGrid() {
    assertEquals(grid , testPane.getMyGrid());

  }

  @Test
  public void correctCellWidth() {
    double myCalculatedWidth = (double) gridWidth / (double) 20;

    assertEquals(myCalculatedWidth, testPane.cellWidth());
  }

  @Test
  public void correctCellHeight() {
    double myCalculatedHeight = (double) gridHeight / (double) 20;
    assertEquals(myCalculatedHeight, testPane.cellHeight());

  }

  @Test
  public void testInvalidCellChange() {

    Shape cellShape = testPane.getInitialArray()[0][0];
    Cell myCell = grid.getCell(0,0);

    String cellType = myCell.getState().toString();
    testPane.setUpPane(grid);
    assertTrue(cellType == cellShape.getId());


    cellType = "DEAD";
    testPane.setNewColor(cellType, "PINK");
    testPane.setUpPane(grid);
    assertFalse(cellShape.getId() == "PINK");

    data = Grid.class.getClassLoader()
            .getResourceAsStream("GameOfLife_Blinker.csv");

    grid = new GameOfLifeGrid(data,"Finite", "Cardinal");
    testPane = new RectangleGamePane(grid, gridWidth, gridHeight);

    cellShape = testPane.getInitialArray()[0][0];
    myCell = grid.getCell(0,0);

    cellType = myCell.getState().toString();
    testPane.setUpPane(grid);
    assertTrue(cellType == cellShape.getId());


    cellType = "DEAD";
    testPane.setNewColor(cellType, "PINK");
    testPane.setUpPane(grid);
    assertTrue(cellShape.getId() == "PINK");



  }

  @Test
  public void consistentChildrenSize() {
    testPane.setUpPane(grid);
    assertEquals(grid.gridColumns() * grid.gridRows(),testPane.getChildren().size());

    grid.performNextStep();
    testPane.setUpPane(grid);
    assertTrue(testPane.getChildren().size() == grid.gridColumns() * grid.gridRows());

    grid.performNextStep();
    testPane.setUpPane(grid);
    assertTrue(testPane.getChildren().size() == grid.gridColumns() * grid.gridRows());

  }

}