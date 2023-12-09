package MathAndGeometry;

import java.util.ArrayList;
import java.util.List;

// Time - O(m * n)
// Space - O(1)
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        while (left <= right && top <= bottom) {
            // Add all the elements of the top row
            for (int i = left; i <= right; ++i) {
                res.add(matrix[top][i]);
            }
            top += 1;

            // Add all the elements of the right column
            for (int i = top; i <= bottom; ++i) {
                res.add(matrix[i][right]);
            }
            right -= 1;

            if (left > right || top > bottom) {
                break;
            }

            // Add all the elements of the bottom row
            for (int i = right; i >= left; --i) {
                res.add(matrix[bottom][i]);
            }
            bottom -= 1;

            // Add all the elements of the left column
            for (int i = bottom; i >= top; --i) {
                res.add(matrix[i][left]);
            }
            left += 1;
        }

        return res;
    }
}
