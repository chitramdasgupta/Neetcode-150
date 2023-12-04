import TwoDimensionalDynamicProgramming.BestTimeToBuyAndSellStockWithCooldown;

public class Main {
    public static void main(String[] args) {
        int[] prices = { 1, 2, 3, 0, 2 };
        System.out.println(new BestTimeToBuyAndSellStockWithCooldown().maxProfit(prices));
    }
}
