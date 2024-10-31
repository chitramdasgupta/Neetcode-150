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
