package Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Time - O(n * 4^n)
// Space - O(n * 4^n)
public class LetterCombinationsOfAPhoneNumber {
    List<String> res;

    Map<Integer, String> numberMap;

    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if (digits.equals("")) {
            return res;
        }
        numberMap = new HashMap<>();
        numberMap.put(2, "abc");
        numberMap.put(3, "def");
        numberMap.put(4, "ghi");
        numberMap.put(5, "jkl");
        numberMap.put(6, "mno");
        numberMap.put(7, "pqrs");
        numberMap.put(8, "tuv");
        numberMap.put(9, "wxyz");

        backtrack(digits, 0, new StringBuilder());
        return res;
    }

    void backtrack(String digits, int idx, StringBuilder sb) {
        if (idx >= digits.length()) {
            res.add(sb.toString());
            return;
        }

        int digit = digits.charAt(idx) - '0';
        String values = numberMap.get(digit);
        for (int i = 0; i < values.length(); ++i) {
            sb.append(values.charAt(i));
            backtrack(digits, idx + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
