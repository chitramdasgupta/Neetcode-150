1. Maximum subarray

```cpp
class Solution {
public:
  int maxSubArray(vector<int> &nums) {
  	int maxSoFar = nums[0];
  	int maxEndingHere = nums[0];
  	for (int i = 1; i < nums.size(); ++i) {
  		maxEndingHere = max(maxEndingHere + nums[i], nums[i]);
  		maxSoFar = max(maxSoFar, maxEndingHere);
  	}

  	return maxSoFar;
  }
};
```

2. Jump Game

```cpp
class Solution {
public:
  bool canJump(vector<int> &nums) {
  	vector<bool> dp(nums.size(), false);
  	int gap = 0;
  	for (int i = nums.size() - 1; i >= 0; --i) {
  		if (nums[i] >= gap) {
  			dp[i] = true;
  			gap = 1;
  			continue;
  		}

  		++gap;
  	}

  	return dp[0];
  }
};
```

3. Jump Game II

```cpp
class Solution {
public:
  int jump(vector<int> &nums) {
  	int res = 0;
  	// The boundaries of the window
  	int left = 0;
  	int right = 0;

  	while (right < nums.size() - 1) {
  		int farthest = 0;
  		for (int i = left; i <= right; ++i) {
  			farthest = max(farthest, i + nums[i]);
  		}

  		left = right + 1;
  		right = farthest;

  		++res;
  	}

  	return res;
  }

};
```

4. Gas Station

```cpp
class Solution {
public:
  int canCompleteCircuit(vector<int> &gas, vector<int> &cost) {
    int totalGas = accumulate(gas.begin(), gas.end(), 0);
    int totalCost = accumulate(cost.begin(), cost.end(), 0);
    if (totalCost > totalGas) {
    	return -1;
    }

    // This means that a unique solution exists (as per the problem)
    int start = 0;
    int tank = 0;
    for (int i = 0; i < gas.size(); ++i) {
    	tank += gas[i] - cost[i];
    	// If total is negative then we cannot start from this index. We try from
    	// the next index
    	if (tank < 0) {
    		// we reset the start to the next index because we cannot start from the
    		// current index, neither can we from any indices we have traversed till now
    		// as it leads to negative gas
    		start = i + 1;
        tank = 0;
    	}
    }

    return start;
  }
};
```

5. Hand of Straights

```cpp
class Solution {
public:
  bool isNStraightHand(vector<int> &hand, int groupSize) {
  	if (hand.size() % groupSize != 0) {
  		return false;
  	}

  	// We use an ordered map so that the keys are kept in sorted order
  	map<int, int> freq;
  	for (int num : hand) {
  		++freq[num];
  	}

  	while (!freq.empty()) {
  		// begin() gives the smallest number in the map
  		int first = freq.begin()->first;

  		for (int i = 0; i < groupSize; ++i) {
  			if (freq[first + i] == 0) {
  				return false;
  			}

  			--freq[first + i];
  			if (freq[first + i] == 0) {
  				freq.erase(first + i);
  			}
  		}
  	}

  	return true;
  }
};
```

6. Merge Triplets to Form Target Triplet

```cpp
class Solution {
public:
  bool mergeTriplets(vector<vector<int>> &triplets, vector<int> &target) {
    unordered_set<int> valid;

    for (vector<int> &triplet : triplets) {
    	if (triplet[0] > target[0] || triplet[1] > target[1] ||
    	    triplet[2] > target[2]) {
    		continue;
    	}

    	for (int i = 0; i < 3; ++i) {
    		if (triplet[i] == target[i]) {
    			valid.insert(i);
    		}
    	}
    }

    return valid.size() == 3;
  }
};
```

7. Partition Labels

```cpp
class Solution {
public:
  vector<int> partitionLabels(string s) {
    vector<int> lastIndex(26);
    for (int i = 0; i < s.size(); ++i) {
      lastIndex[s[i] - 'a'] = i;
    }

    int end = 0;
    int size = 0;
    vector<int> res;
    for (int i = 0; i < s.size(); ++i) {
      ++size;
      end = max(end, lastIndex[s[i] - 'a']);

      if (i == end) {
        res.push_back(size);
        size = 0;
      }
    }

    return res;
  }
};
```

8. Valid Parenthesis String

```cpp
class Solution {
public:
  bool checkValidString(string s) {
    // diff = (no of left parenthesis) - (no of right parenthesis)
    // OR diff can be thought of as the no of open parentheses
    int minOpen = 0;
    int maxOpen = 0;

    for (char c : s) {
      if (c == '(') {
        ++minOpen;
        ++maxOpen;
      } else if (c == ')') {
        --minOpen;
        --maxOpen;
      } else {
        // when c == '*'
        ++maxOpen; // assuming '*' to be '('
        --minOpen; // assuming '*' to be ')'
      }

      // if there are more closed parentheses than open parentheses and '*'
      if (maxOpen < 0) {
        return false;
      }

      // We do not let minOpen become negative; if it becomes negative at a
      // stage, it means that a '*' we considered ')' should be ignored going
      // forward
      minOpen = max(0, minOpen);
    }

    return diffMin == 0;
  }
};
```
