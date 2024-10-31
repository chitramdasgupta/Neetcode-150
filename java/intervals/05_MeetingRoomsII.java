class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return 0;
        }

        intervals.sort(Comparator.comparingInt(a -> a.start));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(intervals.getFirst().end);

        for (int i = 1; i < intervals.size(); ++i) {
            Interval currInterval = intervals.get(i);

            if (currInterval.start < pq.peek()) {
                pq.add(currInterval.end);
            } else {
                pq.poll();
                pq.add(currInterval.end);
            }

        }

        return pq.size();
    }
}

