package Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Time - O(n log n)
// Space - O(n)
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for (int i = 1; i < intervals.length; ++i) {
            int[] currentInterval = intervals[i];
            int[] lastInterval = res.get(res.size() - 1);

            if (currentInterval[0] <= lastInterval[1]) {
                // We then need to merge the current interval with the previous one
                lastInterval[1] = Math.max(lastInterval[1], currentInterval[1]);
            } else {
                // The current interval does not overlap with the previous one.
                // Hence, we can easily add the current interval to the result
                res.add(currentInterval);
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}
