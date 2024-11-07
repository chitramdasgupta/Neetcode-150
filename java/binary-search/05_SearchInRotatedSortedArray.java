class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                res = mid;
                break;
            }

            // If we are in left subarray
            if (nums[mid] >= nums[left]) {
                // If target is less than left or greater than mid
                // move to the right portion
                if (target < nums[left] || target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target > nums[right] || target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return res;
    }
}
