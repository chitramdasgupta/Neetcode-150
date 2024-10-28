class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int hold = 0;        // dp[i+1][0]
        int notHold = 0;     // dp[i+1][1]
        int notHoldNext = 0; // dp[i+2][1]

        for (int i = prices.length - 1; i >= 0; i--) {
            int tempHold = Math.max(hold, prices[i] + notHoldNext);
            int tempNotHold = Math.max(notHold, -prices[i] + hold);

            notHoldNext = notHold;
            hold = tempHold;
            notHold = tempNotHold;
        }

        return notHold;
    }
}
