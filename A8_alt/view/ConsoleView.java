package view;

import model.Map;
import model.World;

/**
 * A view that prints the current state of the world to the console upon every
 * update.
 */
public class ConsoleView implements View {

	Map map = new Map();

	@Override
	public void update(World world) {
		// The player's position
		int playerX = world.getPlayerX();
		int playerY = world.getPlayerY();
		int enemyX = world.getEnemyX();
		int enemyY = world.getEnemyY();

		for (int row = 0; row < world.getHeight(); row++) {
			for (int col = 0; col < world.getWidth(); col++) {
				// If the player is here, print #, otherwise print .
				if (col == playerY && row == playerX) {
					System.out.print("#");
				}
				else if (row == enemyY && col == enemyX) {
					System.out.print("!");
				}
				else {
					System.out.print(map.getMap(col,row));
				}
			}

			// A newline after every row
			System.out.println();
		}

		// A newline between every update
		System.out.println();
	}

}
