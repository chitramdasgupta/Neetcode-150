1. Valid Palindrome

```cpp
class Solution {
public:
  bool isPalindrome(string s) {
    int left = 0;
    int right = s.size() - 1;
    while (left < right) {
      if (!isalnum(s[left])) {
        ++left;
        continue;
      }
      if (!isalnum(s[right])) {
        --right;
        continue;
      }

      if (tolower(s[left]) != tolower(s[right])) {
        return false;
      }

      ++left;
      --right;
    }

    return true;
  }
};
```

2. Two Sum II Input Array Is Sorted

```cpp
class Solution {
public:
  vector<int> twoSum(vector<int> &numbers, int target) {
    int left = 0;
    int right = numbers.size() - 1;
    vector<int> res;
    while (left < right) {
      int sum = numbers[left] + numbers[right];
      if (sum > target) {
        --right;
      } else if (sum < target) {
        ++left;
      } else {
        res.push_back(left + 1);
        res.push_back(right + 1);
        break;
      }
    }

    return res;
  }
};
```

3. 3Sum ⭐

```cpp
class Solution {
public:
  vector<vector<int>> threeSum(vector<int> &nums) {
    sort(nums.begin(), nums.end());
    vector<vector<int>> res;
    for (int i = 0; i < nums.size() - 2; ++i) {
      int left = i + 1;
      int right = nums.size() - 1;

      // Skip duplicates for the first element
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      while (left < right) {
        // Skip duplicates for the second element
        if (left > i + 1 && nums[left] == nums[left - 1]) {
          ++left;
          continue;
        }
        // Skip duplicates for the third element
        if (right < nums.size() - 1 && nums[right] == nums[right + 1]) {
          --right;
          continue;
        }

        if (nums[left] + nums[right] == -nums[i]) {
          res.push_back({nums[i], nums[left], nums[right]});
          ++left;
          --right;
        } else if (nums[left] + nums[right] > -nums[i]) {
          --right;
        } else {
          ++left;
        }
      }
    }

    return res;
  }
};
```

4. Container With Most Water

```cpp
class Solution {
public:
  int maxArea(vector<int> &height) {
    int res = 0;
    int left = 0;
    int right = height.size() - 1;
    while (left < right) {
      int curr = (right - left) * min(height[left], height[right]);
      res = max(curr, res);

      if (height[left] <= height[right]) {
        ++left;
      } else {
        --right;
      }
    }

    return res;
  }
};
```
