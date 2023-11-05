package BinarySearch;

// Time - O(log n)
// Space - O(1)
public class BinarySearch {
    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    int binarySearch(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (target > nums[mid]) {
            return binarySearch(nums, mid + 1, right, target);
        }

        return binarySearch(nums, left, mid - 1, target);
    }
}
