package Greedy;

import java.util.Arrays;

// Time - O(n)
// Space - O(1)
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // If we have less gas than what it takes to go round the route it is
        // impossible to do so
        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) {
            return -1;
        }
        // From here on, we are guaranteed to find a unique solution
        int startPos = 0;
        int total = 0;
        for (int i = 0; i < gas.length; ++i) {
            total += gas[i] - cost[i];
            // If total gets less than 0 it means that we cannot start at this position
            // because if we do we won't be able to make it to the next position as
            // the cost of doing so will be more than the gas that is available at the pos
            if (total < 0) {
                // We neutralize the total
                total = 0;
                // We can start from the next position (the first position after the pos
                // where the total is negative)
                // The reason this holds true is that this is the first position where we
                // have enough gas to travel the entire the route
                startPos = i + 1;
            }
        }

        return startPos;
    }
}
