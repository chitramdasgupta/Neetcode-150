7. Valid Sudoku

```cpp
class Solution {
public:
  bool isValidSudoku(vector<vector<char>> &board) {
    const int size = 9;
    bool rows[size][size + 1] = {false};
    bool cols[size][size + 1] = {false};
    bool squares[size][size + 1] = {false};

    for (int r = 0; r < size; ++r) {
      for (int c = 0; c < size; ++c) {
        char cell = board[r][c];
        if (cell == '.') {
          continue;
        }

        int idx = cell - '0';
        int grid = (r / 3) * 3 + (c / 3);

        if (rows[r][idx] || cols[c][idx] || squares[grid][idx]) {
          return false;
        }

        rows[r][idx] = true;
        cols[c][idx] = true;
        squares[grid][idx] = true;
      }
    }

    return true;
  }
};
```

8. Encode and Decode Strings

```cpp
class Solution {
public:

  string encode(vector<string> &strs) {
    string res = "";
    for (string str : strs) {
      res.push_back(str.size() + '#' + str);
    }

    return res;
  }

  vector<string> decode(string s) {
    vector<string> res;
    int i = 0;
    while (i < s.size()) {
      int j = i;
      while (s[j] != '#') {
        ++j;
      }
      // j is now at the '#' after the length

      int len = stoi(i, j - i);
      string str = s.substr(j + 1, len); // the string start 1 after the '#'
      res.push_back(str);
      i = j + 1 + len; // i is now 1 after the curr string
    }

    return res;
  }
};
```

9. Longest Consecutive Sequence

```cpp
class Solution {
public:
  int longestConsecutive(vector<int> &nums) {
    unordered_set<int> unique(nums.begin(), nums.end());
    int res = 0;
    for (int num : unique) {
      if (!unique.contains(num - 1)) {
        int len = 1;
        while (unique.contains(num + len)) {
          ++len;
        }

        res = max(res, len);
      }
    }

    return res;
  }
};
```
