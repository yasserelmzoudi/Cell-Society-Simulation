package view;

import javafx.stage.FileChooser;
import model.grid.Grid;
import model.grid.GridReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JPanel implements ActionListener {

    public static JFrame myInterfaceFrame = new JFrame();
    private JButton resumebutton = new JButton("Resume");
    private JButton pausebutton = new JButton("Pause");
    private JButton nextbutton = new JButton("Next");
    private JButton loadbutton = new JButton("Load new File");
    private JButton quitbutton = new JButton("Quit Simulation");
    private JPanel mypanel = new JPanel();
    private boolean simshouldresume = true;
    private boolean wantnewfile = false;
    private JPanel gameSimulation;
    private Grid mygamegrid;


    public GUI(Grid grid, JPanel simulation) {
        gameSimulation = simulation;
        mygamegrid = grid;
        mypanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        mypanel.setLayout(new GridLayout(2, 3));
        mypanel.add(pausebutton);
        mypanel.add(nextbutton);
        mypanel.add(loadbutton);
        mypanel.add(quitbutton);
        mypanel.add(resumebutton);
        pausebutton.addActionListener(this);
        resumebutton.addActionListener(this);
        nextbutton.addActionListener(this);
        loadbutton.addActionListener(this);
        quitbutton.addActionListener(this);
        myInterfaceFrame.add(mypanel, BorderLayout.CENTER);
        myInterfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myInterfaceFrame.setTitle("User Options");
        myInterfaceFrame.pack();
        myInterfaceFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resumebutton) {
            simshouldresume =true;
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
            gameSimulation = ((GameSimulator) gameSimulation).loadnewgrid(mygamegrid);
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
        String directory = fd.getDirectory();
        String filename = fd.getFile();
        return (new File(directory + filename)).getAbsolutePath();
    }

    public void resetGUI(Grid newgrid, JPanel newsimulation) {
        gameSimulation = newsimulation;
        mygamegrid = newgrid;
        pausebutton.addActionListener(this);
        resumebutton.addActionListener(this);
        nextbutton.addActionListener(this);
        loadbutton.addActionListener(this);
        quitbutton.addActionListener(this);
        myInterfaceFrame.add(mypanel, BorderLayout.CENTER);
        myInterfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myInterfaceFrame.setTitle("User Options");
        myInterfaceFrame.pack();
        myInterfaceFrame.setVisible(true);
        simshouldresume =true;
        wantnewfile = false;
    }
}
