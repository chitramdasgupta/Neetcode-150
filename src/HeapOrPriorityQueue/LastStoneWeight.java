package HeapOrPriorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> stones_pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            stones_pq.offer(stone);
        }

        while (stones_pq.size() >= 2) {
            int x = stones_pq.poll();
            int y = stones_pq.poll();
            if (x != y) {
                y = Math.abs(y - x);
                stones_pq.offer(y);
            }
        }

        return stones_pq.size() == 1 ? stones_pq.peek() : 0;
    }
}
