package Greedy;

// Kadane's algorithm
// Time - O(n)
// Space - O(1)
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int maxTillHere = 0;
        for (int num : nums) {
            maxTillHere += num;
            maxSoFar = Math.max(maxSoFar, maxTillHere);
            if (maxTillHere < 0) {
                maxTillHere = 0;
            }
        }

        return maxSoFar;
    }
}
