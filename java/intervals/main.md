# Meeting Rooms

```java
class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(a -> s.start));

        for (int i = 1; i < intervals.size(); ++i) {
            Interval currInterval = intervals.get(i);
            Interval lastInterval = intervals.get(i - 1);

            if (currInterval.start < lastInterval.end) {
                return false;
            }
        }

        return true;
    }
}

```

# Insert Intervals

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();

        for (int[] currInterval : intervals) {
            if (newInterval == null || currInterval[1] < newInterval[0]) {
                res.add(currInterval);
            } else if (currInterval[0] > newInterval[1]) {
                res.add(newInterval);
                newInterval = null;

                res.add(currInterval);
            } else {
                newInterval[0] = Math.min(currInterval[0], newInterval[0]);
                newInterval[1] = Math.max(currInterval[1], newInterval[1]);
            }
        }

        if (newInterval != null) {
            res.add(newInterval);
        }


        return res.toArray(new int[res.size()][]);
    }
}

```

# Merge Intervals

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; ++i) {
            int[] currInterval = intervals[i];
            int [] lastInterval = res.getLast();

            if (currInterval[0] <= lastInterval[1]) {
                lastInterval[1] = Math.max(lastInterval[1], currInterval[1]);
            } else {
                res.add(currInterval);
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}

```

# Non Over Lapping Intervals

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int res = 0;
        int prevEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; ++i) {
            int[] currInterval = intervals[i];

            if (currInterval[0] < prevEnd) {
                prevEnd = Math.min(prevEnd, currInterval[1]);
                ++res;
            } else {
                prevEnd = currInterval[1];
            }
        }

        return res;
    }
}

```

# Meeting Rooms I I

```java
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

```
