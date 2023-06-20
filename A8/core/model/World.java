package A8.core.model;

import java.util.ArrayList;

import A8.core.view.View;

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
			// We have detected an upcoming collision, so signal that the player should not move.
			() -> collision = true,
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
				this.field[i][j] = (int)(2 * Math.random()); // Either 0 or 1.
			}
		}
		this.field[0][0] = 0;  // Give the player some space
		this.field[width - 1][height - 1] = 2;  // Add one enemy at the border to test.
	}

	///////////////////////////////////////////////////////////////////////////
	// Getters and Setters

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

		// Run the collision handler.
		this.action_on_new_move[this.field[playerX][this.playerY]].run();

		System.out.println(collision);

		// Either collide or not.
		if (!collision)
			this.playerX = playerX;
		else
			collision = false;
		
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
		int[][] newField = this.field.clone();

		for (int i = 0; i < this.width; i++){
			for (int j = 0; j < this.height; j++){
				if (this.field[i][j] == 2){
					newField[i][j] = 0;

					// Flip 2 coins, and determine the next coordinates for the enemies.
					// The coins have values of {-1, 1}.
					// For now, they can climb over walls.
					try {
						newField[i + 2 * (int) (2 * Math.random()) - 1][j + 2 * (int) (2 * Math.random()) - 1] = 2;
					} catch (IndexOutOfBoundsException indexOutOfBoundsException){
						// If they try to leave the play field, just don't let them move.
					}
				}
			}
		}
		this.field = newField;
	}

	public void setPlayerY(int playerY) {
		playerY = Math.max(0, playerY);
		playerY = Math.min(getHeight() - 1, playerY);

		// Run the collision handler.
		this.action_on_new_move[this.field[playerX][this.playerY]].run();

		// Either collide or not.
		if (!collision)
			this.playerY = playerY;
		else
			collision = false;
		
		updateViews();
	}

	///////////////////////////////////////////////////////////////////////////
	// Player Management
	
	/**
	 * Moves the player along the given direction.
	 * 
	 * @param direction where to move. 1 up, 2 down, 3, left, 4 right
	 */
	public void movePlayer(int direction) {	
		// The direction tells us exactly how much we need to move along
		// every direction.
		// Collision had to be implemented in the following methods,
		// because it would have to change the project structure.
		if (!collision)
			setPlayerX(getPlayerX() + Direction.getDeltaX(direction));
		if (!collision)
			setPlayerY(getPlayerY() + Direction.getDeltaY(direction));
		collision = false;

		// TODO implement updateField and a score system
		updateField();
		score += 100;
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
