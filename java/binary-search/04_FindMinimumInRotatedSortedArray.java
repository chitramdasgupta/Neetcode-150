class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int res = nums[0];
        while (left <= right) {
            if (nums[left] <= nums[right]) {
                res = Math.min(nums[left], res);
                break;
            }

            int mid = left + (right - left) / 2;
            res = Math.min(nums[mid], res);
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }
}
