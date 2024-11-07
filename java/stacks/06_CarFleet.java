class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int len = position.length;
        double[][] cars = new double[len][2];
        for (int i = 0; i < len; ++i) {
            cars[i][0] = position[i];
            cars[i][1] = (target - position[i]) / (double) speed[i];
        }

        Arrays.sort(cars, Comparator.comparingDouble(a -> a[0]));
        double prevTime = 0;
        int res = 0;

        for (int i = len - 1; i >= 0; --i) {
            double currentTime = cars[i][1];

            if (currentTime > prevTime) {
                ++res;
                prevTime = currentTime;
            }
        }

        return res;
    }
}
