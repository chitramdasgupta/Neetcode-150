class Solution {
    private int maxStart = 0;
    private int maxLen = 1;

    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); ++i) {
            checkPalindrome(s, i, i); // odd length palindrome
            checkPalindrome(s, i, i + 1); // even length palindrome
        }

        return s.substring(maxStart, maxStart + maxLen);
    }

    private void checkPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && right >= left &&
               s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }

        // Account for the fact that we have gone one step too far
        int len = (right - 1) - (left + 1) + 1;

        if (len > maxLen) {
            maxLen = len;
            maxStart = left + 1;
        }
    }
}
