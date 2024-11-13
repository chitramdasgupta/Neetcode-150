class Solution {
    public int longestPalindrome(String[] words) {
        // Each element represents the frequency of "ab" (string represented by indices)
        int[][] freq = new int[26][26];
        int res = 0;
        for (String word : words) {
            int a = word.charAt(0) - 'a';
            int b = word.charAt(1) - 'a';

            if(freq[b][a] > 0) {
                res += 4;
                --freq[b][a];
            } else {
                ++freq[a][b];
            }
        }

        // We can add 1 self-palindrome to the center
        for (int i = 0; i < 26; i++) {
            if (freq[i][i] > 0) {
                res += 2;
                break;
            }
        }

        return res;
    }
}
