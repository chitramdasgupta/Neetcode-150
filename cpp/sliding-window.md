1. Best Time to Buy and Sell Stock

```cpp
class Solution {
public:
  int maxProfit(vector<int> &prices) {
    int maxProfit = 0;
    int minBuyPrice = prices[0];
    for (int &price : prices) {
      minBuyPrice = min(minBuyPrice, price);
      maxProfit = max(maxProfit, price - minBuyPrice);
    }

    return maxProfit;
  }
};
```

2. Longest Substring Without Repeating Characters

```cpp
class Solution {
public:
  int lengthOfLongestSubstring(string s) {
    int res = 0;
    int left = 0;
    unordered_set<char> seen;
    for (int right = 0; right < s.size(); ++right) {
      while (seen.contains(s[right])) {
        seen.erase(s[left]);
        ++left;
      }
      seen.insert(s[right]);
      res = max(res, right - left + 1);
    }

    return res;
  }
};
```

3. Longest Repeating Character Replacement

```cpp
class Solution {
public:
  int characterReplacement(string s, int k) {
    int freq[26] = {0};
    int left = 0;
    int res = 0;
    // maxCount is the frequency of the most frequent char in the window
    int maxCount = 0;
    for (int right = 0; right < s.size(); ++right) {
      ++freq[s[right] - 'A'];
      maxCount = max(maxCount, freq[s[right] - 'A']);
      int windowSize = right - left + 1;

      // If the windowSize is larger than the most frequent element and the
      // no of possible replacements, then we have to shrink the window
      if (windowSize > maxCount + k) {
        --freq[s[left] - 'A'];
        ++left;
        --windowSize;
      }

      res = max(res, windowSize);
    }

    return res;
  }
};
```

4. Permutation in String

```cpp
class Solution {
public:
  bool checkInclusion(string s1, string s2) {
    if (s1.size() > s2.size()) {
      return false;
    }

    array<int, 26> freq = {0};
    for (char &c : s1) {
      ++freq[c - 'a'];
    }

    array<int, 26> secondFreq = {0};
    for (int i = 0; i < s2.size(); ++i) {
      ++secondFreq[s2[i] - 'a'];

      if (i >= s1.size()) {
        --secondFreq[s2[i - s1.size()] - 'a'];
      }

      if (freq == secondFreq) {
        return true;
      }
    }

    return false;
  }
};
```
