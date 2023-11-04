package ArraysandHashing;

import java.util.HashMap;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> seen = new HashMap<>();
        int[] answer = new int[2];
        for (int i=0; i< nums.length; ++i) {
            int reqNum = target - nums[i];
            if (seen.containsKey(reqNum)) {
                answer[0] = seen.get(reqNum);
                answer[1] = i;
            } else {
                seen.put(nums[i], i);
            }
        }

        return answer;
    }
}
