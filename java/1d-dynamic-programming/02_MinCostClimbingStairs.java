class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;

        // If we start from the destination, both the costs are 0
        int one = 0;
        int two = 0;

        for (int i = len - 1; i >= 0; --i) {
            // Recurrence relation:
            // cost if we start at current index =
            // cost[i] + min(cost of taking one step, cost of taking two steps)
            int temp = cost[i] + Math.min(one, two);

            two = one;
            one = temp;
        }

        return Math.min(one, two);
     }
}
