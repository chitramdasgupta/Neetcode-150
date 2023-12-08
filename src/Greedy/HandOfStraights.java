package Greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// Time - O(n log n)
// Space - O(n)
public class HandOfStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            // Then it means it is not possible to evenly divide the array
            // into groups of groupSize
            return false;
        }

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : hand) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        // We put the unique numbers in the heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : freq.keySet()) {
            minHeap.offer(num);
        }

        while (minHeap.size() > 0) {
            // The first is the minimum value
            int first = minHeap.peek();
            // We try to iterate over the numbers that will make the sequence
            for (int i = first; i < first + groupSize; ++i) {
                // If the number required to make the sequence is not present
                if (!freq.containsKey(i)) {
                    return false;
                }

                // We reduce the frequency by 1 because we used the number in the
                // current sequence
                freq.put(i, freq.get(i) - 1);
                // If we have exhausted the supply of the number
                if (freq.get(i) == 0) {
                    // If we have exhausted the supply of a number larger than the
                    // smallest number, it means that we have numbers smaller than
                    // the current one remaining. Hence, we will not be able to
                    // complete a subsequent group.
                    if (i != minHeap.peek()) {
                        return false;
                    }
                    // We can safely remove the number from the heap
                    minHeap.poll();
                }
            }
        }

        return true;
    }
}
