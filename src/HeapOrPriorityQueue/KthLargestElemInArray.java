package HeapOrPriorityQueue;

import java.util.PriorityQueue;

// Time - O(n log k)
// Space - O(k)
public class KthLargestElemInArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                pq.remove();
            }
        }
        return pq.peek();
    }
}
