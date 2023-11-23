package OneDimensionalDynamicProgramming;

// Time - O(n^2)
// Space - O(1)
public class PalindromicSubstrings {
    int res = 0;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); ++i) {
            // Odd palindromes
            checkPalindrome(s, i, i);
            // Even palindromes
            checkPalindrome(s, i, i + 1);
        }
        return res;
    }

    void checkPalindrome(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            res += 1;
            start -= 1;
            end += 1;
        }
    }
}
