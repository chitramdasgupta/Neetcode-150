package Intervals;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// Time - O(n log n)
// Space - O(n)
public class MeetingRoomsII {
    public int minMeetingRooms(List<Interval> intervals) {
        // Sort the intervals based on their starting times
        intervals.sort(Comparator.comparingInt(a -> a.start));

        // Min heap to keep of the end times of the meetings currently taking place
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(intervals.get(0).end);

        for (int i = 1; i < intervals.size(); ++i) {
            // If the current interval start time is greater than the earliest interval
            // taking place, then the current meeting can take place in a room that
            // becomes free (so we pop the earliest end time meeting)
            if (intervals.get(i).start >= pq.peek()) {
                pq.poll();
            }
            // and add the current meeting
            pq.add(intervals.get(i).end);
        }

        // Return the number of overlapping meetings taking place
        return pq.size();
    }

    private class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
