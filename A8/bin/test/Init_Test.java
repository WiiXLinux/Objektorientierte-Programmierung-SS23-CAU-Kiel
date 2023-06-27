package A8.bin.test;

import A8.core.controller.Labyrinth;


/**
 * Test class for initial program
 */
public class Init_Test {
    /**
     * Basically GUI.main without the launcher window.
     * @param args ignored
     */
    public static void main(String[] args) {
        Labyrinth.main(new String[]{"11", "4", "src/A8/bin/test/lab.io"});
    }
}
