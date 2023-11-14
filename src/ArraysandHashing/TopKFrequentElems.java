package ArraysandHashing;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

// Time - O(nlogn)
// Space - O(n)
public class TopKFrequentElems {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for(int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return freq.get(b) - freq.get(a);
            }
        });
        pq.addAll(freq.keySet());

        int[] ans = new int[k];
        for(int i=0; i<k; ++i) {
            ans[i] = pq.poll();
        }
        return ans;
    }
}
