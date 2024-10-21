# Maximum Subarray

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int maxEndingHere = nums[0];
        int maxSoFar = nums[0];

        for (int i = 1; i < nums.length; ++i) {
          maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
          maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }

        return maxSoFar;
    }
}
```

# Jump Game

```java
class Solution {
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        Arrays.fill(dp, false);

        int gap = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
          if (nums[i] >= gap) {
            dp[i] = true;
            gap = 1;
          } else {
          ++gap;
          }
        }

        return dp[0];
    }
}
```

# Jump Game II

```java
class Solution {
    public int jump(int[] nums) {
        int left = 0;
        int right = 0;

        int res = 0;
        while (right < nums.length - 1) {
          int farthest = 0;
          for (int i = left; i <= right; ++i) {
            farthest = Math.max(i + nums[i], farthest);
          }

          left = right + 1;
          right = farthest;

          ++res;
        }

        return res;
    }
}
```

# Gas Station

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) {
          return -1;
        }

        int start = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; ++i) {
          tank += gas[i] - cost[i];

          if (tank < 0) {
            start = i + 1;
            tank = 0;
          }
        }

        return start;
    }
}
```

# Hand Of Straights

```java
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
      if (hand.length % groupSize != 0) {
        return false;
      }

      PriorityQueue<Integer> minHeap = new PriorityQueue<>();
      Map<Integer, Integer> freq = new HashMap<>();
      for (int num : hand) {
        if(!freq.containsKey(num)) {
          freq.put(num, 0);
        }

        freq.put(num, freq.get(num) + 1);
      }

      for (int num : freq.keySet()) {
        minHeap.add(num);
      }

      while (!minHeap.isEmpty()) {
        int first = minHeap.peek();

        for (int i = first; i < first + groupSize; ++i) {
          if (!freq.containsKey(i)) {
            return false;
          }

          freq.put(i, freq.get(i) - 1);
          if (freq.get(i) == 0) {
            if (i != minHeap.peek()) {
              return false;
            }

            minHeap.poll();
          }
        }
      }

      return true;
    }
}
```

# Merge Triplets To Form Target Triplet

```java
class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        Set<Integer> valid = new HashSet<>();
        for (int[] triplet : triplets) {
            if (triplet[0] > target[0] || triplet[1] > target[1] || triplet[2] > target[2]) {
                continue;
            }

            for (int i = 0; i < 3; ++i) {
                if (triplet[i] == target[i]) {
                    valid.add(i);
                }
            }
        }

        return valid.size() == 3;
    }
}
```

# Partition Labels

```java
class Solution {
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            lastIndex.put(s.charAt(i), i);
        }

        List<Integer> res = new ArrayList<>();
        int size = 0;
        int end = 0;
        for (int i = 0; i < s.length(); ++i) {
            size += 1;
            end = Math.max(lastIndex.get(s.charAt(i)), end);

            if (i == end) {
                res.add(size);
                size = 0;
            }
        }

        return res;
    }
}
```

# Valid Parenthesis String

```java
class Solution {
    public boolean checkValidString(String s) {
        int leftMin = 0;
        int leftMax = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++leftMin;
                ++leftMax;
            } else if (s.charAt(i) == ')') {
                --leftMin;
                --leftMax;
            } else {
                --leftMin;
                ++leftMax;
            }

            if (leftMax < 0) {
                return false;
            }

            if (leftMin < 0) {
                leftMin = 0;
            }
        }

        return leftMin == 0;
    }
}
```
