package A8.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * "static" class serving as a namespace for a file parser for labyrinths
 */
public class LabReader {
    /**
     * Parses the input of a file to an int[][].
     * Useful to load a labyrinth from a file.
     *
     * @throws java.io.IOException Should not be try-caught
     * @param path   Path of the file to parse
     * @param width  width of the labyrinth
     * @param height height of the labyrinth
     * @return int[][] from File(path)
     */
    public static int[][] parse(String path, int width, int height) throws IOException {
        // Check some things.
        File input = new File(path);
        if (input.isDirectory()) {
            throw new FileNotFoundException("The file you gave is a directory.");
        }
        // "open" the file
        FileInputStream stream = new FileInputStream(input);

        // read all of it
        byte[] allBytes = stream.readAllBytes();
        // and convert it to a String
        String fromBytes = byteArrToString(allBytes);

        // init temporary int[][].
        // has user given bounds, that have to fit to the file.
        int[][] temp = new int[width][height];

        // indexes for current read char
        int i = 0, j = 0;

        System.out.println(fromBytes);

        for (char c : fromBytes.toCharArray()) {
            if (c == '\n') {
                // increase horizontal index
                j++;
                // reset vertical index
                i = 0;
            } else {
                // parse the char.
                try {
                    temp[i][j] = (int) c - 48;
                } catch (IndexOutOfBoundsException ignore) {
                    // if out of bounds, there was something wrong, but we don't care,
                    // since the default value for an int is 0.
                }

                // increase vertical index
                i++;
            }
        }
        System.out.println(Arrays.deepToString(temp));
        return temp;
    }

    /**
     * Private method that converts byte arrays to Strings
     *
     * @param input char-sequence as byte array
     * @return (String)input
     */
    private static String byteArrToString(byte[] input) {
        StringBuilder temp = new StringBuilder();
        for (byte b : input) {
            temp.append((char) b);
        }
        return temp.toString();
    }
}
