package OneDimensionalDynamicProgramming;

// Time - O(n)
// Space - O(1)
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int second = 0;
        int first = 0;

        for (int i = cost.length - 1; i >= 0; --i) {
            cost[i] += Math.min(first, second);
            second = first;
            first = cost[i];
        }

        return Math.min(cost[0], cost[1]);
    }
}
