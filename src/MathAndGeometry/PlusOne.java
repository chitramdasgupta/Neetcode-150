package MathAndGeometry;

import java.util.ArrayList;
import java.util.List;

// Time - O(n)
// Space - O(1) // Except the output
public class PlusOne {
    public int[] plusOne(int[] digits) {
        List<Integer> res = new ArrayList<>();
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; --i) {
            // We need to add 1 to the last digit
            if (i == digits.length - 1) {
                res.add((digits[i] + 1) % 10);
                carry = (digits[i] + 1) / 10;
            } else {
                // For the rest we keep the digit as it is or add carry to it
                res.add((digits[i] + carry) % 10);
                carry = (digits[i] + carry) / 10;
            }
        }

        // Only add carry if it is still not 0 (in say [9, 9, 9])
        if (carry == 1) {
            res.add(carry);
        }

        int[] output = new int[res.size()];
        for (int i = 0; i < output.length; ++i) {
            output[i] = res.get(res.size() - 1 - i);
        }
        return output;
    }
}
