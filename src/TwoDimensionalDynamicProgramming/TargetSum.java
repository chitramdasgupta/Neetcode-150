package TwoDimensionalDynamicProgramming;

import java.util.HashMap;
import java.util.Map;

// NOTE: This problem results in TLE for java if I remove the commented lines
// Time - O(n * target)
// Space - O(n * target)
public class TargetSum {
    //    Map<int[], Integer> dp; // { (idx, currTotal) -> no. of ways }
    Map<String, Integer> dp;
    int[] nums;
    int target;

    public int findTargetSumWays(int[] nums, int target) {
        dp = new HashMap<>();
        this.nums = nums;
        this.target = target;
        return backtrack(0, 0);
    }

    int backtrack(int idx, int currTotal) {
        if (idx == nums.length) {
            // 1 means we have found 1 way to add up to the target
            return currTotal == target ? 1 : 0;
        }
//        int[] currKey = {idx, currTotal};
        String sIdx = idx + ", " + currTotal;
        // If the subproblem is already solved, we return the memoized solution
        // to the subproblem
//        if (dp.containsKey(currKey)) {
//            return dp.get(currKey);
//        }
        if (dp.containsKey(sIdx)) {
            return dp.get(sIdx);
        }

        // We count the sum of the number of ways to add up to the target, if we choose
        // to add the current number or subtract the current number from the current total
        int count = backtrack(idx + 1, currTotal + nums[idx])
                + backtrack(idx + 1, currTotal - nums[idx]);
        dp.put(sIdx, count);

        return count;
    }
}
