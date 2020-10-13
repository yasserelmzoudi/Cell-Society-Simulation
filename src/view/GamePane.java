package view;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.grid.Grid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class GamePane extends GridPane {
    private Grid myGrid;
    private int gridHeight;
    private int gridWidth;
    private TreeMap<String, String> gridCellTypesWithColor;

    //TODO make grid give differnt types of cells in a list

    public GamePane(Grid grid, int width, int height){

        gridHeight = height;
        gridWidth =width;
        myGrid = grid;
        //this.setId("gamePanel");

        gridCellTypesWithColor = new TreeMap<>();
        List<String> myTypes = grid.getAllTypes();

        for (int i =0; i< myTypes.size(); i++) {
          gridCellTypesWithColor.putIfAbsent(myTypes.get(i), myTypes.get(i));
          System.out.println(myTypes.get(i));
        }

    }


    public void setUpPane(Grid grid) {

        //System.out.format("Cell Height: %d, Frame Height: %d, Number of Columns: %d \n", cellHeight, (int) framesize.getHeight(), gridRows);
        //System.out.format("Cell Width: %d, Frame Width: %d, Number of Rows: %d \n", cellWidth, (int) framesize.getWidth(), gridColumns);
        myGrid = grid;
        for (int r = 0; r < myGrid.gridRows(); r++) {
            for (int c = 0; c < myGrid.gridColumns(); c++) {
                Rectangle myPixel = getNodeFromGridPane(r,c);

                String state = getColorId(myGrid.getCell(r, c).getState().toString());//myGrid.getCell(r, c).getState().toString().toLowerCase();
                if(myPixel==null) {
                    myPixel = new Rectangle(myGrid.cellWidth(gridWidth) , myGrid.cellHeight(gridHeight));
                }
                else{
                    this.getChildren().remove(myPixel);
                }
                if(state.equals("Images/Shark.png") || state.equals("Images/Fish.png")) {
                    setImageIcon(myPixel, state);

                }
                else myPixel.setId(state);
                this.setRowIndex(myPixel, r);
                this.setColumnIndex(myPixel, c);
                this.getChildren().add(myPixel);
            }
        }
    }

    private String getColorId(String myType) {
        return gridCellTypesWithColor.get(myType);
    }

    public void setNewColor(String cellType, String newColor) {
        gridCellTypesWithColor.remove(cellType);
        gridCellTypesWithColor.put(cellType, newColor);
    }


    private Rectangle getNodeFromGridPane(int row, int col) {
        for (Node node : this.getChildren()) {
            if (node instanceof Rectangle && GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (Rectangle) node;
            }
        }
        return null;
    }


    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth (){
        return gridWidth;
    }

    public void setImageIcon(Rectangle cell, String imageDirectory) {
        File file = new File(imageDirectory);
        System.out.println(imageDirectory);
        try {
            BufferedImage temp = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(temp, null);
            cell.setFill(new ImagePattern(image));
        } catch (IOException e) {
            cell.setFill(Color.RED);
        }
    }

}
