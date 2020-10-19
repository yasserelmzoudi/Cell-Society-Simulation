package view.GamePaneShapes;


import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.grid.Grid;

/**
 * Class that makes the rectangle game pane.
 */

public class RectangleGamePane extends GamePane {

  private Shape[][] allShapes;

  /**
   * Constructor for this class.
   *
   * @param grid   Triangle grid.
   * @param width  Width of grid.
   * @param height Height of grid.
   */
  public RectangleGamePane(Grid grid, int width, int height) {
    super(grid, width, height);
  }

  /**
   * Makes the array of rectangles.
   *
   * @param myGrid The given grid.
   */
  @Override
  public void makeArray(Grid myGrid) {
    allShapes = new Shape[myGrid.gridRows()][myGrid.gridColumns()];
    for (int r = 0; r < myGrid.gridRows(); r++) {
      for (int c = 0; c < myGrid.gridColumns(); c++) {
        Rectangle mynewPixel = new Rectangle(c * cellWidth(), r * cellHeight(), cellWidth(),
            cellHeight());
        allShapes[r][c] = mynewPixel;
      }
    }
  }

  @Override
  public Shape[][] getInitialArray() {
    return allShapes;
  }

}
