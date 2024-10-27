class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // An empty string is trivially present in the dictionary
        for (int i = 0; i < s.length(); ++i) {
            for (int j = i; j >= 0; --j) {
                String temp = s.substring(j, i + 1);
                // Check if the substring is present in the subset
                // And also check if the previous part of the string is also breakable
                if (wordSet.contains(temp) && dp[j]) {
                    dp[i + 1] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}
