# Best Time To Buy And Sell Stock

```java
class Solution {
    public int maxProfit(int[] prices) {
        int buy = Integer.MAX_VALUE;
        int sell = 0;
        for (int price : prices) {
            if (price < buy) {
                buy = price;
                continue;
            }
            sell = Math.max(price - buy, sell);
        }

        return sell;
    }
}
```

# Longest Subtring Without Repeating Characters

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        int left = 0;
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            freq.put(c, freq.getOrDefault(c, 0) + 1);

            while (freq.get(c) > 1) {
                freq.put(s.charAt(left), freq.getOrDefault(s.charAt(left), 0) - 1);
                ++left;
            }

            res = Math.max(i - left + 1, res);
        }

        return res;
    }
}
```

# Longest Repeating Character Replacement

```java

class Solution {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();

        int highestFreq = 0;
        int left = 0;
        int res = 0;

        for (int right = 0; right < s.length(); ++right) {
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            highestFreq = Math.max(freq.get(c), highestFreq);

            int windowLength = right - left + 1;

            if (windowLength - highestFreq <= k) {
                res = Math.max(right - left + 1, res);
            }  else {
                freq.put(s.charAt(left), freq.get(s.charAt(left)) - 1);
                ++left;
            }
        }

        return res;
    }
}
```

# Permutation In String

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        Map<Character, Integer> firstFreq = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            char c = s1.charAt(i);
            firstFreq.put(c, firstFreq.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> secondFreq = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s2.charAt(i);
            secondFreq.put(c, secondFreq.getOrDefault(c, 0) + 1);
        }

        if (firstFreq.equals(secondFreq)) {
            return true;
        }

        for (int i = s1.length(); i < s2.length(); i++) {
            // Add new character
            char c = s2.charAt(i);
            secondFreq.put(c, secondFreq.getOrDefault(c, 0) + 1);

            // Remove leftmost character
            char leftChar = s2.charAt(i - s1.length());
            secondFreq.put(leftChar, secondFreq.getOrDefault(leftChar, 0) - 1);
            if (secondFreq.get(leftChar) == 0) {
                secondFreq.remove(leftChar);
            }

            if (firstFreq.equals(secondFreq)) {
                return true;
            }
        }

        return false;
    }
}

```

