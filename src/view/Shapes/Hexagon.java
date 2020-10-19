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
  private double hexWidth;
  private double hexHeight;


    /**
     * Constructor for this class.
     *
     * @param row Row of hexagon.
     * @param column Column of hexagon.
     * @param  Width of hexagon.
     * @param hexLength Height of hexagon.
     */
  public Hexagon(int row, int column, double hexWidth, double hexHeight) {
    myRow = row;
    myColumn = column;
    this.hexWidth = hexWidth;
    this.hexHeight = hexHeight;
    makeInitialShape();
  }

    /**
     * Makes the hexagon shape.
     *
     *
     */
  public void makeInitialShape() {
    if (isOdd(myRow)) {
      myColumn += HALF_UNIT;
      myRow += myRow / 2;
    } else if (isNotFirstRow(myRow) && isEven(myRow)) {
      myRow += myRow / 2;
    }
    myRow += HALF_UNIT;
    pointArray = new Double[]{
        myColumn * hexWidth, myRow * this.hexHeight,
        (myColumn + HALF_UNIT) * hexWidth, (myRow - HALF_UNIT) * this.hexHeight,
        (myColumn + UNIT) * hexWidth, myRow * this.hexHeight,
        (myColumn + UNIT) * hexWidth, (myRow + UNIT) * this.hexHeight,
        (myColumn + HALF_UNIT) * hexWidth, (myRow + HALF_UNIT + UNIT) * this.hexHeight,
        myColumn * hexWidth, (myRow + UNIT) * this.hexHeight
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
    return hexWidth;
  }

  public double getHexHeight() {
    return hexHeight;
  }
}
