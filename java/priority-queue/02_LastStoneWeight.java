class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> (int) a).reversed());
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            int y = pq.poll();
            int x = pq.poll();

            if (y > x) {
                pq.offer(y - x);
            }
        }

        return pq.isEmpty() ? 0 : pq.peek();
    }
}
