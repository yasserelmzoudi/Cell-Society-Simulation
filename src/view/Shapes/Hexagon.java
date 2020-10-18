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
    makeInitialShape(myRow, myColumn, hexWidth, hexLength);
  }

    /**
     * Makes the hexagon shape.
     *
     * @param row Row of hexagon.
     * @param col Column of hexagon.
     * @param hexWidth Width of hexagon.
     * @param hexLength Height of hexagon.
     */
  public void makeInitialShape(double row, double col, double hexWidth, double hexLength) {
    if (isOdd(row)) {
      col += HALF_UNIT;
      row += row / 2;
    } else if (isNotFirstRow(row) && isEven(row)) {
      row += row / 2;
    }
    row += HALF_UNIT;
    pointArray = new Double[]{
        col * hexWidth, row * hexLength,
        (col + HALF_UNIT) * hexWidth, (row - HALF_UNIT) * hexLength,
        (col + UNIT) * hexWidth, row * hexLength,
        (col + UNIT) * hexWidth, (row + UNIT) * hexLength,
        (col + HALF_UNIT) * hexWidth, (row + HALF_UNIT + UNIT) * hexLength,
        col * hexWidth, (row + UNIT) * hexLength
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

}
