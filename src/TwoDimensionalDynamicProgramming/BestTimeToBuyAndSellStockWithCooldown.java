package TwoDimensionalDynamicProgramming;

// Time - O(n)
// Space - O(1)
public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        // The max profit if at the last action was buying
        // We assume that we start with buying the stock at day before the beginning
        // Hence in the loop, we can with sell from the first day
        int bought = -prices[0];
        // The max profit if the last action was selling
        int sold = 0;
        // The max profit if the last action was a cool down
        int cool = 0;

        for (int price : prices) {
            int prevSold = sold;
            // We try to sell at the current price
            sold = bought + price;
            // We either continue holding the stock or we buy after cool down
            bought = Math.max(bought, cool - price);
            // We either continue with the cool down or start a new cool down after selling
            cool = Math.max(cool, prevSold);
        }

        // We return the max of selling or cooling down in the previous step
        return Math.max(sold, cool);
    }

}
