class Solution {
    private static final double epsilon = 0.0001;

    public double maxAreaCake(int[] radii, int guests) {
        double[] areas = Arrays.stream(radii).mapToDouble(r -> Math.PI * r * r).toArray();

        double left = 0;
        double right = Arrays.stream(areas).max().getAsDouble();

        double result = 0;
        while (left <= right) {
            double mid = left + (right - left) / 2;

            if (canServeAllGuests(areas, guests, mid)) {
                result = mid;
                left = mid + epsilon;
            } else {
                right = mid - epsilon;
            }
        }

        return result;
    }

    private boolean canServeAllGuests(double[] areas,int target, double value) {
        int res = 0;
        for (double area : areas) {
            int curr = (int) (area / value);
            res += curr;

            if (res >= target) {
                return true;
            }
        }

        return res == target;
    }
}
