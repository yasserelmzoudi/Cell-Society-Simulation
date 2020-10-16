package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import model.cell.CellType;
import model.grid.Grid;

import java.util.*;

public class ScreenVisuals extends BorderPane {
    private ResourceBundle objectIdBundle = ResourceBundle.getBundle("resources.ObjectID");
    private ResourceBundle titleresource = ResourceBundle.getBundle("resources.title");
    private static final List<String> userChangeOptions= Arrays.asList("Shark Image", "Pink Color" , "Blue Color", "Fish Image", "Burning Tree Image", "Tree Image", "Water Image", "Green Color", "Grass Image");
//TODO change userChangeOptions to be according to a grid, probably in a configuration file
//TODO change main directory for loading file to fit with Yasser's properties file directory
// TODO add a color selector that displays the different cells available based on the id and that allows user to choose colors
// TODO for each cell type , make sure to use these colors when saving the files, maybe have a way to override
//TODO For complete: before starting simulation, run a dialog that has the language, the style- dark, unc, etc. (this will load in a style sheet based onthe value in the combo box)

    //Pop up a dialog before that sheos
    private static final int MIN_SLIDER_SPEED =0;
    private static final int MAX_SLIDER_SPEED =8;
    private static final int GRID_PADDING_TB =200;
    private static final int GRID_PADDING_LR =10;

    private int visualWidth;
    private int visualHeight;
    private Grid myGrid;
    private GamePane myGamePane;
    private StartSimulation currentSimulation;
    private UserOptions myButtonDisplay;
    private HBox mySliderDisplay;
    private String gameTitle;
    private List<ComboBox> cellChange = new ArrayList<>();
    private List<String> cellTypes = new ArrayList<>();


    public ScreenVisuals(StartSimulation thisSimulation, Grid grid, int width, int height, String title) {
        myGrid = grid;
        visualWidth = width;
        visualHeight = height;
        gameTitle =title;
        currentSimulation = thisSimulation;
        setupUserInterface();
        addGridEvent();
    }

    private void setupUserInterface() {
        int gridHeight = Math.max(0, visualHeight-GRID_PADDING_TB);
        int gridWidth = Math.max(0, visualWidth - GRID_PADDING_LR);
        myGamePane = new HexagonGamePane(myGrid, gridWidth, gridHeight);
        myGamePane.setId(objectIdBundle.getString("GameDisplay"));
        myGamePane.setUpPane(myGrid);
        this.setBottom(makeBottomPanel());
        this.setTop(makeTitleDisplay());
        HBox myGameBox = new HBox(myGamePane);
        myGameBox.setOnMouseClicked(e -> changeCellStatus(e.getX() - myGameBox.getBoundsInLocal().getMinX(), e.getY()- myGameBox.getBoundsInLocal().getMinY()));
        myGameBox.setId("gameDisplayBox");
        this.setCenter(myGameBox);
    }

    public UserOptions getMyButtonDisplay() {
        return myButtonDisplay;
    }
    public GamePane getMyGamePane() {
        return myGamePane;
    }

    private Node makeBottomPanel() {
        BorderPane optionDisplay = new BorderPane();
        optionDisplay.setId(objectIdBundle.getString("BottomPanel"));
        myButtonDisplay = new UserOptions(myGamePane, myGrid);
        myButtonDisplay.setId(objectIdBundle.getString("ButtonPanel"));
        HBox cellChanger = new HBox();
        cellTypes = myGrid.getAllTypes();
        addDropDowns(cellTypes, cellChanger);
        cellChanger.setId(objectIdBundle.getString("HBox"));
        optionDisplay.setBottom(cellChanger);
        optionDisplay.setCenter(myButtonDisplay);
        optionDisplay.setTop(makeSlider());
        return optionDisplay;
    }



    private void addDropDowns(List<String> cellNames, HBox cellChanger) {
        for (String cell: cellNames) {
            HBox column = new HBox();
            Label cellTypeLabel = new Label(cell.toLowerCase()+": ");
            column.getChildren().add(cellTypeLabel);
            column.getChildren().add(makeDropDownOptions());
            cellChanger.getChildren().add(column);
        }
    }

    private Node makeTitleDisplay() {
        HBox titleDisplay = new HBox(); //Name top Vbox center slider bottom
        titleDisplay.setAlignment(Pos.CENTER);
        Text simulationTitleText = new Text(gameTitle);
        titleDisplay.getChildren().add(simulationTitleText);
        return titleDisplay;
    }

    private Node makeDropDownOptions() {
        ComboBox eachcell = new ComboBox();
        eachcell.getItems().addAll(userChangeOptions);
        cellChange.add(eachcell);
        return eachcell;
    }


    private Node makeSlider() {
        mySliderDisplay = new HBox();
        Slider slider = new Slider();

        slider.setMin(MIN_SLIDER_SPEED);
        slider.setMax(MAX_SLIDER_SPEED);
        slider.setValue(MIN_SLIDER_SPEED);
        slider.setMajorTickUnit(1);
        slider.setShowTickLabels(true);
        //slider.setShowTickMarks(true);
        slider.valueProperty().addListener(
                (ov, old_val, new_val) -> currentSimulation.setAnimationSpeed((Double) new_val));
        slider.setId(objectIdBundle.getString("Slider"));
        mySliderDisplay.getChildren().add(slider);
        mySliderDisplay.setId(objectIdBundle.getString("SliderBox"));
        return mySliderDisplay;
    }

    private void addGridEvent() {
    myGamePane.setOnMouseClicked(e -> changeCellStatus((int) e.getSceneX(), (int) e.getSceneY()));
    }


    public void changeCellStatus(double x, double y) {
        Shape[][] myShapeGrid = myGamePane.getInitialArray();
        for (int i = 0; i < myGrid.gridRows(); i++) {
            for (int j = 0; j < myGrid.gridColumns(); j++) {
                if (checkWithinX(myShapeGrid[i][j], x) && checkWithinY(myShapeGrid[i][j], y)) {
                    Random rand = new Random();
                    List<String> cellChoices = myGrid.getAllTypes();
                    String newChoice = cellChoices.get(rand.nextInt(cellChoices.size()));
                    CellType newCellType = CellType.valueOf(newChoice);
                    myGrid.getCell(i,j).setCellType(newCellType);
                    myGamePane.setUpPane(myGrid);
                }
            }
        }
    }

    private boolean checkWithinX(Shape myShape, double x) {
        return x< myShape.getBoundsInLocal().getMaxX() && x> myShape.getBoundsInLocal().getMinX();
    }

    private boolean checkWithinY(Shape myShape, double y) {
        return y< myShape.getBoundsInLocal().getMaxY() && y> myShape.getBoundsInLocal().getMinY();
    }

    public void checkUserChanges() {
        if(!cellChange.isEmpty()) {
            for(int i=0; i< cellTypes.size(); i++) {
                Object checkObject = cellChange.get(i).getSelectionModel().getSelectedItem();
                if(checkObject !=null ) {
                    String newCellAppearance = cellChange.get(i).getSelectionModel().getSelectedItem().toString();
                    myGamePane.setNewColor(cellTypes.get(i), objectIdBundle.getString(newCellAppearance));
                    myGamePane.setUpPane(myGrid);
                }
            }
        }
    }

}
