1. Binary Search

```cpp
class Solution {
public:
  int search(vector<int> &nums, int target) {
    int res = -1;
    int left = 0;
    int right = nums.size() - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] > target) {
        right = mid - 1;
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        res = mid;
        break;
      }
    }

    return res;
  }
};
```

2. Search a 2D Matrix

```cpp
class Solution {
public:
  bool searchMatrix(vector<vector<int>> &matrix, int target) {
    int numRows = matrix.size();
    int numCols = matrix[0].size();

    // We can consider the entire 2D matrix to be one sorted list
    int left = 0;
    int right = numRows * numCols - 1;

    bool res = false;
    while (left <= right) {
      int mid = left + (right - left) / 2;

      int row = mid / numCols;
      int col = mid % numCols;
      if (matrix[row][col] > target) {
        right = mid - 1;
      } else if (matrix[row][col] < target) {
        left = mid + 1;
      } else {
        res = true;
        break;
      }
    }

    return res;
  }
};
```

3. Koko Eating Bananas

When we have a choice for the limit of a loop, make sure to choose the limit
which is smaller. For instance, here in the `canEat` function, we can loop till
the hours or the piles length; we choose piles length as it is smaller than
hours.

```cpp
class Solution {
public:
  int minEatingSpeed(vector<int> &piles, int h) {
    int maxK = *max_element(piles.begin(), piles.end());
    int minK = 1;

    int res = 1;
    while (minK <= maxK) {
      int k = minK + (maxK - minK) / 2;

      if (canEat(piles, h, k)) {
        res = k;
        maxK = k - 1;
      } else {
        minK = k + 1;
      }
    }

    return res;
  }

private:
  bool canEat(vector<int> &piles, int hours, int k) {
    int h = 0;
    for (int &pile : piles) {
      h += ceil((double)pile / k);
      if (h > hours) {
        return false;
      }
    }

    return true;
  }
};
```

4. Find Minimum in Rotated Sorted Array

```cpp
class Solution {
public:
  int findMin(vector<int> &nums) {
    int res = nums[0];

    int left = 0;
    int right = nums.size() - 1;
    while (left <= right) {
      // If we reach a sorted array we take the min of the leftmost num and the
      // previous minimum. This is either the entire array, the left subarray, or
      // the right subarray.
      if (nums[left] < nums[right]) {
        res = min(res, nums[left]);
        break;
      }

      // This is the binary search portion
      int mid = left + (right - left) / 2;
      res = min(res, nums[mid]);

      // At this point, the entire array is divided into two sorted subarrays.
      // By the description of the problem, we can infer that the left subarray
      // elements will be greater than the right subarray elements

      // If we are in the left subarray we search for the minimum in the right
      // subarray next
      if (nums[mid] >= nums[left]) {
        left = mid + 1;
      } else {
        // else we are in the right subarray then we search in the left
        right = mid - 1;
      }
    }

    return res;
  }
};
```

5. Search in Rotated Sorted Array

The enire array is possibly divided into two sorted arrays (possible beacause it might happen that the enire array is sorted).

When the mid is in the left subarray:

- and the target is greater than the mid, then the target is possibly in the right subarray.
- and the target is lesser than the mid, then the target is possibly in the left subarray or in the right subarray. We check if the target is even smaller than the left element of the left subarray; if it is then the target is possibly in the right subarray

Similarly for the right subarray.

NOTE: If left, mid, and right are part of a sorted subarray we consider it to be a left subarray.

```cpp
class Solution {
public:
  int search(vector<int> &nums, int target) {
    int res = -1;
    int left = 0;
    int right = nums.size() - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (target == nums[mid]) {
        res = mid;
        break;
      }

      // Check if we are in the left subarray
      if (nums[mid] >= nums[left]) {
        if (target > nums[mid] || target < nums[left]) {
          left = mid + 1;
        } else {
          // we continue searching in the left subarray
          right = mid - 1;
        }
      } else {
        if (target < nums[mid] || target > nums[right]) {
          right = mid - 1;
        } else {
          // we continue in the right subarray
          left = mid + 1;
        }
      }
    }

    return res;
  }
};
```

6. Time Based Key Value Store

It is possible to find out the taget as well as the number closest to the target (num < target, such that num is the closest to target) using plain binary search.

``cpp
class TimeMap {
public:
  TimeMap() { }

  void set(string key, string value, int timestamp) {
      store[key].push_back({timestamp, value});
  }

  string get(string key, int timestamp) {
    if (!store.contains(key)) {
      return "";
    }

    int left = 0;
    int right = store[key].size() - 1;
    string res = "";
    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (store[key][mid].first <= timestamp) {
        res = store[key][mid].second;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return res;
  }

private:
  unordered_map<string, vector<pair<int, string>>> store;
};
```
