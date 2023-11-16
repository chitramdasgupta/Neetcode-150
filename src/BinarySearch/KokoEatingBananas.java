package BinarySearch;

// Time - O(n log m) where m is the max element in the array
// Space - O(1)
public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int kMin = 1;
        int kMax = 0;
        for (int pile : piles) {
            kMax = Math.max(kMax, pile);
        }

        while (kMin < kMax) {
            int k = (kMin + kMax) / 2;
            int hours = 0;
            for(int pile : piles) {
                hours += Math.ceil((double) pile / k);
                if (hours > h) {
                    break;
                }
            }

            if (hours > h) {
                kMin = k + 1;
            } else if (hours <= h) {
                kMax = k;
            }
        }

        return kMin;
    }
}
