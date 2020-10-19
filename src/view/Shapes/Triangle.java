package view.Shapes;

import javafx.scene.shape.Polygon;

/**
 * Class that makes the triangle shape. Uses the coordinate system.
 *
 */
public class Triangle extends Polygon {

    private static final double TWO = 2.0;
  private double myRow;
  private double myColumn;
  private Double[] pointArray;
  private double myWidth;
  private double myHeight;

  /**
   * Constructor for this class.
   *
   * @param row Row of shape.
   * @param column Column of shape.
   * @param width Width of shape.
   * @param length Height of shape.
   */
  public Triangle(int row, int column, double width, double length) {
    myRow = row;
    myColumn = column;
    myWidth = width;
    myHeight = length;
    makeInitialShape();

  }

  /**
   * Makes the shape based on whether the row and column is even or odd.
   */
  private void makeInitialShape() {

    if (isOdd(myRow) && isOdd(myColumn)) {
      uprightOddRow();
    } else if (isEven(myRow) && isEven(myColumn)) {
      uprightEvenRow();
    } else if (isEven(myRow) && isOdd(myColumn)) {
      invertedEvenRow();
    } else if (isOdd(myRow) && isEven(myColumn)) {
      invertedOddRow();
    }
    this.getPoints().addAll(pointArray);
  }

  /**
   * Checks if number is even.
   *
   * @param coordinate Given number.
   * @return True if number is even.
   */
  private boolean isEven(double coordinate) {
    return ((coordinate % 2) == 0);
  }


  /**
   * Checks if number is odd.
   *
   * @param coordinate Given number.
   * @return True if number is odd.
   */
  private boolean isOdd(double coordinate) {
    return ((coordinate % 2) != 0);
  }

  /**
   * Coordinates for upright triangle on even row.
   */
  private void uprightEvenRow() {
    myColumn += 1;
    pointArray = new Double[]{
        centerX(), bottomY(),
        centerX() + halfWidth(), topY(),
        centerX() - halfWidth(), topY()
    };
  }

  /**
   * Coordinates for inverted triangle on even row.
   */
  private void invertedEvenRow() {
    myColumn += 1;
    pointArray = new Double[]{
        centerX() - halfWidth(), bottomY(),
        centerX() + halfWidth(), bottomY(),
        centerX(), topY()
    };
  }

  /**
   * Coordinates for inverted triangle on odd row.
   */
  private void invertedOddRow() {
    myColumn += 1;
    pointArray = new Double[]{
        centerX(), topY(),
        centerX() + halfWidth(), bottomY(),
        centerX() - halfWidth(), bottomY()
    };
  }

  /**
   * Coordinates for upright triangle on odd row.
   */
  private void uprightOddRow() {
    myColumn += 1;
    pointArray = new Double[]{
        centerX(), bottomY(),
        centerX() - halfWidth(), topY(),
        centerX() + halfWidth(), topY()
    };
  }

  private double centerX() {
    return (myColumn) * myWidth / TWO;
  }

  private double halfWidth() {
    return myWidth / TWO;
  }

  private double bottomY() {
    return myRow * myHeight;
  }

  private double topY() {
    return (myRow + 1) * myHeight;
  }


}