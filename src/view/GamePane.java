package view;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.TriangleMesh;
import model.grid.Grid;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;



public class GamePane extends Pane {
    private Grid myGrid;
    private int gridHeight;
    private int gridWidth;
    private TreeMap<String, String> gridCellTypesWithColor;

    public GamePane(Grid grid, int width, int height){

        gridHeight = height;
        gridWidth =width;
        myGrid = grid;
        this.setId("GameGrid");
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
                //Hexagon myPixel = getNodeFromGridPane(r,c);

                String state = getColorId(myGrid.getCell(r, c).getState().toString());
                //System.out.println(state);

                Hexagon myPixel = new Hexagon(r, c, determinePolyLength());
                    //myPixel = new Polygon(r, c, r+myGrid.cellWidth(gridWidth),c +myGrid.cellHeight(gridHeight));
                setImage(myPixel, state);
                myPixel.setId(state);
                this.getChildren().add(myPixel);
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


    private Hexagon getNodeFromGridPane(int row, int col) {
        for (Node node : this.getChildren()) {
            if (node instanceof Hexagon ) {
                Hexagon currentHexagon = (Hexagon) node;
                if(currentHexagon.rowIndex() == row || currentHexagon.columIndex() == col)
                    return currentHexagon;
            }
        }
        return null;
    }

    private double determinePolyLength() {
        double maxWidth = gridWidth / (myGrid.gridColumns()/2);
        double maxHeight = gridHeight / myGrid.gridRows();

        return Math.min(maxHeight, maxWidth)/1.5;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth (){
        return gridWidth;
    }

    public void setImage(Hexagon cell, String imageDirectory) {
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



}
