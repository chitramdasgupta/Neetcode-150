package BitManipulation;

// Time - O(log x)
// Space - O(1)
public class ReverseInteger {
    public int reverse(int x) {
        boolean isNegative = x < 0;
        x = Math.abs(x);

        int res = 0;
        while (x > 0) {
            if (Integer.MAX_VALUE / 10 < res) {
                return 0;
            }
            int digit = x % 10; // The last digit
            x /= 10; // Remove the last digit for the next iteration
            res = 10 * res + digit;
        }

        return isNegative ? -res : res;
    }
}
