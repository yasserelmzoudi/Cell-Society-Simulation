package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.grid.Grid;
import java.util.Arrays;
import java.util.List;

public class GUI extends GridPane{
    private boolean simShouldResume = true;
    private boolean wantNewFile = false;
    private Grid myGameGrid;
    private GamePane myGamePane;



    public GUI(GamePane myPane, Grid grid) {
        myGamePane = myPane;
        myGameGrid = grid;
        for(int i =0 ; i < setUpButtons().size(); i++) {
            Button eachbutton = setUpButtons().get(i);
            this.setRowIndex(eachbutton, i);
            this.getChildren().add(eachbutton);
            System.out.println(i);
        }

    }


    private List<Button> setUpButtons() {

        Button resumebutton = new Button("Resume");
        Button loadbutton = new Button("Load new File");
        Button quitbutton = new Button("Quit Simulation");
        Button nextbutton = new Button("Next");
        Button pausebutton = new Button("Pause");

        quitbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        resumebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simShouldResume = true;
            }
        });
        pausebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simShouldResume =false;
            }
        });

        loadbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("load");
                simShouldResume =false;
                wantNewFile = true;
            }
        });

        nextbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                simShouldResume =false;
                myGameGrid.performNextStep();
                myGamePane.setUpPane(myGameGrid);
            }
        });

        List<Button> allButtons = Arrays.asList(pausebutton, resumebutton, nextbutton, loadbutton, quitbutton);
        return  allButtons;

    }


    public boolean shouldcontinue(){
        return simShouldResume;
    }
    public boolean wantnewFile(){
        return wantNewFile;
    }


    public void resetGUI(Grid newgrid) { //change into interface so easier to create JWindow and GUI at same time
        simShouldResume =true;
        wantNewFile = false;
        myGameGrid = newgrid;
    }

}
