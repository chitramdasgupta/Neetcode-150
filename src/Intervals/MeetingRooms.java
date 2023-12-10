package Intervals;

import java.util.Comparator;
import java.util.List;

// Time - O(n log n)
// Space - O(1)
public class MeetingRooms {
    public boolean canAttendMeetings(List<Interval> intervals) {
        // Sort the intervals based on their starting times
        intervals.sort(Comparator.comparingInt(a -> a.start));
        for (int i = 1; i < intervals.size(); ++i) {
            if (intervals.get(i).start < intervals.get(i - 1).end) {
                return false;
            }
        }
        return true;
    }

    private class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
