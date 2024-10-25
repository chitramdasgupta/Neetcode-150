class Solution {
    private int res;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); ++i) {
            checkPalindrome(s, i, i);
            checkPalindrome(s, i, i + 1);
        }

        return res;
    }

    private void checkPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && right >= left &&
              s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;

            ++res;
        }
    }
}
