package Intervals;

import java.util.Arrays;
import java.util.Comparator;

// Time - (n log n)
// Space - O(n)
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int res = 0;
        for (int i = 0; i < intervals.length - 1; ++i) {
            // If there is an overlap with the current interval and the next one
            if (intervals[i][1] > intervals[i + 1][0]) {
                // If the current interval end time is lesser than that of the
                // next one, we remove the next one (as a lesser end time has a
                // higher chance of a non-overlap with the subsequent intervals)
                if (intervals[i][1] < intervals[i + 1][1]) {
                    // By 'removing', we mean setting the next interval to be equal
                    // to the current one
                    intervals[i + 1] = intervals[i];
                }
                res += 1;
            }
        }

        return res;
    }
}
