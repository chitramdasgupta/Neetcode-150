package SlidingWindow;

// Time - O(n)
// Space - O(1)
public class BestTimeBuySellStock {
    public int maxProfit(int[] prices) {
        int maximumProfit = 0;
        int minBuyingPrice = Integer.MAX_VALUE;
        for (int price : prices) {
            minBuyingPrice = Math.min(price, minBuyingPrice);
            maximumProfit = Math.max((price - minBuyingPrice), maximumProfit);
        }
        return maximumProfit;
    }
}
