class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            int rowIndex = mid / cols;
            int colIndex = mid % cols;
            if (matrix[rowIndex][colIndex] > target) {
                right = mid - 1;
            } else if (matrix[rowIndex][colIndex] < target) {
                left = mid + 1;
            } else {
                res = mid;
                break;
            }
        }

        return res != -1;
    }
}
