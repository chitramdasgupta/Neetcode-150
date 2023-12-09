package MathAndGeometry;

// Time - O(log n)
// Space - O(1)
public class HappyNumber {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = nextValue(n);

        while (fast != slow) {
            slow = nextValue(slow);
            fast = nextValue(nextValue(fast));
        }

        return fast == 1;
    }

    int nextValue(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += Math.pow(digit, 2);
            n /= 10;
        }

        return sum;
    }
}
