package HeapOrPriorityQueue;

import java.util.PriorityQueue;

public class KthLargestInStream {
    int target;
    private PriorityQueue<Integer> pq;

    public void KthLargest(int k, int[] nums) {
        target = k;
        pq = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (pq.size() < target) {
            pq.offer(val);
        } else if (val > pq.peek()) {
            pq.poll();
            pq.offer(val);
        }
        return pq.peek();
    }
}
