package BinarySearch;

// Time - O(log (m*n))
// Space - O(1)
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }

        int numRows = matrix.length;
        int numColumns = matrix[0].length;

        int low = 0;
        int high = numRows * numColumns - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (matrix[mid / numColumns][mid % numColumns] == target) {
                return true;
            } else if (matrix[mid / numColumns][mid % numColumns] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}
