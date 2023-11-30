package OneDimensionalDynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Time - O(n x sum(nums))
// Space - O(sum(nums)) because the size of the set is proportional to the
// total sum of the nums
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }

        sum /= 2;
        Set<Integer> dp = new HashSet<>();
        dp.add(0);

        for (int i = nums.length - 1; i >= 0; --i) {
            Set<Integer> nextDp = new HashSet<>();
            for (int s : dp) {
                if (s + nums[i] == sum) {
                    return true;
                }
                nextDp.add(s + nums[i]);
                nextDp.add(s);
            }
            dp = nextDp;
        }

        return false;
    }
}
