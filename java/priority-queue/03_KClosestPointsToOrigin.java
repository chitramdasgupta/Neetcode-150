class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int[] point : points) {
            int distance = Math.abs(point[0] * point[0] + point[1] * point[1]);

            if (pq.size() < k) {
                pq.offer(new int[] {distance, point[0], point[1]});
                continue;
            }

            if (distance < pq.peek()[0]) {
                pq.poll();
                pq.add(new int[] {distance, point[0], point[1]});
            }
        }

        int[][] res = new int[k][2];
        int index = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            res[index++] = new int[] {curr[1], curr[2]};
        }

        return res;
    }
}
