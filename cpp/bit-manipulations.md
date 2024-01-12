1. Single Number

```cpp
class Solution {
public:
  int singleNumber(vector<int> &nums) {
    int res = 0;
    for (int &num : nums) {
      res ^= num;
    }

    return res;
  }
};
```

2. Number of 1 Bits

```cpp
class Solution {
public:
  int hammingWeight(uint32_t n) {
    int res = 0;
    while (n > 0) {
      if (n & 1) {
        ++res;
      }
      n >>= 1;
    }

    return res;
  }
};
```

3. Counting Bits

```cpp
class Solution {
public:
  vector<int> countBits(int n) {
    vector<int> res(n + 1, 0);
    int offset = 1;
    for (int i = 1; i <= n; ++i) {
      if (offset * 2 == i) {
        offset = i;
      }
      dp[i] = 1 + dp[i - offset];
    }

    return res;
  }
};
```

4. Reverse Bits

```cpp
class Solution {
public:
  uint32_t reverseBits(uint32_t n) {
    int res = 0;
    for (int i = 0; i < 32; ++i) {
      res <<= 1;
      res |= n & 1;
      n >>= 1;
    }

    return res;
  }
};
```

5. Missing Number

```cpp
class Solution {
public:
  int missingNumber(vector<int> &nums) {
    int totalSum = nums.size() * (nums.size() + 1) / 2;
    int availSum = accumulate(nums.begin(), nums.end(), 0);

    return totalSum - availSum;
  }
};
```

6. Sum of Two Integers

```cpp
class Solution {
public:
  int getSum(int a, int b) {
    while (b != 0) {
      int carry = a & b;
      a ^= b;
      b = carry << 1;
    }

    return a;
  }
};
```

7. Reverse Integer


```cpp
class Solution {
public:
  int reverse(int x) {
    int res = 0;
    while (x != 0) { // checking x != 0 because x can be -ve or +ve
      if(res > INT_MAX / 10 || res < INT_MIN / 10) return 0;

      int digit = x % 10;
      res = res * 10 + digit;

      x /= 10;
    }

    return res;
  }
};
```
