class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        for (int i = 0; i < k + 1; ++i) {
            int[] tempPrices = Arrays.copyOf(prices, prices.length);

            for (int[] flight : flights) {
                int source = flight[0];
                int destination = flight[1];
                int price = flight[2];

                if (prices[source] == Integer.MAX_VALUE) {
                    continue;
                }

                tempPrices[destination] =
                    Math.min(prices[source] + price, tempPrices[destination]);
            }

            prices = tempPrices;
        }

        return prices[dst] != Integer.MAX_VALUE ? prices[dst] : -1;
    }
}
