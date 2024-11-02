class Solution {
    public int maxProfit(int[] prices) {
        int buy = Integer.MAX_VALUE;
        int sell = 0;
        for (int price : prices) {
            if (price < buy) {
                buy = price;
                continue;
            }
            sell = Math.max(price - buy, sell);
        }

        return sell;
    }
}
