package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.grid.Grid;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class UserOptions extends GridPane{
    private static final String RESOURCES = "resources/";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
    public static final String CONTROL_STYLESHEET = "Control_Styles.css";
    public static final String CONTROL_STYLESHEET_PATH = DEFAULT_RESOURCE_FOLDER + CONTROL_STYLESHEET;

    private boolean simShouldResume = true;
    private boolean wantNewFile = false;
    private Grid myGameGrid;
    private GamePane myGamePane;
    private TextField authorField;
    private TextField titleField;
    private TextArea descriptionField;
    private ResourceBundle objectIdBundle = ResourceBundle.getBundle("resources.ObjectID");
    private ResourceBundle titlesBundle = ResourceBundle.getBundle("resources.title");

    public UserOptions(GamePane myPane, Grid grid) {
        myGamePane = myPane;
        myGameGrid = grid;
        setUpButtons();
        addButtonsToGui();
    }

    private void addButtonsToGui() {
        for(int i =0 ; i < setUpButtons().size(); i++) {
            Button eachbutton = setUpButtons().get(i);
            this.setColumnIndex(eachbutton, i);
            this.getChildren().add(eachbutton);
            System.out.println(i);
        }
    }


    private List<Button> setUpButtons() {

        Button resumeButton = new Button(objectIdBundle.getString("ResumeButton"));
        Button loadButton = new Button(objectIdBundle.getString("LoadButton"));
        Button quitButton = new Button(objectIdBundle.getString("QuitButton"));
        Button nextButton = new Button(objectIdBundle.getString("NextButton"));
        Button pauseButton = new Button(objectIdBundle.getString("PauseButton"));
        Button saveButton = new Button (objectIdBundle.getString("SaveButton"));
        //pauseButton.getStyleClass().add("pauseButton");


        quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        resumeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simShouldResume = true;
            }
        });
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("PAUSE");
                simShouldResume =false;
            }
        });

        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simShouldResume =false;
                wantNewFile = true;
            }
        });

        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simShouldResume =false;
                myGameGrid.performNextStep();
                myGamePane.setUpPane(myGameGrid);
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simShouldResume =false;
                // TODO : add code to save a new file and pop up a dialog that asks for author name
                infoForSaving();

            }
        });

        List<Button> allButtons = Arrays.asList(pauseButton, resumeButton, nextButton, loadButton, quitButton, saveButton);
        return  allButtons;

    }


    private void infoForSaving() {
        VBox userData = new VBox();
        List<String> myCells = Arrays.asList("Author: ","Title: ","Description: " ); //TODO: get data names based on configuration file

        userData.getChildren().add(makeAuthorField(myCells.get(0)));
        userData.getChildren().add(makeTitleField(myCells.get(1)));
        userData.getChildren().add(makeDesciptionField(myCells.get(2)));

        Button okButton = new Button(objectIdBundle.getString("OkButtonText"));
        okButton.setId(objectIdBundle.getString("OkButton"));
        HBox column = new HBox();
        column.getChildren().add(okButton);
        column.setAlignment(Pos.CENTER);
        Stage newWindow = new Stage();
        newWindow.setTitle(titlesBundle.getString("SAVE_FILE"));

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simShouldResume =false;
                newWindow.close();
                writeToFile();
                simShouldResume = true;

            }
        });
        userData.getChildren().add(column);
        userData.setId(objectIdBundle.getString("DataPanel"));
        Scene userInput  = new Scene(userData);
        userInput.getStylesheets().add(getClass().getResource(CONTROL_STYLESHEET_PATH).toExternalForm());
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

    private HBox makeDesciptionField(String labelString) {
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


    private void writeToFile() {
        String myAuthor = authorField.getText();
        String myTitle = titleField.getText();
        String myDescription = descriptionField.getText();
        if(myAuthor.isBlank()) {
            myAuthor = "No Name";
        }
        if(myTitle.isBlank()) {
            myTitle = "No Title";//grid.getType(); //TODO: add my type enum
        }
        if(myDescription.isEmpty()) {
            myDescription = "No description needed";
        }
        System.out.println(myAuthor + " " + " " + myTitle+ " " +myDescription); //TODO save all this in new file
    }


    public boolean shouldcontinue(){
        return simShouldResume;
    }
    public boolean wantNewFile(){
        return wantNewFile;
    }


    public void resetGUI(Grid newgrid) { //change into interface so easier to create JWindow and GUI at same time
        simShouldResume =true;
        wantNewFile = false;
        myGameGrid = newgrid;
    }

}
