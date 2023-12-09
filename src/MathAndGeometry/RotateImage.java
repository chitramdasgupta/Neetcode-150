package MathAndGeometry;

// Time - O(n ^ 2)
// Space - O(1)
public class RotateImage {
    public void rotate(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length - 1;

        while (left < right) {
            for (int i = 0; i < right - left; ++i) {
                // The top and bottom can be set to the left and right, respectively
                // because our matrix is 2D
                int top = left;
                int bottom = right;

                //save the top left
                int topLeft = matrix[top][left + i];
                //move bottom left into top left
                matrix[top][left + i] = matrix[bottom - i][left];
                // move bottom right into bottom left
                matrix[bottom - i][left] = matrix[bottom][right - i];
                // move top right into bottom right
                matrix[bottom][right - i] = matrix[top + i][right];
                // move top left into top right
                matrix[top + i][right] = topLeft;
            }

            left += 1;
            right -= 1;
        }
    }
}
