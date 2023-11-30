package OneDimensionalDynamicProgramming;

// Time - O(n)
// Space - O(1)
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        // res will be the maximum product of a subarray in the entire array
        // Idea: the minProduct and maxProduct will change for every iteration
        // computing the values, we will store the maximum of all subarrays in res
        // The above idea is why this is a DP problem.
        int res = nums[0];
        // The minimum product up to the current element
        int minProduct = 1; // We treat 1 as a neutral element
        // The maximum product up to the current element
        int maxProduct = 1;

        for (int num : nums) {
            int temp = maxProduct * num;
            // For the maximum, we can either multiply the current num with the max
            // (if the max is positive and current element is positive as well) or
            // start afresh from the current num or multiply the current num
            // with the minimum (if the current num is negative)
            maxProduct = Math.max(Math.max(minProduct * num, maxProduct * num), num);
            minProduct = Math.min(Math.min(minProduct * num, temp), num);

            res = Math.max(res, maxProduct);
        }

        return res;
    }
}
