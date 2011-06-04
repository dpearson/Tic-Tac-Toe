/*
 * Copyright 2011 David Pearson. GPL v3.
 */

import javax.swing.JFrame;
import java.awt.*;

/**
 * Runs the application.
 *
 * @author David
 */
public class Main {

    /**
     * Main method. Creates a window.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setSize(400, 400);
        frame.setLocation(100, 50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Panel());
        frame.setVisible(true);
    }

}
