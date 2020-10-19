package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.cell.CellType;
import model.exceptions.InvalidSimulationTypeException;
import model.grid.Grid;
import view.GamePaneShapes.GamePane;

import java.util.*;

public class ScreenVisuals extends BorderPane {
    private static final String DEFAULT_STYLE_FOLDER ="/" + "StyleResources/";
    public static final String INIT_DIALOG_STYLESHEET = "InitialDialogs.css";
    public static final String INIT_DIALOG_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER + INIT_DIALOG_STYLESHEET;
    private static final ResourceBundle OBJECT_ID_BUNDLE = ResourceBundle.getBundle("StyleResources.ObjectID");
    private static final ResourceBundle INITIAL_OPTIONS = ResourceBundle.getBundle("StyleResources.InitialOptions");
    private static final ResourceBundle STYLE_OPTIONS = ResourceBundle.getBundle("StyleResources.ColorStyles");

//TODO change main directory for loading file to fit with Yasser's properties file directory
// TODO for each cell type , make sure to use these colors when saving the files, maybe have a way to override

    private static final int MIN_SLIDER_SPEED = 0;
    private static final int MAX_SLIDER_SPEED = 8;
    private static final int GRID_PADDING_TB = 220;
    private static final int GRID_PADDING_LR = 30;

    private int visualWidth;
    private int visualHeight;
    private Grid myGrid;
    private GamePane myGamePane;
    private String myShapeType;
    private StartSimulation currentSimulation;
    private ButtonPanel myButtonDisplay;
    private HBox mySliderDisplay;
    private String gameTitle;
    private List<ComboBox> cellChange = new ArrayList<>();
    private List<String> cellTypes = new ArrayList<>();
    private Class<?> gamePaneType;
    private static final String EXCEPTION_RESOURCE = "resources.exceptionMessages";
    private ResourceBundle errorMessageSource = ResourceBundle.getBundle(EXCEPTION_RESOURCE);
    private ResourceBundle titlesBundle = ResourceBundle.getBundle("LanguageResources.english");
    private String myStyle;
    private String myStyleEnglish;
    private String myLang;
    Map<String, ComboBox> myLangOption;
    Map<String, ComboBox> myOtherOptions;
    private String chosenStylePath;


    public ScreenVisuals(StartSimulation thisSimulation, Grid grid, int width, int height, String title) {
        myGrid = grid;
        visualWidth = width;
        visualHeight = height;
        gameTitle = title;
        currentSimulation = thisSimulation;
        initialSetUp();
    }

    public void setupUserInterface() {
        int gridHeight = Math.max(0, visualHeight - GRID_PADDING_TB);
        int gridWidth = Math.max(0, visualWidth - GRID_PADDING_LR);
        try {
            String myEnglishShapeType = getEnglishTranslation(myShapeType);
            gamePaneType = Class.forName("view.GamePaneShapes." + myEnglishShapeType + "GamePane");
            Object gridShapeInstance = gamePaneType.getDeclaredConstructor(
                    new Class[]{Grid.class, int.class, int.class}).newInstance(myGrid, gridWidth, gridHeight);
            myGamePane = (GamePane) gridShapeInstance;
        } catch (Exception e) {
            throw new InvalidSimulationTypeException(errorMessageSource.getString("InvalidSimulation"));
        }
        //myGamePane = new TriangleGamePane(myGrid, gridWidth, gridHeight);
        myGamePane.setId(OBJECT_ID_BUNDLE.getString("GameDisplay"));
        myGamePane.setUpPane(myGrid);
        this.setBottom(makeBottomPanel());
        this.setTop(makeTitleDisplay());
        HBox myGameBox = new HBox(myGamePane);
        myGameBox.setOnMouseClicked(e -> changeCellStatus(e.getX() - myGameBox.getBoundsInLocal().getMinX(), e.getY() - myGameBox.getBoundsInLocal().getMinY()));
        myGameBox.setId("gameDisplayBox");
        this.setCenter(myGameBox);
        addGridEvent();
        System.out.println(getChosenStylePath());
        currentSimulation.setUpScene(getChosenStylePath());
    }

    private Node makeBottomPanel() {
        BorderPane optionDisplay = new BorderPane();
        optionDisplay.setId(OBJECT_ID_BUNDLE.getString("BottomPanel"));
        myButtonDisplay = new ButtonPanel(myGamePane, myGrid, titlesBundle);
        myButtonDisplay.setId(OBJECT_ID_BUNDLE.getString("ButtonPanel"));
        Pane cellChanger = new HBox();
        cellTypes =myGrid.getAllTypes() ;
        addCellDropDowns(cellNamesWithSpace(myGrid.getAllTypes()), cellChanger);
        cellChanger.setId(OBJECT_ID_BUNDLE.getString("HBox"));
        optionDisplay.setBottom(cellChanger);
        optionDisplay.setCenter(myButtonDisplay);
        optionDisplay.setTop(makeSlider());
        return optionDisplay;
    }

    private List<String> cellNamesWithSpace(List<String> oldTypeList) {
        List<String> oldTypes = oldTypeList;
        List<String> newTypeList = new ArrayList<>();
        for(String cellType : oldTypes) {
            System.out.println(cellType);
            String newCellType = cellType.replaceAll("_", " ");
            System.out.println(newCellType);
            newTypeList.add(newCellType);
        }
        return newTypeList;
    }


    private void addCellDropDowns(List<String> cellNames, Pane cellChanger) {
        for (String cell : cellNames) {
            ComboBox newCombo = addToLocation(cell, Arrays.asList(titlesBundle.getString("CellChanges").split(",")), cellChanger);
            cellChange.add(newCombo);
        }
    }

    private ComboBox makeDropDownOptions(List<String> dropDownOptions) {
        ComboBox eachcell = new ComboBox();
        eachcell.getItems().addAll(dropDownOptions);
        return eachcell;
    }

    private Node makeTitleDisplay() {
        HBox titleDisplay = new HBox(); //Name top Vbox center slider bottom
        titleDisplay.setAlignment(Pos.CENTER);
        Text simulationTitleText = new Text(gameTitle);
        titleDisplay.getChildren().add(simulationTitleText);
        return titleDisplay;
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
        slider.setId(OBJECT_ID_BUNDLE.getString("Slider"));
        mySliderDisplay.getChildren().add(slider);
        mySliderDisplay.setId(OBJECT_ID_BUNDLE.getString("SliderBox"));
        return mySliderDisplay;
    }

    private void addGridEvent() {
        myGamePane.setOnMouseClicked(e -> changeCellStatus((int) e.getSceneX(), (int) e.getSceneY()));
    }

    private String getEnglishTranslation(String itemToSearchFor) {
        for(String key: titlesBundle.keySet()) {
            if(titlesBundle.getString(key).equals(itemToSearchFor)) {
                return key;
            }
        }
        return "invalid shape";
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
                    myGrid.getCell(i, j).setCellType(newCellType);
                    myGamePane.setUpPane(myGrid);
                }
            }
        }
    }

    private boolean checkWithinX(Shape myShape, double x) {
        return x < myShape.getBoundsInLocal().getMaxX() && x > myShape.getBoundsInLocal().getMinX();
    }

    private boolean checkWithinY(Shape myShape, double y) {
        return y < myShape.getBoundsInLocal().getMaxY() && y > myShape.getBoundsInLocal().getMinY();
    }

    public void checkUserOptionsChosen() {
        if (!cellChange.isEmpty()) {
            for (int i = 0; i < cellTypes.size(); i++) {
                Object checkObject = cellChange.get(i).getSelectionModel().getSelectedItem();
                if (checkObject != null) {
                    String newCellAppearance = cellChange.get(i).getSelectionModel().getSelectedItem().toString();
                    myGamePane.setNewColor(cellTypes.get(i), OBJECT_ID_BUNDLE.getString(newCellAppearance));
                    myGamePane.setUpPane(myGrid);
                }
            }
        }
    }


    private boolean allOptionsChosen(Map<String, ComboBox> myOptionMap) {
        String optionChosen = "";
        if (myOptionMap == null || myOptionMap.isEmpty()) return false;
        for (String setUpOption : myOptionMap.keySet()) {
            Object checkObject = myOptionMap.get(setUpOption).getSelectionModel().getSelectedItem();
            if (checkObject == null) return false;
            else {  //can you assign to a certain variable here using reflection? ex myShape = optionChosed
                optionChosen = myOptionMap.get(setUpOption).getSelectionModel().getSelectedItem().toString();
                if (optionChosen == null || optionChosen.isEmpty()) return false;
            }
        }
        return true;
    }

    private void setUpLanguage() {
        myLang = myLangOption.get("Language").getSelectionModel().getSelectedItem().toString();
        titlesBundle = ResourceBundle.getBundle("languageresources." + myLang.toLowerCase());
        askForOthers();
    }

    private void askForOthers() {
        myOtherOptions= new HashMap<>();
        Pane newPane = new VBox();
        List<String> initialOptionLabels = new ArrayList<>();
        initialOptionLabels = Arrays.asList(titlesBundle.getString("ShapeTranslation"), titlesBundle.getString("StyleTranslation"));

        addDifferentDropDowns(initialOptionLabels, newPane, myOtherOptions, titlesBundle);
        Stage otherOptionStage = new Stage();
        EventHandler newHandler = e -> {
            if (allOptionsChosen(myOtherOptions)) {
                otherOptionStage.close();
                setUpOtherOptions();
                setupUserInterface();
            }
            else System.out.println("Please choose a value for each");
        };
        makeOkButton(newPane, newHandler);
        newPane.setId("initialUserDialogs");
        Scene userInput = new Scene(newPane);
        assignStyleSheet(userInput, INIT_DIALOG_STYLESHEET_PATH);
        otherOptionStage.setScene(userInput);
        otherOptionStage.show();
    }

    private void setUpOtherOptions() {
        myShapeType = myOtherOptions.get(titlesBundle.getString("ShapeTranslation")).getSelectionModel().getSelectedItem().toString();
        myStyle = myOtherOptions.get(titlesBundle.getString("StyleTranslation")).getSelectionModel().getSelectedItem().toString();
        System.out.println(myStyle);
        myStyleEnglish = getEnglishTranslation(myStyle);
        System.out.println(myStyleEnglish);
    }

    private String getChosenStylePath() {
        chosenStylePath =  DEFAULT_STYLE_FOLDER + STYLE_OPTIONS.getString(myStyleEnglish);
        return chosenStylePath;
    }

    private void initialSetUp() {
        myLangOption = new HashMap<>();
        Pane initialUserOptions = new VBox();
        List<String> initialOptionLabels = new ArrayList<>();
        for (String x : INITIAL_OPTIONS.keySet())
            initialOptionLabels.add(x);
        addDifferentDropDowns(initialOptionLabels, initialUserOptions, myLangOption, INITIAL_OPTIONS);
        Stage newWindow = new Stage();
        EventHandler newHandler = e -> {
            if (allOptionsChosen(myLangOption)) {
                newWindow.close();
                setUpLanguage();
            }
            else System.out.println("Please choose a value for each");
        };
        makeOkButton(initialUserOptions, newHandler); //add event handler
        initialUserOptions.setId("initialUserDialogs");
        Scene userInput = new Scene(initialUserOptions);
        assignStyleSheet(userInput, INIT_DIALOG_STYLESHEET_PATH);
        newWindow.setScene(userInput);
        newWindow.show();
    }

    private void addDifferentDropDowns(List<String> allLabels, Pane comboBoxLocation, Map initialComboMap, ResourceBundle resourceFile) {
        List<String> dropDownItems;
        for (String label : allLabels) {
            dropDownItems = Arrays.asList(resourceFile.getString(label).split(","));
            initialComboMap.putIfAbsent(label, addToLocation(label, dropDownItems, comboBoxLocation));
        }
    }

    private ComboBox addToLocation(String label, List<String> dropDownItems, Pane comboBoxLocation) {
        HBox column = new HBox();
        Label cellTypeLabel = new Label(label.toLowerCase() + ": ");
        column.getChildren().add(cellTypeLabel);
        ComboBox eachCombo = makeDropDownOptions(dropDownItems);
        column.getChildren().add(eachCombo);
        column.setId("column");
        comboBoxLocation.getChildren().add(column);
        return eachCombo;
    }

    private void makeOkButton(Pane buttonLocation, EventHandler<ActionEvent> handler) {
        Button okButton = new Button(titlesBundle.getString("OkButtonText"));
        okButton.setId(OBJECT_ID_BUNDLE.getString("OkButton"));
        HBox column = new HBox();
        okButton.setOnAction(handler);
        column.getChildren().add(okButton);
        column.setAlignment(Pos.CENTER);
        column.setPadding(new Insets(7,6,7,6));
        buttonLocation.getChildren().add(column);
    }

    public boolean wantNewFile() {
        return myButtonDisplay != null && myButtonDisplay.wantNewFile();
    }

    public boolean shouldContinue() {
         return myButtonDisplay != null && myButtonDisplay.shouldcontinue();
    }

    public void resetGUI(Grid newGrid) {
        if(myButtonDisplay != null) {
            myButtonDisplay.resetGUI(newGrid);
        }
    }

    private void assignStyleSheet(Scene scene, String styleSheetPath) {
        scene.getStylesheets().add(getClass().getResource(styleSheetPath).toExternalForm());
    }

    public GamePane getMyGamePane() {
        return myGamePane;
    }

}



