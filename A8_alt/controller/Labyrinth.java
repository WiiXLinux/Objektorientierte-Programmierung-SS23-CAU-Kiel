package controller;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JFrame;

import model.World;
import view.ConsoleView;
import view.GraphicView;

//TODO event triggered on win condition
//TODO implement starting interface, integrating
//restart button, map / difficulty selection, other
//game functions

/**
 * This is our main program. It is responsible for creating all of the objects
 * that are part of the MVC pattern and connecting them with each other.
 */
public class Labyrinth {

	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	// Dimension of the game board (10x10).
            	int width = 10;
    			int height = 10;
    			// Create a new game world.
            	World world = new World(width, height);
                //Map playmap = new Map();
            	
            	// Size of a field in the graphical view.
            	Dimension fieldDimensions = new Dimension(25, 25);
            	// Create and register graphical view.
            	GraphicView gview = new GraphicView(width * fieldDimensions.width, height * fieldDimensions.height, fieldDimensions);
            	world.registerView(gview);
                gview.setVisible(true);

            	// Create and register console view.
        		ConsoleView cview = new ConsoleView();
            	world.registerView(cview);
            	
            	// Create controller and initialize JFrame.
                Controller controller = new Controller(world);
                controller.setTitle("DON'T DIE");
                controller.setResizable(false);
                controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                controller.getContentPane().add(gview);
                // pack() is needed before JFrame size can be calculated.
                controller.pack();

                // Calculate size of window by size of insets (titlebar + border) and size of graphical view.
                Insets insets = controller.getInsets();
                
                int windowX = width * fieldDimensions.width + insets.left + insets.right;
                int windowY = height * fieldDimensions.height + insets.bottom + insets.top;
                Dimension size = new Dimension(windowX, windowY);
                controller.setSize(size);
                controller.setMinimumSize(size);
                controller.setVisible(true);
            }
        });
    }
}
