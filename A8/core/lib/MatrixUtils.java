package A8.core.lib;

public class MatrixUtils {
    public static String matrixToString(boolean[][] matrix) {
        StringBuilder sb = new StringBuilder("[");
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean value = matrix[i][j];
                String str = String.valueOf(value);
                sb.append(str);

                if (j < cols - 1) {
                    sb.append(",\t");
                }
            }


            sb.append("]\n");
            if (i < rows - 1){
                sb.append("[");
            }
        }

        return sb.toString();
    }

}
