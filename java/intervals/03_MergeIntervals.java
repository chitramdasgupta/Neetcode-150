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

