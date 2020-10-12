package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.grid.Grid;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class UserOptions extends GridPane{

    private boolean simShouldResume = false;
    private boolean wantNewFile = false;
    private Grid myGameGrid;
    private GamePane myGamePane;
    private ResourceBundle bundle = ResourceBundle.getBundle("resources.ButtonText");

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

        Button resumeButton = new Button(bundle.getString("ResumeButton"));
        //resumeButton.setId("resumeButton");
        Button loadButton = new Button(bundle.getString("LoadButton"));
        //loadButton.setId("loadButton");
        Button quitButton = new Button(bundle.getString("QuitButton"));
        //quitButton.setId("quitButton");
        Button nextButton = new Button(bundle.getString("NextButton"));
       // nextButton.setId("nextButton");
        Button pauseButton = new Button(bundle.getString("PauseButton"));
        Button saveButton = new Button (bundle.getString("SaveButton"));
        //pauseButton.setId("pauseButton");
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
            }
        });

        List<Button> allButtons = Arrays.asList(pauseButton, resumeButton, nextButton, loadButton, quitButton, saveButton);
        return  allButtons;

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
