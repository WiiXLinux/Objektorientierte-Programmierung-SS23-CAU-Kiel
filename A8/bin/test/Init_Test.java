package A8.bin.test;
import A8.core.controller.Labyrinth;
import A8.core.lib.Rect2D;


// Test class for initial program with additional parameters
public class Init_Test {
    public static void main(String[]args){
        Rect2D bounds = new Rect2D(20, 20);
        Labyrinth.main(bounds.toStringArr());
    }
}
