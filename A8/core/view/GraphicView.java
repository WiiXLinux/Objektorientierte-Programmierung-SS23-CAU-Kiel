package A8.core.view;

import java.awt.*;

import javax.swing.*;

import A8.core.model.World;

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

    private Dimension fieldDimension;

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
     * Creates a new instance.
     */
    @Override
    public void paint(Graphics g) {
        // Paint background
        g.setColor(Color.RED);
        g.fillRect(bg.x, bg.y, bg.width, bg.height);

        if (gameOver) {
            g.setColor(Color.BLACK);
            g.drawString("Game Over\nScore: " + score, bg.width / 2, bg.height / 2);
        } else {
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

        // Update the play field
        current_field = world.getField();

        // Update the current game state.
        gameOver = world.isGame_over();

        // Update the current score
        score = world.getScore();

        // Update players size and location
        player.setSize(fieldDimension);
        player.setLocation((int)
                        (world.getPlayerX() * fieldDimension.width),
                (int) (world.getPlayerY() * fieldDimension.height));


        repaint();
    }

}
