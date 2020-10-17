package view;


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



public abstract class GamePane extends Pane {
    private Grid myGrid;
    private int gridHeight;
    private int gridWidth;
    private TreeMap<String, String> gridCellTypesWithColor;
    private Shape[][] allShapes;

    public GamePane(Grid grid, int width, int height){
        gridHeight = height;
        gridWidth =width;
        myGrid = grid;
        makeArray(grid);
        allShapes = getInitialArray();
        gridCellTypesWithColor = new TreeMap<>();
        List<String> myTypes = grid.getAllTypes();
        for (int i =0; i< myTypes.size(); i++) {
          gridCellTypesWithColor.putIfAbsent(myTypes.get(i), myTypes.get(i));
        }
    }


    private boolean isImage(String stringToCheck) {
        return stringToCheck.contains(".png") || stringToCheck.contains(".jpg");
    }


    public void setUpPane(Grid grid) {
        //System.out.format("Cell Height: %d, Frame Height: %d, Number of Columns: %d \n", cellHeight, (int) framesize.getHeight(), gridRows);
        //System.out.format("Cell Width: %d, Frame Width: %d, Number of Rows: %d \n", cellWidth, (int) framesize.getWidth(), gridColumns);
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

    private String getColorId(String myType) {
        return gridCellTypesWithColor.get(myType);
    }

    public void setNewColor(String cellType, String newColor) {
        gridCellTypesWithColor.remove(cellType);
        gridCellTypesWithColor.putIfAbsent(cellType, newColor);
    }


    private void checkForRemoval(Shape newPixel) {
        if(this.getChildren().contains(newPixel)) {
            this.getChildren().remove(newPixel);
        }
    }


    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth (){
        return gridWidth;
    }



    public void setImage(Shape cell, String imageDirectory) {
        if(imageDirectory ==null || !isImage(imageDirectory)) return;
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

    public Grid getMyGrid() {
        return myGrid;
    }

    public double cellWidth() {
        double cellWidth =  (double) getGridWidth() / (double) getMyGrid().gridColumns();
        return cellWidth;
    }

    public double cellHeight() {
        double cellHeight = (double) getGridHeight() / (double) getMyGrid().gridRows();
        return cellHeight;
    }


    public abstract void makeArray(Grid grid);
    public abstract Shape[][] getInitialArray();

}
