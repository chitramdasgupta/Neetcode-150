package OneDimensionalDynamicProgramming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Time - O(n ^ 3) Because of outer loop * inner loop * substring
// Space - O(n)
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        // This marks the beginning of the string
        dp[0] = true;

        for (int i = 1; i <= s.length(); ++i) {
            for (int j = i - 1; j >= 0; --j) {
                if (dp[j]) {
                    String word = s.substring(j, i);
                    if (words.contains(word)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        return dp[s.length()];
    }
}
