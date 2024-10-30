class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task :tasks) {
            freq[task - 'A'] += 1;
        }

        PriorityQueue<int[]> processing = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Arrays.sort(freq);
        int[] sortedFreq = IntStream.range(0, freq.length)
                                .map(i -> freq[freq.length - 1 - i])
                                .toArray();

        for (int i = 0; i < sortedFreq.length; ++i) {
            if (sortedFreq[i] > 0) {
                processing.offer(new int[] {i + 1, sortedFreq[i]});
            }
        }

        int time = 0;
        while (!processing.isEmpty()) {
            ++time;
            if (time < processing.peek()[0]) {
                continue;
            }

            int[] currProcess = processing.poll();
            currProcess[1] -= 1;

            if (currProcess[1] > 0) {
                processing.offer(new int[] {currProcess[0] + n + 1, currProcess[1]});
            }
        }

        return time;
    }
}
