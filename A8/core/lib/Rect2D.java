package A8.core.lib;

import java.awt.Rectangle;

/**
 * Class extending from Rectangle to implement a main parser.
 */
public class Rect2D extends Rectangle {
    // Copy some constructors 1
    public Rect2D(){
        super();
    }
    // Copy some constructors 2
    public Rect2D(int width, int height){
        super(width, height);
    }

    /**
     * Parses a Rectangle to a String array that can be used as input for a main method.
     * @return ["$(width)", "$(height)"]
     */
    public String[] toStringArr(){
        return new String[]{
                String.valueOf(this.width),
                String.valueOf(this.height)
        };
    }
}
