package MathAndGeometry;

import java.util.Arrays;

// Time - O(m * n)
// Space - O(m + n)
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();

        char[] result = new char[m + n];
        Arrays.fill(result, '0');

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // i + j + 1 is the current position; i + j is the next one
                int sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + (result[i + j + 1] - '0');
                result[i + j + 1] = (char) (sum % 10 + '0');
                result[i + j] += sum / 10; // This can be greater than '9'
            }
        }

        // This takes care of the leading zeros
        for (int i = 0; i < m + n; i++) {
            if (result[i] != '0') {
                return new String(result, i, m + n - i);
            }
        }
        return "0";
    }
}
