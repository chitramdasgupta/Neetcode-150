package OneDimensionalDynamicProgramming;

// Time - O(n^2)
// Space - O(1)
public class LongestPalindromicSubstring {
    int maxLen = 0;
    int maxStart = 0;

    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); ++i) {
            // Odd length palindromes
            checkPalindrome(s, i, i);
            // Even length palindromes
            checkPalindrome(s, i, i + 1);
        }
        return s.substring(maxStart, maxStart + maxLen);
    }

    void checkPalindrome(String s, int start, int end) {
        int currLen = 0;
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            if (start == end) {
                // This is only for the first iteration for odd palindromes of len 1
                currLen += 1;
            } else {
                currLen += 2;
            }
            if (currLen > maxLen) {
                maxLen = currLen;
                maxStart = start;
            }
            start -= 1;
            end += 1;
        }
    }
}
