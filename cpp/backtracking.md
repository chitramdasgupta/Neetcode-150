1. Subsets

```cpp
class Solution {
public:
  vector<vector<int>> subsets(vector<int> &nums) {
    vector<vector<int>> res = {};
    vector<int> curr = {};
    backtrack(nums, res, curr, 0);
    return res;
  }

private:
  void backtrack(const vector<int> &nums, vector<vector<int>> &res,
                 vector<int> &curr, int index) {
    res.push_back(curr);

    for (int i = index; i < nums.size(); ++i) {
      curr.push_back(nums[i]);
      backtrack(nums, res, curr, i + 1);
      curr.pop_back();
    }
  }
};
```

2. Combination Sum

```cpp
class Solution {
public:
  vector<vector<int>> combinationSum(vector<int> &candidates, int target) {
    vector<vector<int>> res = {};
    vector<int> curr = {};
    backtrack(candidates, res, curr, target, 0);
    return res;
  }

private:
  void backtrack(vector<int> &candidates, vector<vector<int>> &res,
                 vector<int> &curr, int remain, int start) {
    if (remain < 0) {
      return;
    }

    if (remain == 0) {
      res.push_back(curr);
      return;
    }

    for (int i = start; i < candidates.size(); ++i) {
      curr.push_back(candidates[i]);
      backtrack(candidates, res, curr, remain - candidates[i], i);
      curr.pop_back();
    }
  }
};
```

3. Permutations

```cpp
class Solution {
public:
  vector<vector<int>> permute(vector<int> &nums) {
    vector<vector<int>> res = {};
    vector<int> curr = {};
    backtrack(nums, res, 0);
    return res;
  }

private:
  void backtrack(vector<int> &nums, vector<vector<int>> &res, int start) {
    if (start >= nums.size()) {
      res.push_back(nums);
      return;
    }

    for (int i = start; i < nums.size(); ++i) {
      swap(nums[start], nums[i]);
      backtrack(nums, res, start + 1);
      swap(nums[start], nums[i]);
    }
  }
};
```

4. Subsets II

```cpp
class Solution {
public:
  vector<vector<int>> subsetsWithDup(vector<int> &nums) {
    sort(nums.begin(), nums.end());

    vector<vector<int>> res = {};
    vector<int> curr = {};
    backtrack(nums, res, curr, 0);

    return res;
  }

private:
  void backtrack(vector<int> &nums, vector<vector<int>> &res, vector<int> &curr,
                 int index) {
    res.push_back(curr);

    for (int i = index; i < nums.size(); ++i) {
      if (i > index && nums[i] == nums[i - 1]) {
        continue;
      }

      curr.push_back(nums[i]);
      backtrack(nums, res, curr, i + 1);
      curr.pop_back();
    }
  }
};
```

5. Combination Sum II

```cpp
class Solution {
public:
  vector<vector<int>> combinationSum2(vector<int> &candidates, int target) {
    sort(candidates.begin(), candidates.end());

    vector<vector<int>> res = {};
    vector<int> curr = {};
    backtrack(candidates, res, curr, target, 0);

    return res;
  }

private:
  void backtrack(vector<int> &candidates, vector<vector<int>> &res,
                 vector<int> &curr, int remain, int start) {
    if (remain < 0) {
      return;
    }

    if (remain == 0) {
      res.push_back(curr);
      return;
    }

    for (int i = start; i < candidates.size(); ++i) {
      if (i > start && candidates[i] == candidates[i - 1]) {
        continue;
      }

      curr.push_back(candidates[i]);
      backtrack(candidates, res, curr, remain - candidates[i], i + 1);
      curr.pop_back();
    }
  }
};
```

6. Word Search

```cpp
class Solution {
public:
  bool exist(vector<vector<char>> &board, string word) {
    for (int i = 0; i < board.size(); ++i) {
      for (int j = 0; j < board[0].size(); ++j) {
        if (dfs(board, word, i, j, 0)) {
          return true;
        }
      }
    }

    return false;
  }

private:
  bool dfs(vector<vector<char>> &board, string word, int row, int col,
           int index) {
    if (row < 0 || col < 0 || row >= board.size() || col >= board[0].size() ||
        index >= word.size() || board[row][col] != word[index]) {
      return false;
    }

    if (index == word.size() - 1) {
      return true;
    }

    board[row][col] = '#';

    if (dfs(board, word, row + 1, col, index + 1) ||
        dfs(board, word, row, col + 1, index + 1) ||
        dfs(board, word, row - 1, col, index + 1) ||
        dfs(board, word, row, col - 1, index + 1)) {
      return true;
    }

    board[row][col] = word[index];
    return false;
  }
};
```

7. Palindrome Partitioning

```cpp
class Solution {
public:
  vector<vector<string>> partition(string s) {
    vector<vector<string>> res = {};
    vector<string> curr = {};
    backtrack(s, res, curr, 0);
    return res;
  }

private:
  void backtrack(string &s, vector<vector<string>> &res, vector<string> &curr,
                 int index) {
    if (index >= s.size()) {
      res.push_back(curr);
      return;
    }

    for (int i = index; i < s.size(); ++i) {
      string temp = s.substr(index, i - index + 1);
      if (is_palindrome(temp)) {
        curr.push_back(temp);
        backtrack(s, res, curr, i + 1);
        curr.pop_back();
      }
    }
  }

  bool is_palindrome(string text) {
    int left = 0;
    int right = text.size() - 1;

    while (left < right) {
      if (text[left] != text[right]) {
        return false;
      }

      ++left;
      --right;
    }

    return true;
  }
};
```

8. Letter Combinations of a Phone Number

```cpp
class Solution {
public:
  vector<string> letterCombinations(string digits) {
    if (digits.empty()) {
      return {};
    }

    unordered_map<char, string> digits_map = {
        {'2', "abc"}, {'3', "def"},  {'4', "ghi"}, {'5', "jkl"},
        {'6', "mno"}, {'7', "pqrs"}, {'8', "tuv"}, {'9', "wxyz"}};

    vector<string> res = {};
    string curr = "";
    backtrack(digits, digits_map, res, curr, 0);
    return res;
  }

private:
  void backtrack(string &digits, unordered_map<char, string> &digits_map,
                 vector<string> &res, string &curr, int index) {
    if (index >= digits.size()) {
      res.push_back(curr);
      return;
    }

    string temp = digits_map[digits[index]];
    for (int i = 0; i < temp.size(); ++i) {
      curr.push_back(temp[i]);
      backtrack(digits, digits_map, res, curr, index + 1);
      curr.pop_back();
    }
  }
};
```
