package view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Cell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import model.cell.CellType;
import model.grid.Grid;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ScreenVisuals extends BorderPane {
    private static final String RESOURCES = "resources/";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
    private ResourceBundle objectIdBundle = ResourceBundle.getBundle("resources.ObjectID");
    private ResourceBundle titleresource = ResourceBundle.getBundle("resources.title");

    private static final int MIN_SLIDER_SPEED =0;
    private static final int MAX_SLIDER_SPEED =8;
    private static final int GRID_PADDING_LR =100;
    private static final int GRID_PADDING_TB =200;

    private int visualWidth;
    private int visualHeight;
    private String gameDisplayID;
    private Grid myGrid;
    private GamePane myGamePane;
    private StartSimulation currentSimulation;
    private UserOptions myButtonDisplay;
    private HBox mySliderDisplay;
    private String gameTitle;


    public ScreenVisuals(StartSimulation thisSimulation, Grid grid, int width, int height, String title) {
        myGrid = grid;
        visualWidth = width;
        visualHeight = height;
        gameTitle =title;
        currentSimulation = thisSimulation;
        setupUserInterface();
        addGridEvent();
        //TODO load simulation text to display from resources
    }

    private void setupUserInterface() {
        myGamePane = new GamePane(myGrid, visualWidth-GRID_PADDING_LR, visualHeight- GRID_PADDING_TB);
        myGamePane.setId(objectIdBundle.getString("GameDisplay"));
        myGamePane.setUpPane(myGrid);
        this.setBottom(makeBottomPanel());
        this.setTop(makeTitleDisplay());
        this.setCenter(myGamePane);
    }

    public UserOptions getMyButtonDisplay() {
        return myButtonDisplay;
    }
    public GamePane getMyGamePane() {
        return myGamePane;
    }

    private Node makeBottomPanel() {
        BorderPane optionDisplay = new BorderPane();
        optionDisplay.setId(objectIdBundle.getString("BottomPanel")); //TODO change to get text from resource
        myButtonDisplay = new UserOptions(myGamePane, myGrid);
        myButtonDisplay.setId(objectIdBundle.getString("ButtonPanel"));
        HBox cellChanger = new HBox();
        List<String> myCells = Arrays.asList("cell 1: ","cell 2: ","cell 3: " ); //TODO: get cell names based on configuration file
        addDropDowns(myCells, cellChanger);
        cellChanger.setId("tilePane");
        optionDisplay.setBottom(cellChanger);
        optionDisplay.setCenter(myButtonDisplay);
        optionDisplay.setTop(makeSlider());
        return optionDisplay;
    }

    private void addDropDowns(List<String> cellNames, HBox cellChanger) {
        for (String cell: cellNames) {
            HBox column = new HBox();
            column.getChildren().add(new Label(cell));
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
        ComboBox eachcell = new ComboBox(); //TODO: get all options from somewhere else
        eachcell.getItems().addAll(
                "Pink",
                "Blue",
                "Cat Image",
                "Dog Image",
                "Shark Image"
        );
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
        mySliderDisplay.setId("sliderBox");
        return mySliderDisplay;
    }

    private void addGridEvent() {
        myGamePane.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("clicked");
                    Node source = (Node)event.getSource() ;
                    Integer colIndex = GridPane.getColumnIndex(source);
                    Integer rowIndex = GridPane.getRowIndex(source);
                    changeCellStatus(colIndex.intValue(), rowIndex.intValue());

                }
            });

        });
    }


    public void changeCellStatus(int rowIndex, int colIndex) {
        System.out.println(rowIndex + " "+ colIndex);
        myGrid.getCell(rowIndex, colIndex).setCellType(CellType.DEAD);
        myGamePane.setUpPane(myGrid);

    }



    //TODO add a color selector that displays the different cells available based on the id and that allows user to choose colors
    // TODO for each cell type , make sure to use these colors when saving the files, maybe have a way to override
    //TODO or write to css file so that you won't have to change the layout of game panel


}
