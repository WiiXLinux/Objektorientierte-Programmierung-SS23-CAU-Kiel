package A8.core.model;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import A8.core.lib.LabReader;
import A8.core.view.View;

/** Mini Class to represent a tuple of type E */
class Tuple<E>{
	public Tuple(E x, E y){
		this.x = x;
		this.y = y;
	}
	public E x;
	public E y;

	@Override
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
}


/**
 * The world is our model. It saves the bare minimum of information required to
 * accurately reflect the state of the game. Note how this does not know
 * anything about graphics.
 */
public class World {

	public static final int DIR_RIGHT = 3;
	public static final int DIR_LEFT = 2;
	public static final int DIR_DOWN = 1;
	public static final int DIR_UP = 0;
	/** The world's width. */
	private final int width;
	/** The world's height. */
	private final int height;
	/** The player's x position in the world. */
	private int playerX = 0;
	/** The player's y position in the world. */
	private int playerY = 0;

	/** Set of views registered to be notified of world updates. */
	private final ArrayList<View> views = new ArrayList<>();

	/** boolean indicating if the game is over */
	private boolean game_over = false;
	/** boolean indicating if the player will collide or not */
	private boolean collision = false;
	/** 2D Int-Array to represent each square.
	 * 0 -> empty square
	 * 1 -> wall square
	 * 2 -> enemy square
	 */
	private int[][] field;
	/** Array of thing the world should do when the player tries to move to a new square */
	private final Runnable[] action_on_new_move = new Runnable[]{
			// index 0, empty square
			// Since the square is empty, do nothing
			() -> {},
			// index 1, wall square
			// Collision solution is now handled in a different way, so do nothing
			() -> {},
			// index 2, enemy square
			// it seems the player ran into an enemy, or the enemy ran into the player, so the game is over.
			() -> game_over = true
	};

	/** Score system */
	int score = 0;

	/**
	 * Creates a new world with the given size.
	 */
	public World(int width, int height) {
		// Normally, we would check the arguments for proper values
		this.width = width;
		this.height = height;
		// Initialise field size
		this.field = new int[width][height];
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				this.field[i][j] = (int)(1.5 * Math.random()); // Either 0 or 1.
			}
		}
		this.field[0][0] = 0;  // Give the player some space
		this.field[width - 2][height - 2] = 2;  // Add one enemy at the border to test.
		this.field[1][1] = 2;
	}


	/**
	 * Creates a given world with set size.
	 * @param path Path to the labyrinth file.
	 */
	public World(int width, int height, String path) throws IOException {
		this.field = LabReader.parse(path, width, height);
		this.width = field.length;
		this.height = field[0].length;
	}

	///////////////////////////////////////////////////////////////////////////
	// Getters and Setters

	public int getScore() {
		return score;
	}
	public boolean isGame_over() {
		return game_over;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPlayerX() {
		return playerX;
	}

	// Needed to display each square on their own.
	public int[][] getField(){
		return this.field;
	}

	public void setPlayerX(int playerX) {
		playerX = Math.max(0, playerX);
		playerX = Math.min(getWidth() - 1, playerX);

		// Run the action handler.
		this.action_on_new_move[this.field[playerX][this.playerY]].run();


		this.playerX = playerX;

		updateViews();
	}

	public void setPlayerY(int playerY) {
		playerY = Math.max(0, playerY);
		playerY = Math.min(getHeight() - 1, playerY);

		// Run the action handler.
		this.action_on_new_move[this.field[playerX][this.playerY]].run();

		this.playerY = playerY;

		updateViews();
	}

	public int getPlayerY() {
		return playerY;
	}
	/**
	 * Method to update the play field after every step.
	 * Really slow implementation, but I don't care because the game is turn based and not real time.
	 */
	public void updateField(){
		// Clone the old field
		int[][] newField = this.field.clone();

		for (int i = 0; i < this.width; i++){
			for (int j = 0; j < this.height; j++){
				if (this.field[i][j] == 2){
					newField[i][j] = 0;

					// Calculate the environment of an enemy to look where they could go etc.
					// This is in form of a 3x3 matrix:
					// [no, 	UP, 	no]
					// [LEFT, 	no, 	RIGHT]
					// [no, 	DOWN,	no]
					// Initialise the 2D-array.
					boolean[][] lookAround = new boolean[][]{
							{false,	false, false},
							{false,	false, false},
							{false, false, false}
					};

					// Now get the real values at UP, LEFT, RIGHT and DOWN
					// Looking at this can be a bit confusing, because the "x/y coordinates" are "y/x" in the matrix.
					// We have to try catch because of border issues.
					// Remember for the next time: Never do it like this, if you have something like an array to
					// represent your play field, just add a frame of unpassable barriers to it...
					try {lookAround[0][1] = field[i][j-1] == 0;} catch (IndexOutOfBoundsException ignore) {lookAround[0][1] = false;}
					try {lookAround[1][0] = field[i-1][j] == 0;} catch (IndexOutOfBoundsException ignore) {lookAround[1][0] = false;}
					try {lookAround[1][2] = field[i+1][j] == 0;} catch (IndexOutOfBoundsException ignore) {lookAround[1][2] = false;}
					try {lookAround[2][1] = field[i][j+1] == 0;} catch (IndexOutOfBoundsException ignore) {lookAround[2][1] = false;}

					Tuple<Integer>[] possibleTargets = new Tuple[4];
					int lenOfThat = 0;
					for (int k = 0; k < 3; k++){
						for (int l = 0; l < 3; l++)
							if (lookAround[k][l]){
								// Here we convert back to "x/y" and then change them to be differences in coordinates.
								// For example UP and LEFT will give {(0, -1), (-1, 0)}.
								possibleTargets[lenOfThat] = new Tuple<Integer>(l - 1, k - 1);
								lenOfThat++;
							}
					}
					// for (int k = 0; k < lenOfThat; k++)
					// 		System.out.println(possibleTargets[k]);


					// Now roll the dice and pick the next direction.
					int index = (int)(lenOfThat * Math.random());
					// If the enemy ran into the player, the game is over
					if (i + possibleTargets[index].x == playerX && j + possibleTargets[index].y == playerY){
						game_over = true;
					}
					// set the next position of the enemy.
					newField[i + possibleTargets[index].x][j + possibleTargets[index].y] = 2;
				}
			}
		}
		// Update
		this.field = newField;
	}


	///////////////////////////////////////////////////////////////////////////
	// Player Management
	
	/**
	 * Moves the player along the given direction.
	 * 
	 * @param direction where to move. 1 up, 2 down, 3, left, 4 right
	 */
	public void movePlayer(int direction) {
		if (game_over){
			// "delete" the player
			field[playerX][playerY] = 0;
			// call this to notify the view
			updateViews();
		} else {
			// The direction tells us exactly how much we need to move along
			// every direction.
			// Collision had to be implemented in the following methods,
			// because it would have to change the project structure.
			int newX = getPlayerX() + Direction.getDeltaX(direction);
			try {
				if (field[newX][getPlayerY()] != 1)
					setPlayerX(newX);
			} catch (IndexOutOfBoundsException ignored) {
				// Collide with corner
			}

			int newY = getPlayerY() + Direction.getDeltaY(direction);
			try {
				if (field[getPlayerX()][newY] != 1)
					setPlayerY(newY);
			} catch (IndexOutOfBoundsException ignored) {
				// ...
			}

			// Update enemy movements
			updateField();
			// 100 points for each survived turn
			score += 100;
		}
	}

	///////////////////////////////////////////////////////////////////////////
	// View Management

	/**
	 * Adds the given view of the world and updates it once. Once registered through
	 * this method, the view will receive updates whenever the world changes.
	 * 
	 * @param view the view to be registered.
	 */
	public void registerView(View view) {
		views.add(view);
		view.update(this);
	}

	/**
	 * Updates all views by calling their {@link View#update(World)} methods.
	 */
	private void updateViews() {
		for (int i = 0; i < views.size(); i++) {
			views.get(i).update(this);
		}
	}

}
