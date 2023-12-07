package BitManipulation;

// Time - O(1)
// Space - O(1)
public class ReverseBits {
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            res <<= 1; // Makes room for the next LSB from n
            // (n & 1) extracts the LSB from n
            // | applies the extracted LSB from n to res
            res |= (n & 1);
            // Process the next LSB from n in the next iteration
            n >>= 1;
        }
        return res;
    }
}
