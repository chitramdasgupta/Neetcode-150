1. Valid Parentheses

```cpp
class Solution {
public:
  bool isValid(string s) {
    stack<char> parenStack;
    for (char &c : s) {
      if (c == '(' || c == '{' || c == '[') {
        parenStack.push(c);
      } else {
        if (parenStack.empty()) {
          return false;
        }

        char last = parenStack.top();
        parenStack.pop();
        if ((c == ')' && last != '(') || (c == '}' && last != '{') ||
            (c == ']' && last != '[')) {
          return false;
        }
      }
    }

    return parenStack.empty();
  }
};
```

2. Min Stack

We maintain two stacks: a normal one and a monotonically decreasing one.

```cpp
class MinStack {
public:
  MinStack() { }

  void push(int val) {
    stk.push(val);

    if (minStk.empty() || val < minStk.top().first) {
      minStk.push({val, 1});
    } else if (val == minStk.top().first) {
      ++minStk.top().second;
    }
  }

  void pop() {
    if (stk.top() == minStk.top().first) {
      --minStk.top().second;
      if (minStk.top().second == 0) {
        minStk.pop();
      }
    }

    stk.pop();
  }

  int top() {
    return stk.top();
  }

  int getMin() {
    return minStk.top().first;
  }

private:
  stack<int> stk;
  stack<pair<int, int>> minStk; // {val, freq}
};
```

3. Evaluate Reverse Polish Notation

```cpp
class Solution {
public:
  int evalRPN(vector<string> &tokens) {
    stack<int> stk;
    for (string &token : tokens) {
      if (token.size() > 1 || isdigit(token[0])) {
        stk.push(stoi(token));
      } else {
        int second = stk.top();
        stk.pop();
        int first = stk.top();
        stk.pop();

        int temp = 0;
        if (token == "+") {
          temp = (first + second);
        } else if (token == "-") {
          temp = (first - second);
        } else if (token == "*") {
          temp = (first * second);
        } else {
          temp = (first / second);
        }

        stk.push(temp);
      }
    }

    return stk.top();
  }
};
```

4. Generate Parentheses

```cpp
class Solution {
public:
  vector<string> generateParenthesis(int n) {
    vector<string> res;
    generate(n, 0, 0, "", res);
    return res;
  }

private:
  void generate(int n, int open, int close, string curr, vector<string> &res) {
    if (open == n && close == n) {
      res.push_back(curr);
      return;
    }

    if (open < n) {
      generate(n, open + 1, close, curr + '(', res);
    }

    if (close < open) {
      generate(n, open, close + 1, curr + ')', res);
    }
  }
};
```

5. Daily Temperatures

We will maintain a monotonically non-increasing stack.

```cpp
class Solution {
public:
  vector<int> dailyTemperatures(vector<int> &temperatures) {
    int n = temperatures.size();
    stack<pair<int, int>> stk; // {temperature, index}
    vector<int> res(n, 0);

    for (int i = 0; i < n; ++i) {
      while (!stk.empty() && temperatures[i] > stk.top().first) {
        pair<int, int> prev = stk.top();
        stk.pop();

        res[prev.second] = i - prev.second;
      }

      stk.push({temperatures[i], i});
    }

    return res;
  }
};
```

6. Car Fleet

```cpp
class Solution {
public:
  int carFleet(int target, vector<int> &position, vector<int> &speed) {
    vector<pair<int, double>> cars; // {position, time left};
    for (int i = 0; i < speed.size(); ++i) {
      cars.push_back({position[i], (double)(target - position[i]) / speed[i]});
    }

    // Sorting the cars in descending order of position
    sort(cars.begin(), cars.end());

    int res = 0;
    // The maximum time to reach the destination (among the cars seen till now)
    // This will be of the slowest car seen till now
    double maxTime = 0;
    for (int i = cars.size() - 1; i >= 0; --i) {
      double time = cars[i].second;
      // Check if the current car will take more time (is slower) than the
      // slowest one seen till now; if it is then it will make a fleet
      if (time > maxTime) {
        maxTime = time;
        ++res;
      }
    }

    return res;
  }
};
```
