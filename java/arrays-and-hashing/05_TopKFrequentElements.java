class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // min heap
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int num : freq.keySet()) {
            if (pq.size() < k) {
                pq.add(new int[] {freq.get(num), num});
                continue;
            }

            if (freq.get(num) > pq.peek()[0]) {
                pq.poll();
                pq.add(new int[] {freq.get(num), num});
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; ++i) {
            res[i] = pq.poll()[1];
        }

        return res;
    }
}
