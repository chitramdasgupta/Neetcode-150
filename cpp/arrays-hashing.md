1. Contains Duplicate

```cpp
class Solution {
public:
	bool containsDuplicate(vector<int> &nums) {
		unordered_set<int> seen;
		for (int &num : nums) {
			if (seen.contains(num)) {
				return true;
			}
			seen.insert(num);
		}

		return false;
	}
};
```

2. Valid Anagram

```cpp
class Solution {
public:
  bool isAnagram(string s, string t) {
    if (s.size() != t.size()) {
      return false;
    }

  	unordered_map<char, int> freq;
  	for (char &c : s) {
  		++freq[c];
  	}

  	for (char &c : t) {
  		if (!freq.contains(c) || freq[c] == 0) {
  			return false;
  		}

  		--freq[c];
  	}

  	return true;
  }
};
```

3. Two Sum

```cpp
class Solution {
public:
  vector<int> twoSum(vector<int> &nums, int target) {
    unordered_map<int, int> indices;
    vector<int> res;
    for (int i = 0; i < nums.size(); ++i) {
    	int toCheck = target - nums[i];
    	if (indices.contains(toCheck)) {
    		res.push_back(i);
    		res.push_back(indices[toCheck]);
    		break;
    	}

    	indices[nums[i]] = i;
    }

    return res;
  }
};
```

4. Group Anagrams

```cpp
class Solution {
public:
  vector<vector<string>> groupAnagrams(vector<string> &strs) {
    unordered_map<string, vector<string>> anagrams;
    for (string str : strs) {
    	string originalStr = str;
    	sort(str.begin(), str.end());
    	anagrams[str].push_back(originalStr);
    }

    vector<vector<string>> res;
    for (auto [key, val] : anagrams) {
    	res.push_back(val);
    }

    return res;
  }
};
```

5. Top K Frequent Elements

```cpp
class Solution {
public:
  vector<int> topKFrequent(vector<int> &nums, int k) {
    unordered_map<int, int> freq;
    for (int num : nums) {
      ++freq[num];
    }

    priority_queue<pair<int, int>, vector<pair<int, int>>,
                   greater<pair<int, int>>> pq;
    for (pair<int, int> item : freq) {
      pq.push({item.second, item.first});

      if (pq.size() > k) {
        pq.pop();
      }
    }

    vector<int> res;
    while (!pq.empty()) {
      pair<int, int> item = pq.top();
      pq.pop();
      res.push_back(item.second);
    }
    return res;
  }
};
```

6. Product of Array Except Self

```cpp
class Solution {
public:
  vector<int> productExceptSelf(vector<int> &nums) {
    vector<int> suffix(nums.size(), 1);
    int suff = 1;
    for (int i = nums.size() - 2; i >= 0; --i) {
    	suff *= nums[i + 1];
    	suffix[i] = suff;
    }

    vector<int> prefix(nums.size(), 1);
    int pref = 1;
    for (int i = 1; i < nums.size(); ++i) {
    	pref *= nums[i - 1];
    	prefix[i] = pref;
    }

    vector<int> res(nums.size(), 1);
    for (int i = 0; i < res.size(); ++i) {
    	res[i] = suffix[i] * prefix[i];
    }

    return res;
  }
};
```

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
