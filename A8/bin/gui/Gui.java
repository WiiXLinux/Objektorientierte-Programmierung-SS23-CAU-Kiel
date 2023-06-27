package A8.bin.gui;

import A8.core.controller.Labyrinth;

import javax.swing.*;
import java.awt.*;

/**
 * Implements a whole GUI.
 */
public class Gui extends JFrame {
    /**
     * Constructor that initialises a more or less good-looking JFrame.
     * Has a start and an exit button.
     */
    public Gui() {
        this.setTitle("Launcher for LABYRINTH MAN");
        this.setBounds(0, 0, 500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        JLabel title = new JLabel("LABYRINTH MAN");
        title.setFont(new Font(null, Font.ITALIC, 50));
        title.setBounds(30, 200, 500, 50);
        this.add(title);
        JButton start = new JButton("Start");
        start.setBounds(200, 250, 100, 50);
        start.addActionListener(e -> {
            String width = "11";
            String height = "4";
            String path = "src/A8/bin/test/lab.io";
            Labyrinth.main(new String[]{width, height, path});
            this.setVisible(false);
        });
        this.add(start);
        JButton exit = new JButton("Exit");
        exit.setBounds(200, 300, 100, 50);
        exit.addActionListener(e -> System.exit(1));
        this.add(exit);

        this.setVisible(true);
    }
    /**
     * This is the program you should start.
     */
    public static void main(String[] args) {
        new Gui();
    }
}
