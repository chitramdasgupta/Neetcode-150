package BitManipulation;

// Time - O(1)
// Space - O(1)
public class Numberof1Bits {
    // This solution works in C++, but will not work in Java because
    // Java has no unsigned integer type, and for integers preserves
    // The MSB sign bit.
//    int hammingWeight(uint32_t n) {
//        int res = 0;
//        while(n > 0) {
//            if(n % 2 == 1) {
//                res += 1;
//            }
//            n >>= 1;
//        }
//
//        return res;
//    }
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1); // This handles the rightmost set bit
            res += 1;
        }

        return res;
    }
}
