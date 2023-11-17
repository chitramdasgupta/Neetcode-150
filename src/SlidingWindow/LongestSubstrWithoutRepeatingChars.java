package SlidingWindow;

import java.util.HashSet;
import java.util.Set;

// Time - O(n)
// Space - O(n)
public class LongestSubstrWithoutRepeatingChars {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> seen = new HashSet<>();
        int ans = 0;
        int left = 0;

        for (int right=0; right<s.length(); ++right) {
            while (seen.contains(s.charAt(right))) {
                seen.remove(s.charAt(left));
                left += 1;
            }
            seen.add(s.charAt(right));
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
