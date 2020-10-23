package view.GamePaneShapes;

import javafx.scene.shape.Shape;
import model.grid.Grid;
import view.Shapes.Hexagon;

/**
 * Class that makes the hexangle game pane.
 */

public class HexagonGamePane extends GamePane {

  private static final double HEIGHT_FACTOR = 1.60;
  private static final double WIDTH_FACTOR = 1.3;
  private Shape[][] allShapes;

  /**
   * Constructor for this class.
   *
   * @param grid   Hexagon grid.
   * @param width  Width of grid.
   * @param height Height of grid.
   */
  public HexagonGamePane(Grid grid, int width, int height) {
    super(grid, width, height);
  }

  /**
   * Makes the array of hexagons.
   *
   * @param myGrid The given grid.
   */
  @Override
  public void makeArray(Grid myGrid) {
    allShapes = new Hexagon[myGrid.gridRows()][myGrid.gridColumns()];
    for (int r = 0; r < myGrid.gridRows(); r++) {
      for (int c = 0; c < myGrid.gridColumns(); c++) {
        Hexagon mynewPixel = new Hexagon(r, c, polyWidth(), polyHeight());
        allShapes[r][c] = mynewPixel;
      }
    }
  }

  @Override
  public Shape[][] getInitialArray() {
    return allShapes;
  }

  private double polyHeight() {
    return cellHeight() / HEIGHT_FACTOR;
  }

  private double polyWidth() {
    return cellWidth() / WIDTH_FACTOR;
  }

}
