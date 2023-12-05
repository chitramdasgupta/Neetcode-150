package AdvancedGraphs;

import java.util.Arrays;

// Bellman-Ford algorithm
// Time - O(V * E)
// Space - O(V)
public class CheapestFlightsWithinKStops {
    // NOTE: k is the max number of nodes between the source and the destination
    // excluding the source and the destination.
    // Flights : [[source, dest, price], ...]
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0; // the cost to go from src to itself is 0

        // We loop till (k + 1) iterations
        for (int i = 0; i < k + 1; ++i) {
            int[] tempPrices = Arrays.copyOf(prices, prices.length);

            for (int[] flight : flights) {
                int source = flight[0];
                int destination = flight[1];
                int price = flight[2];

                // This means that we have not visited source node yet
                if (prices[source] == Integer.MAX_VALUE) {
                    continue;
                }
                // Set the destination price to the minimum of
                // (going from the current source and taking the flight with the price)
                // or (the previous price to reach the destination).
                tempPrices[destination] = Math.min(tempPrices[destination], prices[source] + price);
            }
            prices = tempPrices;
        }

        // If the dst is still MAX_VALUE, it means we cannot reach the dst from the src
        return prices[dst] != Integer.MAX_VALUE ? prices[dst] : -1;
    }
}
