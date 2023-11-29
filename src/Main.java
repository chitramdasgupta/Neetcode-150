import OneDimensionalDynamicProgramming.CoinChange;

public class Main {
    public static void main(String[] args) {
        int[] coins = { 1, 3, 4, 5 };
        int amount = 7;
        System.out.println(new CoinChange().coinChange(coins, amount));
    }
}
