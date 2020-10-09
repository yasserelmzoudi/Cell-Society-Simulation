package view;
import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame implements GameInterface {

    public GameWindow(Dimension mydimensions) {
        this.setTitle("Conways's Game of Life Simulator");
        this.setSize(mydimensions);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.black);
    }



}
