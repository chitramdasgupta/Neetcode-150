package BitManipulation;

// Time - O(n)
// Space - O(1)
public class SumofTwoIntegers {
    public int getSum(int a, int b) {
        // In the first iteration a will become the partial sum (without the carry)
        // In the subsequent iterations, the carry will be incorporated to a until
        // carry is 0.
        while (b != 0) {
            int carry = (a & b) << 1;
            a ^= b; // sum without the carry
            b = carry;
        }
        return a;
    }
}
