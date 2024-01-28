1. Happy Number

The fast and slow pointers will either converge to each other, or they will to 1.

```cpp
class Solution {
public:
  bool isHappy(int n) {
    int slow = getNext(n);
    int fast = getNext(getNext(n));

    while (slow != fast) {
      slow = getNext(slow);
      fast = getNext(getNext(fast));
    }

    return slow == 1;
  }

private:
  int getNext(int n) {
    int res = 0;
    while (n > 0) {
      int digit = n % 10;
      res += (digit * digit);

      n /= 10;
    }

    return res;
  }
};
```

2. Plus One

```cpp
class Solution {
public:
  vector<int> plusOne(vector<int> &digits) {
    int carry = 1;
    for (int i = digits.size() - 1; i >= 0; --i) {
      if (digits[i] < 9) {
        ++digits[i];
        return digits;
      }

      digits[i] = 0;
    }

    // We reach here if the digits were all 9's like [9, 9, 9]
    // it will become [0, 0, 0]. We need it to be [1, 0, 0, 0]
    digits.insert(digits.begin(), 1);
    return digits;
  }
};
```

3. Rotate Image

- For clockwise rotation, reverse up and down and then transpose
- For anticlockwise rotation, reverse left and right and then transpose

```cpp
class Solution {
public:
  void rotate(vector<vector<int>> &matrix) {
    reverse(matrix.begin(), matrix.end());

    for (int i = 0; i < matrix.size(); ++i) {
      for (int j = 0; j < i; ++j) {
        swap(matrix[i][j], matrix[j][i]);
      }
    }
  }
};
```

4. Spiral Matrix

```cpp
class Solution {
public:
 vector<int> spiralOrder(vector<vector<int>> &matrix) {
    int top = 0;
    int right = matrix[0].size() - 1;
    int bottom = matrix.size() - 1;
    int left = 0;

    vector<int> res;
    while (left <= right && top <= bottom) {
      // Add all the elements of the top row
      for (int i = left; i <= right; ++i) {
        res.push_back(matrix[top][i]);
      }
      ++top;

      // Add all the elements of the right column
      for (int i = top; i <= bottom; ++i) {
        res.push_back(matrix[i][right]);
      }
      --right;

      // Check if we have traversed all the columns or all the rows
      if (left > right || top > bottom) {
        break;
      }

      // Add all the elements of the bottom row
      for (int i = right; i >= left; --i) {
        res.push_back(matrix[bottom][i]);
      }
      --bottom;

      // Add all the elements of the left column
      for (int i = bottom; i >= top; --i) {
        res.push_back(matrix[i][left]);
      }
      ++left;
    }

    return res;
 }
};
```

5. Set Matrix Zeroes

```cpp
class Solution {
public:
  void setZeroes(vector<vector<int>> &matrix) {
    vector<bool> rows(matrix.size(), false);
    vector<bool> cols(matrix[0].size(), false);

    // Find out which rows and cols need to zeroed out
    for (int i = 0; i < matrix.size(); ++i) {
      for (int j = 0; j < matrix[0].size(); ++j) {
        if (matrix[i][j] == 0) {
          rows[i] = true;
          cols[j] = true;
        }
      }
    }

    // Zeroing out the rows and cols
    for (int i = 0; i < matrix.size(); ++i) {
      for (int j = 0; j < matrix[0].size(); ++j) {
        if (rows[i] || cols[j]) {
          matrix[i][j] = 0;
        }
      }
    }

    return;
  }
};
```

6. Pow(x, n)

```cpp
class Solution {
public:
  double myPow(double x, int n) {
    if (x == 0) return 0;

    double res = helper(x, abs(n));
    return n > 0 ? res : 1 / res;
  }

private:
  double helper(double x, int n) {
    if (n == 0) return 1;

    double res = helper(x, n / 2);
    return n % 2 == 0 ? res * res : res * res * x;
  }
};
```

7. Multiply Strings

```cpp
class Solution {
public:
  string multiply(string num1, string num2) {
    int m = num1.size();
    int n = num2.size();

    string result(m + n, '0');

    for (int i = m - 1; i >= 0; i--) {
      for (int j = n - 1; j >= 0; j--) {
        // i + j + 1 is the current index
        // i + j is the next index
        int sum = (num1[i] - '0') * (num2[j] - '0') + (result[i + j + 1] - '0');
        result[i + j + 1] = sum % 10 + '0';
        result[i + j] += sum / 10;
      }
    }

    for (int i = 0; i < res.size(); i++) {
      if (result[i] != '0') {
        return result.substr(i);
      }
    }
    return "0";
  }
};
```

8. Detect Squares

For counting squares, we iterate over each point and check if it is a diagonally
opposite of the given point (diagonally opposite such that the x and y distance is
same). If that is satisfied, we then check for the presence of the other two points.

```cpp
class DetectSquares {
public:
  DetectSquares() { }

  void add(vector<int> point) {
    ++freq[{point[0], point[1]}];
    points.push_back({point[0], point[1]});
  }

  int count(vector<int> point) {
    int res = 0;

    int px = point[0];
    int py = point[1];
    for (pair<int, int> p : points) {
      int x = p.first;
      int y = p.second;

      // If not diagonal point, or is overlapping
      if (abs(py - y) != abs(px - x) || (x == px && y == py)) {
        continue;
      }

      if (freq.contains({x, py}) && freq.contains({px, y})) {
        res += freq[{x, py}] * freq[{px, y}];
      }
    }

    return res;
  }

private:
  vector<pair<int, int>> points;
  map<pair<int, int>, int> freq; // { point, freq }
};
```
