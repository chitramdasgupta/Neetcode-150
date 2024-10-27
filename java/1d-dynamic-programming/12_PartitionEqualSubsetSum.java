package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canPartition(new int[] {1, 2, 5}));
    }
}

class Solution {
    public boolean canPartition(int[] nums) {
        int total = Arrays.stream(nums).sum();
        if (total % 2 != 0) {
            return false;
        }

        total /= 2;

        boolean[] dp = new boolean[total + 1];
        dp[0] = true;

        for (int num : nums) {
            if (num > total) {
                continue;
            }

            for (int sum = total; sum >= num; --sum) {
                if (dp[sum - num]) {
                    dp[sum] = true;
                }
            }
        }

        return dp[total];
    }
}
