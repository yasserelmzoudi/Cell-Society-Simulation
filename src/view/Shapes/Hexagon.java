package view.Shapes;

import javafx.scene.shape.Polygon;
import model.grid.Grid;

/**
 * Class that makes the hexagon shape.
 */
public class Hexagon extends Polygon {

  private static final double HALF_UNIT = 0.5;
  private static final double UNIT = 1;
  private int myRow;
  private int myColumn;
  private Double[] pointArray;
  private double myHexWidth;
  private double myHexHeight;


  /**
   * Constructor for this class.
   *
   * @param row Row of hexagon.
   * @param column Column of hexagon.
   * @param hexWidth Width of hexagon.
   * @param hexLength Height of hexagon.
   */
  public Hexagon(int row, int column, double hexWidth, double hexLength) {
    myRow = row;
    myColumn = column;
    myHexWidth = hexWidth;
    myHexHeight = hexLength;
    makeInitialShape(myRow, myColumn);
  }

  /**
   * Makes the hexagon shape.
   *
   * @param row Row of hexagon.
   * @param col Column of hexagon.

   */
  private void makeInitialShape(double row, double col) {
    if (isOdd(row)) {
      col += HALF_UNIT;
      row += row / 2;
    } else if (isNotFirstRow(row) && isEven(row)) {
      row += row / 2;
    }
    row += HALF_UNIT;
    pointArray = new Double[]{
            col * myHexWidth, row * myHexHeight,
            (col + HALF_UNIT) * myHexWidth, (row - HALF_UNIT) * myHexHeight,
            (col + UNIT) * myHexWidth, row * myHexHeight,
            (col + UNIT) * myHexWidth, (row + UNIT) * myHexHeight,
            (col + HALF_UNIT) * myHexWidth, (row + HALF_UNIT + UNIT) * myHexHeight,
            col * myHexWidth, (row + UNIT) * myHexHeight
    };
    this.getPoints().addAll(pointArray);
  }

  private boolean isEven(double coordinate) {
    return coordinate % 2 == 0;
  }

  private boolean isOdd(double coordinate) {
    return coordinate % 2 == 1;
  }

  private boolean isNotFirstRow(double coordinate) {
    return coordinate != 0;
  }


  public double getHexWidth() {
    return myHexWidth;
  }

  public double getHexHeight() {
    return myHexHeight;
  }
}
