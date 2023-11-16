package BinarySearch;

// Time - O(log n)
// Space - O(1)
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // We do not do left <= right because here the left and the right will
        // converge to the same index
        while (left < right) {
            if (nums[left] <= nums[right]) {
                return nums[left];
            }
            int mid = left + (right - left) / 2;
            if (nums[left] <= nums[mid]) {
                left = mid + 1;
            } else {
                // We assign the mid to the right because the mid might be the
                // index of the min element
                right = mid;
            }
        }

        return nums[left];
    }
}
