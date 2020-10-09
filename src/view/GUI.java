package view;

import model.grid.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;

public class GUI extends JPanel implements ActionListener {
    public static final ResourceBundle resources = ResourceBundle.getBundle("resources.data");
    public static JFrame myInterfaceFrame = new JFrame();
    private JButton resumebutton = new JButton("Resume");
    private JButton pausebutton = new JButton("Pause");
    private JButton nextbutton = new JButton("Next");
    private JButton loadbutton = new JButton("Load new File");
    private JButton quitbutton = new JButton("Quit Simulation");
    private JPanel mybuttonpanel = new JPanel();
    private boolean simshouldresume = true;
    private boolean wantnewfile = false;
    private GamePanel gamePanel;
    private JFrame gameFrame;
    private Grid mygamegrid;


    public GUI(Grid grid, JFrame simulationwindow, GamePanel simulationpanel) {
        gamePanel = simulationpanel;
        gameFrame = simulationwindow;
        mygamegrid = grid;
        mybuttonpanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        mybuttonpanel.setLayout(new GridLayout(2, 3));
        mybuttonpanel.add(resumebutton);
        mybuttonpanel.add(pausebutton);
        mybuttonpanel.add(nextbutton);
        mybuttonpanel.add(loadbutton);
        mybuttonpanel.add(quitbutton);
        resumebutton.addActionListener(this);
        pausebutton.addActionListener(this);
        nextbutton.addActionListener(this);
        loadbutton.addActionListener(this);
        quitbutton.addActionListener(this);
        myInterfaceFrame.add(mybuttonpanel, BorderLayout.CENTER);
        myInterfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myInterfaceFrame.setTitle("User Options");
        myInterfaceFrame.pack();
        myInterfaceFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resumebutton && !simshouldresume) {
            simshouldresume = true;
        }
        if (e.getSource() == pausebutton) {
            simshouldresume =false;
        }

        if (e.getSource() == quitbutton) {
            System.exit(0);
        }

        if (e.getSource() == nextbutton) {
            simshouldresume =false;
            mygamegrid.performNextStep();
            gamePanel.updategrid(mygamegrid);
            gameFrame.add(gamePanel);

        }

        if (e.getSource() == loadbutton) {
            simshouldresume =false;
            wantnewfile = true;
        }
    }

    public boolean shouldcontinue(){
        return simshouldresume;
    }
    public boolean wantnewFile(){
        return wantnewfile;
    }

    public String chooseNewFile() {
        Window parentWindow = FocusManager.getCurrentManager().getActiveWindow();
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(parentWindow);
        java.awt.FileDialog fd = new java.awt.FileDialog(topFrame, "Select New File", java.awt.FileDialog.LOAD);
        fd.setVisible(true);
        String filename = fd.getFile();
        return (new File(filename)).getPath();
    }


    public void resetGUI(Grid newgrid, JFrame newsimulationwindow) { //change into interface so easier to create JWindow and GUI at same time
        simshouldresume =true;
        wantnewfile = false;
        mygamegrid = newgrid;
        gameFrame = newsimulationwindow;
        pausebutton.addActionListener(this);
        resumebutton.addActionListener(this);
        nextbutton.addActionListener(this);
        loadbutton.addActionListener(this);
        quitbutton.addActionListener(this);
    }
}
