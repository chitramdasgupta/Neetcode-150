package Greedy;

import java.util.HashSet;
import java.util.Set;

// Time - O(n)
// Space - O(1)
public class MergeTripletsToFormTargetTriplet {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        // The indices we have found so far of the target values
        Set<Integer> good = new HashSet<>();
        for (int[] triplet : triplets) {
            if (triplet[0] > target[0] || triplet[1] > target[1] || triplet[2] > target[2]) {
                continue;
            }

            for (int i = 0; i < 3; ++i) {
                if (triplet[i] == target[i]) {
                    good.add(i);
                }
            }
        }

        return good.size() == 3;
    }
}
