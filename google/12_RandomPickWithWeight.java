class Solution {
    private Random random;
    private int[] arr;
    private double[] prob;

    public Solution(int[] w) {
        random = new Random();
        this.arr = w;
        this.prob = new double[arr.length];

        double sum = Arrays.stream(w).sum();
        prob[0] = w[0] / sum;
        for (int i = 1; i < w.length; ++i) {
            prob[i] = prob[i - 1] + (w[i] / sum);
        }
    }
    
    public int pickIndex() {
        double target = random.nextDouble();

        int left = 0;
        int right = prob.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if(prob[mid] < target) {
                // we are not in the valid range, so move one index to the right
                left = mid + 1;
            } else {
                // we are in a valid range
                right = mid;
            }
        }

        return right; // or left because left = right
    }
}
