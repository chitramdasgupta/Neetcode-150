package SlidingWindow;

// Time - O(n)
// Space - O(1)
public class LongestRepeatingCharReplacement {
    public int characterReplacement(String s, int k) {
        int ans = 0;
        int[] freq = new int[26];
        int left = 0;
        // maxCount is the count of the most frequent char in the window
        int maxCount = 0;
        for (int right = 0; right < s.length(); ++right) {
            int idx = s.charAt(right) - 'A';
            freq[idx] += 1;
            maxCount = Math.max(maxCount, freq[idx]);
            int windowSize = right - left + 1;
            // We are checking if the chars in the windowSize section of the
            // string can be all made to be the same character as the most
            // frequent char. Thus, for things to be fine:
            // maxCount (number of occurrences of the most frequent char) +
            // k (number of replacements) == windowSize
            if (windowSize - maxCount > k) {
                int leftIdx = s.charAt(left) - 'A';
                freq[leftIdx] -= 1;
                left += 1;
                windowSize -= 1;
            }
            ans = Math.max(ans, windowSize);
        }
        return ans;
    }
}
