package BinarySearch;

// Time - O(log n)
// Space - O(1)
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            // mid is in the left sorted array
            if (nums[mid] >= nums[left]) {
                if (target > nums[mid] || target < nums[left]) {
                    // then our target must be in the right sorted array
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } // mid is in the right sorted array
            else {
                if (target < nums[mid] || target > nums[right]) {
                    // then our target must be in the left sorted array
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }
}
