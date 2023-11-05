import SlidingWindow.BestTimeBuySellStock;

public class Main {
    public static void main(String[] args) {
        int[] prices = {7,6,4,3,1};
        System.out.println(new BestTimeBuySellStock().maxProfit(prices));
    }
}