package MathAndGeometry;

// Divide and Conquer
// Time - O(log n)
// Space - O(log n)
public class PowXToTheN {
    public double myPow(double x, int n) {
        double res = helper(x, Math.abs(n));
        return n >= 0 ? res : 1 / res;
    }

    double helper(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;

        double res = helper(x, n / 2);
        return n % 2 == 0 ? res * res : x * res * res;
    }
}
