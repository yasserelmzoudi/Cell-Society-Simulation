package view.GamePaneShapes;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;

import javafx.scene.image.Image;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;
import javafx.scene.shape.TriangleMesh;
import model.grid.Grid;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Abstract class that makes the <code>GamePane</code> depending on the type of shape in the grid.
 */

public abstract class GamePane extends Pane {

  private Grid myGrid;
  private int gridHeight;
  private int gridWidth;
  private TreeMap<String, String> gridCellTypesWithColor;
  private Shape[][] allShapes;

  /**
   * Constructor for this class.
   *
   * @param grid   The grid.
   * @param width  Width of grid.
   * @param height Height of grid.
   */
  public GamePane(Grid grid, int width, int height) {
    gridHeight = height;
    gridWidth = width;
    myGrid = grid;

    makeArray(grid);

    allShapes = getInitialArray();
    gridCellTypesWithColor = new TreeMap<>();
    List<String> myTypes = grid.getAllTypes();

    for (int index = 0; index < myTypes.size(); index++) {
      gridCellTypesWithColor.putIfAbsent(myTypes.get(index), myTypes.get(index));
    }
  }

  /**
   * Abstract method that makes the array of shapes.
   *
   * @param grid The grid that is used to generate the array.
   */
  public abstract void makeArray(Grid grid);

  /**
   * Gets initial array.
   *
   * @return Array of shapes.
   */
  public abstract Shape[][] getInitialArray();

  /**
   * Sets up pane.
   *
   * @param grid The grid used to set up pane.
   */
  public void setUpPane(Grid grid) {
    myGrid = grid;
    for (int r = 0; r < myGrid.gridRows(); r++) {
      for (int c = 0; c < myGrid.gridColumns(); c++) {
        Shape mynewPixel = allShapes[r][c];
        checkForRemoval(mynewPixel);
        String state = getColorId(myGrid.getCell(r, c).getState().toString());
        setImage(mynewPixel, state);
        mynewPixel.setId(state);
        this.getChildren().add(mynewPixel);
      }
    }
  }

  /**
   * If an image needs to be set in the cell, the corresponding image is filled in the cell.
   *
   * @param cell           The cell in which to place the image.
   * @param imageDirectory Directory from which to pull the image.
   */
  public void setImage(Shape cell, String imageDirectory) {
    if (imageDirectory == null || !isImage(imageDirectory)) {
      return;
    }
    File file = new File(imageDirectory);
    try {
      BufferedImage temp = ImageIO.read(file);
      Image image = SwingFXUtils.toFXImage(temp, null);
      cell.setFill(new ImagePattern(image));
      return;
    } catch (IOException e) {
      cell.setFill(Color.RED);
    }
  }

  public int getGridHeight() {
    return gridHeight;
  }

  public int getGridWidth() {
    return gridWidth;
  }

  public Grid getMyGrid() {
    return myGrid;
  }

  public double cellWidth() {
    double cellWidth = (double) getGridWidth() / (double) getMyGrid().gridColumns();
    return cellWidth;
  }

  public double cellHeight() {
    double cellHeight = (double) getGridHeight() / (double) getMyGrid().gridRows();
    return cellHeight;
  }

  public void setNewColor(String cellType, String newColor) {
    gridCellTypesWithColor.remove(cellType);
    gridCellTypesWithColor.putIfAbsent(cellType, newColor);
  }

  private String getColorId(String myType) {
    return gridCellTypesWithColor.get(myType);
  }

  private boolean isImage(String stringToCheck) {
    return stringToCheck.contains(".png") || stringToCheck.contains(".jpg");
  }

  private void checkForRemoval(Shape newPixel) {
    if (this.getChildren().contains(newPixel)) {
      this.getChildren().remove(newPixel);
    }
  }


}
