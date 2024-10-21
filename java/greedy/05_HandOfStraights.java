class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
      if (hand.length % groupSize != 0) {
        return false;
      }

      PriorityQueue<Integer> minHeap = new PriorityQueue<>();
      Map<Integer, Integer> freq = new HashMap<>();
      for (int num : hand) {
        if(!freq.containsKey(num)) {
          freq.put(num, 0);
        }

        freq.put(num, freq.get(num) + 1);
      }

      for (int num : freq.keySet()) {
        minHeap.add(num);
      }

      while (!minHeap.isEmpty()) {
        int first = minHeap.peek();

        for (int i = first; i < first + groupSize; ++i) {
          if (!freq.containsKey(i)) {
            return false;
          }

          freq.put(i, freq.get(i) - 1);
          if (freq.get(i) == 0) {
            if (i != minHeap.peek()) {
              return false;
            }

            minHeap.poll();
          }
        }
      }

      return true;
    }
}
