# Subsets

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
      List<Integer> curr = new ArrayList<>();
      backtrack(nums, curr, 0);

      return res;
    }

    private void backtrack(int[] nums, List<Integer> curr, int start) {
      res.add(new ArrayList<>(curr));

      for (int i = start; i < nums.length; ++i) {
        curr.add(nums[i]);
        backtrack(nums, curr, i + 1);
        curr.removeLast();
      }
    }
}
```

# Subsets I I

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> curr = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, curr, 0);

        return res;
    }

    private void backtrack(int[] nums, List<Integer> curr, int start) {
      res.add(new ArrayList<>(curr));

      for (int i = start; i < nums.length; ++i) {
        if (i > start && nums[i] == nums[i - 1]) {
          continue;
        }

        curr.add(nums[i]);
        backtrack(nums, curr, i + 1);
        curr.removeLast();
      }
    }
}
```

# Combination Sum

```java
class Solution {
  private List<List<Integer>> res = new ArrayList<>();

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<Integer> curr = new ArrayList<>();
    backtrack(candidates, curr, target, 0);

    return res;
  }

  private void backtrack(int[] candidates, List<Integer> curr, int target, int start) {
    if (target < 0) {
      return;
    }
    if (target == 0) {
      res.add(new ArrayList<>(curr));
      return;
    }

    for (int i = start; i < candidates.length; ++i) {
      curr.add(candidates[i]);
      backtrack(candidates, curr, target - candidates[i], i);
      curr.removeLast();
    }
  }
}
```

# Combination Sum I I

```java
class Solution {
  private List<List<Integer>> res = new ArrayList<>();

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<Integer> curr = new ArrayList<>();
    Arrays.sort(candidates);
    backtrack(candidates, curr, target, 0);
    return res;
  }

  private void backtrack(int[] candidates, List<Integer> curr, int target, int start) {
    if (target < 0) {
      return;
    }
    if (target == 0) {
      res.add(new ArrayList<>(curr));
      return;
    }

    for (int i = start; i < candidates.length; ++i) {
      if (i > start && candidates[i] == candidates[i - 1]) {
        continue;
      }

      curr.add(candidates[i]);
      backtrack(candidates, curr, target - candidates[i], i + 1);
      curr.removeLast();
    }
  }
}
```

# Permutations

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums, new ArrayList<>());

        return res;
    }

    private void backtrack(int[] nums, List<Integer> curr) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (curr.contains(nums[i])) {
                continue;
            }

            curr.add(nums[i]);
            backtrack(nums, curr);
            curr.removeLast();
        }
    }
}
```

# Palindrome Partioning

```java
class Solution {
    private List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, new ArrayList<>(), 0);
        return res;
    }

    private void backtrack(String s, List<String> curr, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(curr));
        }

        for (int i = start; i < s.length(); ++i) {
            String temp = s.substring(start, i + 1);

            if (isPalindrome(temp)) {
                curr.add(temp);
                backtrack(s, curr, i + 1);
                curr.removeLast();
            }
        }
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }

            ++left;
            --right;
        }

        return true;
    }
}
```

# Word Search

```java
class Solution {
    private int rows;
    private int cols;

    public boolean exist(char[][] board, String word) {
        this.rows = board.length;
        this.cols = board[0].length;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int row, int col, int index) {
        if (row < 0 || row >= rows || col < 0 || col >= cols ||
            index == word.length() || board[row][col] != word.charAt(index)) {
            return false;
        }

        if (index == word.length() - 1) {
            return true;
        }

        board[row][col] = '#';

        if (dfs(board, word, row, col + 1, index + 1) ||
            dfs(board, word, row + 1, col, index + 1) ||
            dfs(board, word, row, col - 1, index + 1) ||
            dfs(board, word, row - 1, col, index + 1)) {
            return true;
        }

        board[row][col] = word.charAt(index);

        return false;
    }
}
```

# Letter Combinations Of A Phone Number

```java
class Solution {
    private List<String> res = new ArrayList<>();
    private Map<Character, String> digitsMap = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return res;
        }

        digitsMap.put('2', "abc");
        digitsMap.put('3', "def");
        digitsMap.put('4', "ghi");
        digitsMap.put('5', "jkl");
        digitsMap.put('6', "mno");
        digitsMap.put('7', "pqrs");
        digitsMap.put('8', "tuv");
        digitsMap.put('9', "wxyz");

        backtrack(digits, new StringBuilder(), 0);
        return res;
    }

    private void backtrack(String digits, StringBuilder curr, int start) {
        if (start == digits.length()) {
            res.add(curr.toString());
            return;
        }

        String values = digitsMap.get(digits.charAt(start));
        for (int i = 0; i < values.length(); ++i) {
            curr.append(values.charAt(i));
            backtrack(digits, curr, start + 1);
            curr.deleteCharAt(curr.length() - 1);
        }   
    }
}
```

