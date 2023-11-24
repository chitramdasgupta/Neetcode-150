package Stack;

import java.util.Arrays;
import java.util.Comparator;

// Time - O(n log n)
// Space - O(n)
public class CarFleet {
    public int carFleet(int target, int[] position, int[] speed) {
        double[][] combined = new double[position.length][2];
        for (int i = 0; i < position.length; ++i) {
            combined[i][0] = position[i];
            combined[i][1] = (double) (target - position[i]) / speed[i];
        }
        // Sorted by the ascending order of position
        Arrays.sort(combined, Comparator.comparingDouble(x -> x[0]));
        int count = 0;
        double maxTime = 0;
        for (int i = combined.length - 1; i >= 0; --i) {
            // Time this car will take to reach the target
            double currentTime = combined[i][1];

            // If the time taken by the current car is greater than the cars
            // ahead of it then it will become its own fleet.
            // Also note that if equal, the current car will become a part of
            // the fleet at ahead
            if (currentTime > maxTime) {
                maxTime = currentTime;
                count += 1;
            }
        }

        return count;
    }
}
