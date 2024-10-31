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

