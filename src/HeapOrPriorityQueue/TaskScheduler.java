package HeapOrPriorityQueue;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// Time - O(n)
// Space - O(n)
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }
        // Will hold the frequency of each task and poll the most frequent one
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        // The remaining freq of each task along with the time it will again be available
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();

        int[] arr = new int[26];
        for (char c : tasks) arr[c - 'A']++;
        for (int val : arr) if (val > 0) pq.add(val);

        int time = 0;
        while ((!pq.isEmpty() || !q.isEmpty())) {
            time++;
            if (!pq.isEmpty()) {
                int val = pq.poll();
                val--;
                if (val > 0) q.add(new Pair(val, time + n));
            }

            if (!q.isEmpty() && q.peek().getValue() == time) pq.add(
                    q.poll().getKey()
            );
        }
        return time;
    }
}

// We can directly use Pair in leetcode
// But in my local system, it requires javafx
class Pair<I extends Number, I1 extends Number> {
    private I key;
    private I val;

    Pair(I key, I val) {
        this.key = key;
        this.val = val;
    }

    public I getKey() {
        return key;
    }

    public I getValue() {
        return val;
    }
}
