package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import model.grid.Grid;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ScreenVisuals extends BorderPane {
    private static final String RESOURCES = "resources/";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
    private ResourceBundle bundle = ResourceBundle.getBundle("resources.ButtonText");
    private ResourceBundle titleresource = ResourceBundle.getBundle("resources.title");

    private static final int MIN_SLIDER_SPEED =0;
    private static final int MAX_SLIDER_SPEED =10;
    //private static final double DEFAULT_SLIDER_VALUE =1;
    private int visualWidth;
    private int visualHeight;
    private String gameDisplayID;
    private Grid myGrid;
    private GamePane myGamePane;
    private StartSimulation currentSimulation;
    private UserOptions myButtonDisplay;
    private HBox mySliderDisplay;
    private String gameTitle;


    public ScreenVisuals(StartSimulation thisSimulation, String name, Grid grid, int width, int height) {
        myGrid = grid;
        visualWidth = width;
        visualHeight = height;
        gameDisplayID = name;
        gameTitle =titleresource.getString(name);
        currentSimulation = thisSimulation; //prop not going to work
        this.setId(bundle.getString("GameDisplay"));
        setupUserInterface();
        //TODO load simulation text to display from resources
    }

    private void setupUserInterface() {
        myGamePane = new GamePane(myGrid, visualWidth-100, visualHeight- 200);
        myGamePane.setUpPane(myGrid);
        myGamePane.setId("gameDisplay");
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
        optionDisplay.setId("bottomPanel"); //TODO change to get text from resource
        myButtonDisplay = new UserOptions(myGamePane, myGrid);
        myButtonDisplay.setId("buttonPanel");
        TilePane allcells = new TilePane();
        List<String> myCells = Arrays.asList("cell 1: ","cell 1: ","cell 1: " );
        for (String cell: myCells) {
            HBox column = new HBox();
            column.getChildren().add(new Label(cell));
            column.getChildren().add(makeDropDownOptions());
            allcells.getChildren().add(column);
        }
        allcells.setId("tilePane");
        optionDisplay.setBottom(allcells);
        optionDisplay.setCenter(myButtonDisplay);
        optionDisplay.setTop(makeSlider());
        return optionDisplay;
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
        slider.setMax(8);
        slider.setValue(MIN_SLIDER_SPEED);
        slider.setMajorTickUnit(1);
        slider.setShowTickLabels(true);
        //slider.setShowTickMarks(true);
        slider.valueProperty().addListener(
                (ov, old_val, new_val) -> currentSimulation.setAnimationSpeed((Double) new_val));
        slider.setId(bundle.getString("Slider"));
        mySliderDisplay.getChildren().add(slider);
        mySliderDisplay.setId("sliderBox");
        return mySliderDisplay;
    }


    //TODO add a color selector that displays the different cells available based on the id and that allows user to choose colors
    // TODO for each cell type , make sure to use these colors when saving the files, maybe have a way to override
    //TODO or write to css file so that you won't have to change the layout of game panel


}
