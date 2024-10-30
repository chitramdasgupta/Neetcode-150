class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            if (pq.size() < k) {
                pq.offer(num);
                continue;
            }

            if (num > pq.peek()) {
                pq.poll();
                pq.add(num);
            }
        }

        return pq.peek();
    }
}
