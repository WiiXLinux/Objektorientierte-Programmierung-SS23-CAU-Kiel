package model;

import java.util.ArrayList;
import java.util.Random;

import view.View;

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
	/** Enemy's X position in the world. */
	private int enemyX = 8;
	/** Enemy's Y position in the world. */
	private int enemyY = 8;
	/** Set of views registered to be notified of world updates. */
	private final ArrayList<View> views = new ArrayList<>();
	private final Map playmap = new Map();
	/**
	 * Creates a new world with the given size.t
	 */
	public World(int width, int height) {
		// Normally, we would check the arguments for proper values
		this.width = width;
		this.height = height;
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
		//System.out.println("player X: " + playerX); //used for debugging right now
		return playerX;
	}

	public void setPlayerX(int playerX) {
		playerX = Math.max(0, playerX);
		playerX = Math.min(getWidth() - 1, playerX);
		this.playerX = playerX;
		
		updateViews();
	}

	public int getPlayerY() {
		//System.out.println("playerY: " + playerY); //used for debugging right now
		return playerY;
	}

	public void setPlayerY(int playerY) {
		playerY = Math.max(0, playerY);
		playerY = Math.min(getHeight() - 1, playerY);
		this.playerY = playerY;
		
		updateViews();
	}

	public int getEnemyX() {
		//System.out.println(enemyX);
		return enemyX;
	}

	public void setEnemyX(int enemyX) {
		enemyX = Math.max(0, enemyX);
		enemyX = Math.min(getWidth() -1, enemyX);
		this.enemyX = enemyX;

		updateViews();
	}

	public int getEnemyY() {
		//System.out.println(enemyY);
		return enemyY;
	}

	public void setEnemyY(int enemyY) {
		enemyY = Math.max(0, enemyY);
		enemyY = Math.min(getWidth() -1, enemyY);
		this.enemyY = enemyY;

		updateViews();
	}

	//GOAL FIELD

	public int getGoalX() {
        /** method that returns the X position of
         * the goal field.
         */
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (playmap.getMap(j,i).equals("G")) {
                    int goalX = j;
					return goalX;
                }
            }
        }
		return 0;
    }

	private int getGoalY() {
		/** method that returns the Y position of
		 * the goal field.
		 */
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (playmap.getMap(j,i).equals("G")) {
					int goalY = i;
					return goalY;
				}
			}
		}
		return 0;
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
		// every direction
		
		//direction is given as one of four ints (so down, up, left, right)
		//we need to calculate the new position of the player and then look
		//up in playmap.getMap() whether this is a wall or not and after that
		//move the player, so the player doesn't get to make illegal moves

		//storing this as a Strings makes it a little bit easier to read for now
		boolean playermoved = false;
		String temp = playmap.getMap(
								getPlayerX() + Direction.getDeltaX(direction),
								getPlayerY() + Direction.getDeltaY(direction)
								);
		//playmap.printMap();
		if (temp.equals(".")) {
			setPlayerX(getPlayerX() + Direction.getDeltaX(direction));
			setPlayerY(getPlayerY() + Direction.getDeltaY(direction));
			playermoved = true;
		}
		else if (// win condition
			// sysout only for testing purposes. Event will be triggered,
			// as soon as won is true
				getPlayerX() + Direction.getDeltaX(direction) == getGoalX() &&
				getPlayerY() + Direction.getDeltaY(direction) == getGoalY()) {
			setPlayerX(getPlayerX() + Direction.getDeltaX(direction));
			setPlayerY(getPlayerY() + Direction.getDeltaY(direction));
			Boolean won = true;
			System.out.println("YOU WON!");
		}

		if (playermoved == true) {
			moveEnemy();
			playermoved = false;
		}
	}

	/** function that moves the enemy. Calls nextStep() in order to determine
	 * the direction it is going to move in next
	 */
	public void moveEnemy() {
		System.out.println("enemy move");
		int direction = nextStep();
		String temp = playmap.getMap(
								getEnemyX() + Direction.getDeltaX(direction),
								getEnemyY() + Direction.getDeltaY(direction)
								);
		//playmap.printMap();
		if (temp.equals(".")) {
			setEnemyX(getEnemyX() + Direction.getDeltaX(direction));
			setEnemyY(getEnemyY() + Direction.getDeltaY(direction));
		}

		if (getEnemyX() == getPlayerX() && getEnemyY() == getPlayerY()) {
			gameOver();
		}
	}
		

	/** ENEMY MOVEMENT
	 * for each turn the player gets to take the enemy also gets to take
	 * a turn. Depending on the difficulty of the level the enemy will be
	 * initialized n turns after the player (easy difficulty: 4 free turns
	 * for the player; medium difficulty: 3 free turns for the player; hard
	 * difficulty: 2 free turns for the player
	 *
	 * enemy movement is calculated using a shortest path algorithm
	 */

	//recursively try every direction through the labyrinth
	//if a field is checked, mark it as done
	//if a field is not done, recursively call yourself to
	//check all other fields and return done
	//if shortest path to player position has been found,
	//go back as many steps to enemy starting position as
	//needed and return the direction (1-4) of the first
	//step, enemy has to take

	/** helper to check if a point on the map is a wall */
	boolean isWall (int row, int col) {
		String temp = playmap.getMap(row, col);
		if (temp.equals("*")) {
			return true;
		}
		return false;
	}

	/** helper to check if a point on the map is the player */
	boolean isPlayer (int row, int col) {
		if (row == getPlayerX() && col == getPlayerY()) {
			return true;
		}
		return false;
	}

	/** loose condition */
	public void gameOver() {
		System.out.println("YOU LOSE!");
		boolean gameover = true;
	}

	//boolean values to store information about the map
	//used by nextStep() and findPath().
	boolean[][] alreadychecked = new boolean[10][10];
	boolean[][] path = new boolean[10][10];
	
	/** method that initializes alreadychecked and path with values
	* and calls findPath()in order to determine the next step the enemy
	* is going to take.
	* @return one of four directions (1 UP, 2 DOWN, 3 LEFT, 4 RIGHT)
	*/
	private int nextStep() {
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				alreadychecked[row][col] = false;
				path[row][col] = false;
			}
		}
		findPath(getEnemyX(), getEnemyY());

		//look left
		if (path[getEnemyX() -1][getEnemyY()] == true) {
			if (isWall(getEnemyX()-1, getEnemyY()) == false) {
				if (getPreviousX() != getEnemyX() -1 && getPreviousY() != getEnemyY()) {
					int x = getEnemyX() -1;
					int y = getEnemyY();
					saveSteps(x,y);
					return 3;
				}
				else {
					return randomStep();
				}
			}
			else if(getEnemyX() -1 == getPlayerX() && getEnemyY() == getPlayerY()) {
				if (getPreviousX() != getEnemyX() -1 && getPreviousY() != getEnemyY()) {
					int x = getEnemyX() -1;
					int y = getEnemyY();
					saveSteps(x,y);
					return 3;
				}
				else {
					return randomStep();
				}
			}
		}
		//look up
		else if (path[getEnemyX()][getEnemyY() -1] == true){
			if (isWall(getEnemyX(), getEnemyY() -1) == false) {
				if (getPreviousX() != getEnemyX() && getPreviousY() != getEnemyY() -1) {
				int x = getEnemyX();
				int y = getEnemyY() -1;
				saveSteps(x,y);
				return 1;
				}
				else {
					return randomStep();
				}
			}
			else if (getEnemyX() == getPlayerX() && getEnemyY() -1 == getPlayerY()) {
				if (getPreviousX() != getEnemyX() && getPreviousY() != getEnemyY() -1) {
					int x = getEnemyX();
					int y = getEnemyY()-1;
					saveSteps(x,y);
					return 1;
				}
				else {
					return randomStep();
				}
			}
		}
		//look down
		else if (path[getEnemyX()][getEnemyY() +1] == true){
			if (isWall(getEnemyX(), getEnemyY() +1) == false) {
				if (getPreviousX() != getEnemyX() && getPreviousY() != getEnemyY() +1) {
					int x = getEnemyX();
					int y = getEnemyY()+1;
					saveSteps(x,y);
					return 2;
				}
				else {
					return randomStep();
				}
			}
			else if (getEnemyX() == getPlayerX() && getEnemyY() +1 == getPlayerY()) {
				if (getPreviousX() != getEnemyX() && getPreviousY() != getEnemyY() +1) {
					int x = getEnemyX();
					int y = getEnemyY()+1;
					saveSteps(x,y);
					return 2;
				}
				else {
					return randomStep();
				}
			}
		}
		//look right
		else if (path[getEnemyX() +1][getEnemyY()] == true) {
			if (isWall(getEnemyX() +1, getEnemyY()) == false) {
				if (getPreviousX() != getEnemyX() +1 && getPreviousY() != getEnemyY()) {
					int x = getEnemyX()+1;
					int y = getEnemyY();
					saveSteps(x,y);
					return 4;
				}
				else {
					return randomStep();
				}
			}
			else if (getEnemyX() +1 == getPlayerX() && getEnemyY() == getPlayerY()) {
				if (getPreviousX() != getEnemyX() +1 && getPreviousY() != getEnemyY()) {
					int x = getEnemyX()+1;
					int y = getEnemyY();
					saveSteps(x,y);
					return 4;
				}
				else {
					return randomStep();
				}
			}
		}
		return randomStep();
	}

	/** algorithm that computes a path from the enemy position
	* to the player position. calls itself iteratively.
	* @param x current x position of the enemy
	* @param y current y position of the enemy
	* @return a boolean value to make sure the program terminates
	*/
	private boolean findPath(int x, int y) {
		if (x == getPlayerX() && y == getPlayerY()) {
			return true;
		}
		if (isWall(x,y) == true || alreadychecked[x][y] == true) {
			return false;
		}
		alreadychecked[x][y] = true;
		if (x != 0) {
			if (findPath(x-1,y)) {
				path[x][y] = true;
				return true;
			}
		}
		if (x != 10) {
			if (findPath(x+1,y)) {
				path[x][y] = true;
				return true;
			}
		}
		if (y != 0) {
			if (findPath(x,y-1)) {
				path[x][y] = true;
				return true;
			}
		}
		if (y != 10) {
			if (findPath(x,y+1)) {
				path[x][y] = true;
				return true;
			}
		}
		return false;
	}

	//used to store the previous steps of the enemy
	ArrayList<Integer> enemysteps = new ArrayList<Integer>();
	{ //fill it with 0's when program is first run
		for (int i = 0; i < 4; i++) {
			enemysteps.add(0);
		}
	}

	/** method that stores the previous steps of the enemy
	* in order to make sure, the enemy does not get stuck in
	* a step repeating cycle
	@param x x position to be saved
	@param y y position to be saved
	*/
	private void saveSteps(int x, int y) {
		//it is not necessary to store more than four positions
		//(so two steps), so whenever the size is larger than 3
		//when this function is called, the oldest two elements get
		//removed to make space for the new positions
		if (enemysteps.size() > 3) {
			enemysteps.remove(1);
			enemysteps.remove(0);
		}
		enemysteps.add(x);
		enemysteps.add(y);
	}

	/** method that returns the second to
	 * last X position of the enemy
	 * @return previous x position of enemy
	 * */
	private int getPreviousX() {
		return enemysteps.get(0);
	}
	/** returns the second to last Y position of the enemy
	 * @return previous y position of enemy
	*/
	private int getPreviousY() {
		return enemysteps.get(1);
	}

	/** if the algorithm would result in the enemy making the
	* same two moves over and over again, instead of that a
	* random direction will be chosen for the enemy, determined
	* by randomStep()
	* @return one of four random directions (1 to 4)
	*/
	private int randomStep() {
		Random random = new Random();
		int rand = 0;
		while (true) {
			rand = random.nextInt(5);
			if(rand != 0) break;
		}
		//store the new random position in enemystep Array
		int x = getEnemyX() + Direction.getDeltaX(rand);
		int y = getEnemyY() + Direction.getDeltaY(rand);
		saveSteps(x,y);
		return rand;
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
