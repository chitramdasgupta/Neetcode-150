package Backtracking;

import java.util.ArrayList;
import java.util.List;

// Time - O(n * 2^n)
// Space - O(n)
public class PalindromePartitioning {
    List<List<String>> res;

    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>());
        return res;
    }

    void backtrack(String s, int idx, List<String> part) {
        if (idx >= s.length()) {
            res.add(new ArrayList<>(part));
            return;
        }

        for (int i = idx; i < s.length(); ++i) {
            if (isPalindrome(s, idx, i)) {
                part.add(s.substring(idx, i + 1));
                backtrack(s, i + 1, part);
                part.remove(part.size() - 1);
            }
        }
    }

    Boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left += 1;
            right -= 1;
        }

        return true;
    }
}
