package Intervals;

import java.util.ArrayList;
import java.util.List;

// Time - O(n)
// Space - O(n)
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();

        for (int[] interval : intervals) {
            // If there is no newInterval to add, or the end time of the
            // current interval is smaller than the start time of the new interval
            if (newInterval == null || interval[1] < newInterval[0]) {
                res.add(interval);
            } else if (interval[0] > newInterval[1]) {
                // If the end of the current interval is greater than or equal to the
                // start of the new interval and the start of the current is greater
                // than the end of the new interval
                res.add(newInterval);
                res.add(interval);
                newInterval = null;
            } else {
                // Else the current interval and the new interval overlap
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
                // We do not add the merged interval right away as it might
                // overlap with subsequent intervals as well
            }
        }

        if (newInterval != null) {
            res.add(newInterval);
        }

        return res.toArray(new int[res.size()][]);
    }
}
