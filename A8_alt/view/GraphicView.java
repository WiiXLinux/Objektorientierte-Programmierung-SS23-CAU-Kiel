package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import model.Map;
import model.World;

//TODO initialize the enemy only after a certain amount of steps has been taken

/**
 * A graphical view of the world.
 */
public class GraphicView extends JPanel implements View {
	
	/** The view's width. */
	private final int WIDTH;
	/** The view's height. */
	private final int HEIGHT;
	
	private Dimension fieldDimension;
	
	public GraphicView(int width, int height, Dimension fieldDimension) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.fieldDimension = fieldDimension;
		this.bg = new Rectangle(WIDTH, HEIGHT);
	}
	
	/** The background rectangle. */
	private final Rectangle bg;
	/** The rectangle we're moving. */
	private final Rectangle player = new Rectangle(1, 1);
	/** The Map. */
	private final Rectangle enemy = new Rectangle(25,25);
	private final Map playmap = new Map();
	/**
	 * Creates a new instance.
	 */
	@Override
	public void paint(Graphics g) {
		// Paint background
		g.setColor(Color.RED);
		g.fillRect(bg.x, bg.y, bg.width, bg.height);
		// Paint map
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				if (playmap.getMap(x,y).equals(".")){
					g.setColor(Color.GREEN);
					g.fillRect(x*25, y*25, 25,25);
				}
				if (playmap.getMap(x,y).equals("G")) {
					g.setColor(Color.YELLOW);
					g.fillRect(x*25, y*25, 25, 25);
				}
				if (playmap.getMap(x,y).equals("*")) {
					g.setColor(Color.GRAY);
					g.fillRect(x*25,y*25, 25,25);
				}
			}
		}
		// Paint player
		g.setColor(Color.BLACK);
		g.fillRect(player.x, player.y, player.width, player.height);
		// Paint enemy
		g.setColor(Color.RED);
		g.fillRect(enemy.x, enemy.y, enemy.width, enemy.height);
	}

	@Override
	public void update(World world) {
		// Update players size and location
		player.setSize(fieldDimension);
		player.setLocation((int)
				(world.getPlayerX() * fieldDimension.width),
				(int) (world.getPlayerY() * fieldDimension.height));
		enemy.setSize(fieldDimension);
		enemy.setLocation((int)
				(world.getEnemyX() * fieldDimension.width),
				(int) (world.getEnemyY() * fieldDimension.height));
		repaint();
	}
	
}
