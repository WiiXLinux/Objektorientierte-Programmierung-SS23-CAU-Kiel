package A8.bin.test;
import A8.core.controller.Labyrinth;
import A8.core.lib.Rect2D;


// Test class for initial program with additional parameters
public class Init_Test {
    public static void main(String[]args){
        Rect2D bounds = new Rect2D(11, 4);
        Labyrinth.main(new String[]{String.valueOf(bounds.width), String.valueOf(bounds.height), "src/A8/bin/test/lab.io"});
    }
}
