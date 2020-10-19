package view.GamePaneShapes;

import javafx.scene.shape.Shape;
import model.grid.Grid;
import view.Shapes.Triangle;

/**
 * Class that makes the triangle game pane.
 */
public class TriangleGamePane extends GamePane {

  private static final double BASE_WIDTH = 1.5;
  private Shape[][] allShapes;

  /**
   * Constructor for this class.
   *
   * @param grid   Triangle grid.
   * @param width  Width of grid.
   * @param height Height of grid.
   */
  public TriangleGamePane(Grid grid, int width, int height) {
    super(grid, width, height);
  }

  /**
   * Makes the array of triangles.
   *
   * @param myGrid The given grid.
   */
  @Override
  public void makeArray(Grid myGrid) {
    allShapes = new Triangle[myGrid.gridRows()][myGrid.gridColumns()];
    for (int r = 0; r < myGrid.gridRows(); r++) {
      for (int c = 0; c < myGrid.gridColumns(); c++) {
        Triangle mynewPixel = new Triangle(r, c, triangleBase(), triangleHeight());
        allShapes[r][c] = mynewPixel;
      }
    }
  }

  @Override
  public Shape[][] getInitialArray() {
    return allShapes;
  }

  private double triangleBase() {
    return cellWidth() / BASE_WIDTH;
  }

  private double triangleHeight() {
    return cellHeight();
  }

}
