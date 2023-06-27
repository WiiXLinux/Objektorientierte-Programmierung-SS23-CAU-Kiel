package A8.core.controller;

import A8.core.model.World;
import A8.core.view.GraphicView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


/**
 * This is our main program. It is responsible for creating all of the objects
 * that are part of the MVC pattern and connecting them with each other.
 */
public class Labyrinth {
    /**
     * Forms the holy trinity between Controller, Viewers and World.
     * @param args width and height of the grid
     */
    public static void main(String[] args) {
        // Dimension of the game board from args (10x10) if args == null.
        int w = 10;
        int h = 10;

        if (args.length != 0) {
            w = Integer.parseInt(args[0]);
            h = Integer.parseInt(args[1]);
        }

        final int width = w;
        final int height = h;


        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {


                // Create a new game world.
                World world = null;
                try {
                    world = new World(width, height, args[2]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // Size of a field in the graphical view.
                Dimension fieldDimensions = new Dimension(25, 25);
                // Create and register graphical view.
                GraphicView gview = new GraphicView(width * fieldDimensions.width, height * fieldDimensions.height, fieldDimensions);
                world.registerView(gview);
                gview.setVisible(true);

                // Create and register console view.
                // ConsoleView cview = new ConsoleView();
                // world.registerView(cview);

                // Create controller and initialize JFrame.
                Controller controller = new Controller(world);
                controller.setTitle("LABYRINTH MAN");
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

                new Thread(() -> {
                    while (true) {
                        // If I remove this it does not work and I don't know why
                        System.out.print("");
                        if (!gview.isVisible()) {
                            controller.setVisible(false);
                            break;
                        }

                    }
                }).start();
            }
        });
    }
}
