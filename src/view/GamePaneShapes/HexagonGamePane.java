package view.GamePaneShapes;

import javafx.scene.shape.Shape;
import model.grid.Grid;
import view.Shapes.Hexagon;


public class HexagonGamePane extends GamePane {
    private Shape[][] allShapes;

    public HexagonGamePane(Grid grid, int width, int height){
        super(grid, width, height);
    }


    private double polyHeight() {
        return cellHeight()/1.60;
    }

    private double polyWidth() {
        return cellWidth() /1.3;
    }


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

}
