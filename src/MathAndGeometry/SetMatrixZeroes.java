package MathAndGeometry;

// Time - O(m * n)
// Space - O(1)
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int nRows = matrix.length;
        int nCols = matrix[0].length;
        boolean rowZero = false;

        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j < nCols; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    if (i == 0) {
                        rowZero = true;
                    } else {
                        matrix[i][0] = 0;
                    }
                }
            }
        }

        for (int i = 1; i < nRows; ++i) {
            for (int j = 1; j < nCols; ++j) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    // Then set all the elements in the row or column to 0
                    matrix[i][j] = 0;
                }
            }
        }

        if (matrix[0][0] == 0) {
            // Then set all the elements in the first column to 0
            for (int i = 0; i < nRows; ++i) {
                matrix[i][0] = 0;
            }
        }

        if (rowZero) {
            // Then set all the elements in the first row to 0
            for (int i = 0; i < nCols; ++i) {
                matrix[0][i] = 0;
            }
        }
    }
}
