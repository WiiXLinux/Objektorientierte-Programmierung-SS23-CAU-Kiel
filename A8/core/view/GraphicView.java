package A8.core.view;

import A8.core.controller.Labyrinth;
import A8.core.model.World;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * A graphical view of the world.
 */
public class GraphicView extends JPanel implements View {

    /**
     * The view's width.
     */
    private final int WIDTH;
    /**
     * The view's height.
     */
    private final int HEIGHT;
    /**
     * The dimension of one square
     */
    private final Dimension fieldDimension;
    /**
     * Constructor that should be used
     */
    public GraphicView(int width, int height, Dimension fieldDimension) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.fieldDimension = fieldDimension;
        this.bg = new Rectangle(WIDTH, HEIGHT);
        this.current_field = new int[fieldDimension.width][fieldDimension.height];
    }

    /**
     * The background rectangle.
     */
    private final Rectangle bg;
    /**
     * The rectangle we're moving.
     */
    private final Rectangle player = new Rectangle(1, 1);

    /**
     * Colors for the different, non-player square types:
     * White for a free square,
     * Gray for a wall square and
     * Red for an enemy.
     */
    private final Color[] squares = new Color[]{Color.WHITE, Color.GRAY, Color.RED};
    /**
     * Currently known play field
     */
    private int[][] current_field;
    /**
     * Currently known state of the game
     */
    private boolean gameOver = false;
    /**
     * Current score
     */
    private int score = 0;
    /**
     * Should we still render?
     */
    private boolean stop = false;

    @Override
    public void paint(Graphics g) {
        // If the game is over, change to a new "scene"
        if (!gameOver) {
            // Paint background
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(bg.x, bg.y, bg.width, bg.height);

            // Paint squares, again, a slow implementation, but turn based, so...
            for (int i = 0; i < current_field.length; i++) {
                for (int j = 0; j < current_field[0].length; j++) {
                    g.setColor(squares[current_field[i][j]]);
                    g.fillRect(player.width * i, player.height * j, player.width, player.height);
                }
            }
            // Paint player
            g.setColor(Color.BLUE);
            g.fillRect(player.x, player.y, player.width, player.height);
        }
    }

    @Override
    public void update(World world) {
        if (gameOver && !stop) {
            this.setVisible(false);

            stop = true;
            System.out.println("Game Over");

            JFrame gameOver = new JFrame();

            gameOver.setTitle("Game Over");
            gameOver.setBounds(0, 0, 500, 500);
            gameOver.setDefaultCloseOperation(EXIT_ON_CLOSE);
            gameOver.setLayout(null);
            gameOver.setResizable(false);

            Font font = new Font(null, Font.ITALIC, 50);

            JLabel title = new JLabel("Game Over");
            title.setFont(font);
            title.setBounds(30, 200, 500, 50);


            JLabel scoreText = new JLabel("Score: " + this.score);
            scoreText.setFont(font);
            scoreText.setBounds(30, 250, 500, 50);

            JButton start = new JButton("Replay?");
            start.setBounds(30, 300, 300, 50);
            start.addActionListener(e -> {
                String width = "11";
                String height = "4";
                String path = "src/A8/bin/test/lab.io";
                Labyrinth.main(new String[]{width, height, path});
                gameOver.setVisible(false);
                this.setVisible(false);
            });

            gameOver.add(title);
            gameOver.add(scoreText);
            gameOver.add(start);

            gameOver.setVisible(true);


            // ignore when gameOver && stop (so that we don't create a million new windows)
        } else if (!gameOver) {
            // Update the play field
            current_field = world.getField();

            // Update the current game state.
            gameOver = world.isGame_over();

            // Update the current score
            score = world.getScore();

            // Update players size and location
            player.setSize(fieldDimension);
            player.setLocation(world.getPlayerX() * fieldDimension.width,
                    world.getPlayerY() * fieldDimension.height);


            repaint();
        }
    }

}
