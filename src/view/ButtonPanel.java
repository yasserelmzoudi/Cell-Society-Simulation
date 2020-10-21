package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.exceptions.UnableToSaveFileException;
import model.grid.Grid;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import model.grid.GridCSVWriter;
import model.grid.SimulationSettingsReader;
import view.GamePaneShapes.GamePane;

public class ButtonPanel extends GridPane{
    private static final String DEFAULT_STYLE_FOLDER ="/" + "StyleResources/";
    public static final String DATA_STYLESHEET = "DataStyles.css";
    public static final String DATA_STYLESHEET_PATH = DEFAULT_STYLE_FOLDER+DATA_STYLESHEET;
    private static final String EXCEPTION_RESOURCE = "resources.exceptionMessages";
    private static final ResourceBundle OBJECT_ID_BUNDLE = ResourceBundle.getBundle("StyleResources.ObjectID");
    private List<String> USER_DATA_NEEDED = Arrays.asList("Author", "Title", "Description");
    private ResourceBundle titlesBundle;
    private ResourceBundle errorMessageSource;

    private boolean simShouldResume = true;
    private boolean wantNewFile = false;
    private Grid myGameGrid;
    private GamePane myGamePane;
    private TextField authorField;
    private TextField titleField;
    private TextArea descriptionField;
    private boolean unitStep;

    private SimulationSettingsReader previousSettings;

    /**
     * Constructor of Class
     * Sets up the button panel for the user options
     * Includes a pause,resume, next,load simulation, save simulation, and quit button
     */

    public ButtonPanel(GamePane myPane, Grid grid, ResourceBundle titlesBundle, SimulationSettingsReader previousSettings) {
        unitStep = false;
        myGamePane = myPane;
        myGameGrid = grid;
        this.titlesBundle = titlesBundle;
        setUpButtons();
        addButtonsToGui();
        errorMessageSource = ResourceBundle.getBundle(EXCEPTION_RESOURCE);
        this.previousSettings = previousSettings;

    }

    private void addButtonsToGui() {
        for(int i =0 ; i < setUpButtons().size(); i++) {
            Button eachbutton = setUpButtons().get(i);
            this.setColumnIndex(eachbutton, i);
            this.getChildren().add(eachbutton);
        }
    }


    private List<Button> setUpButtons() {

        Button resumeButton = new Button(titlesBundle.getString("ResumeButton"));
        setButtonID(resumeButton, "ResumeButton");
        Button loadButton = new Button(titlesBundle.getString("LoadButton"));
        setButtonID(loadButton, "LoadButton");
        Button quitButton = new Button(titlesBundle.getString("QuitButton"));
        setButtonID(quitButton, "QuitButton");
        Button nextButton = new Button(titlesBundle.getString("NextButton"));
        setButtonID(nextButton, "NextButton");
        Button pauseButton = new Button(titlesBundle.getString("PauseButton"));
        setButtonID(pauseButton, "PauseButton");
        Button saveButton = new Button (titlesBundle.getString("SaveButton"));
        setButtonID(saveButton, "SaveButton");
        //pauseButton.getStyleClass().add("pauseButton");


        quitButton.setOnAction(e-> {
            System.exit(0);
        });

        resumeButton.setOnAction(e-> {
            simShouldResume = true;
            resetGUI(myGameGrid);
        });
        pauseButton.setOnAction(e->{
            simShouldResume =false;
        });

        loadButton.setOnAction(e->{
            simShouldResume =false;
            wantNewFile = true;
        });

        nextButton.setOnAction(e->{
            simShouldResume =false;
            unitStep = true;
        });

        saveButton.setOnAction(e->{
                    simShouldResume =false;
                    infoForSaving();

                }
        );

        List<Button> allButtons = Arrays.asList(pauseButton, resumeButton, nextButton, loadButton,saveButton, quitButton);
        return  allButtons;
    }


    private void infoForSaving() {
        VBox userData = new VBox();

        userData.getChildren().add(makeAuthorField(titlesBundle.getString(USER_DATA_NEEDED.get(0))+":"));
        userData.getChildren().add(makeTitleField(titlesBundle.getString(USER_DATA_NEEDED.get(1))+":"));
        userData.getChildren().add(makeDescriptionField(titlesBundle.getString(USER_DATA_NEEDED.get(2))+":"));

        Button okButton = new Button(titlesBundle.getString("OkButtonText"));
        okButton.setId(OBJECT_ID_BUNDLE.getString("OkButton"));
        HBox column = new HBox();
        column.getChildren().add(okButton);
        column.setAlignment(Pos.CENTER);
        Stage newWindow = new Stage();
        newWindow.setTitle(titlesBundle.getString("SAVE_FILE"));

        okButton.setOnAction(e->{
                simShouldResume =false;
                newWindow.close();
                writeToFile();
                simShouldResume = true;

            });
        userData.getChildren().add(column);
        userData.setId(OBJECT_ID_BUNDLE.getString("DataPanel"));
        Scene userInput  = new Scene(userData);
        userInput.getStylesheets().add(getClass().getResource(DATA_STYLESHEET_PATH).toExternalForm());
        newWindow.setScene(userInput);
        newWindow.show();

    }

    private HBox makeAuthorField(String labelString) {
        HBox createdBox = boxWithLabel(labelString);
        authorField = new TextField();
        createdBox.getChildren().add(authorField);
        return createdBox;
    }
    private HBox makeTitleField(String labelString) {
        HBox createdBox = boxWithLabel(labelString);
        titleField = new TextField();
        createdBox.getChildren().add(titleField);
        return createdBox;
    }

    private HBox makeDescriptionField(String labelString) {
        HBox createdBox = boxWithLabel(labelString);
        descriptionField = new TextArea();
        createdBox.getChildren().add(descriptionField);
        return createdBox;
    }

    private HBox boxWithLabel(String labelString) {
        HBox column = new HBox();
        Label label = new Label(labelString);
        column.getChildren().add(label);
        return column;
    }


    private void writeToFile() throws UnableToSaveFileException {
        String myAuthor = authorField.getText();
        String myTitle = titleField.getText();
        String myDescription = descriptionField.getText();

        try {
            GridCSVWriter csvFile = new GridCSVWriter(myGameGrid, myTitle, myAuthor, myDescription, previousSettings);
            csvFile.saveFile();
        } catch (UnableToSaveFileException e) {
            new ErrorPanel();
        }
    }

    /**
     * boolean that states whether the simulation should continue
     * Dependent on if a button was pressed
     */
    public boolean shouldcontinue(){
        return simShouldResume;
    }

    /**
     * resets the Gui so that buttons can be pressed multiple times after a new simulation is loaded
     */

    public boolean wantNewFile(){
        return wantNewFile;
    }


    public void resetGUI(Grid newGrid) { //change into interface so easier to create JWindow and GUI at same time
        simShouldResume =true;
        wantNewFile = false;
        myGameGrid = newGrid;
        resetUnit(newGrid);
    }

    public void resetUnit(Grid newGrid){
        myGameGrid = newGrid;
        unitStep = false;
    }


    private void setButtonID(Button button, String objectIDName) {
        button.setId(OBJECT_ID_BUNDLE.getString(objectIDName));

    }

    public boolean doUnitStep() {
        return unitStep;
    }

}
