# Contains Duplicate

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }

            seen.add(num);
        }

        return false;
    }
}
```

# Valid Anagram

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> firstFreq = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            firstFreq.put(s.charAt(i), firstFreq.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < t.length(); ++i) {
            if (!firstFreq.containsKey(t.charAt(i))) {
                return false;
            }

            firstFreq.put(t.charAt(i), firstFreq.getOrDefault(t.charAt(i), 0) - 1);
            if (firstFreq.get(t.charAt(i)) < 0) {
                return false;
            }
        }

        return true;
    }
}
```

# Two Sum

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; ++i) {
            if (seen.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = seen.get(target - nums[i]);
                break;
            } else {
                seen.put(nums[i], i);
            }
        }

        return res;
    }
}
```

# Group Anagrams

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] freq = new int[26];
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                freq[c - 'a'] += 1;
            }

            String key = Arrays.toString(freq);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }
}
```

# Top K Frequent Elements

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // min heap
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int num : freq.keySet()) {
            if (pq.size() < k) {
                pq.add(new int[] {freq.get(num), num});
                continue;
            }

            if (freq.get(num) > pq.peek()[0]) {
                pq.poll();
                pq.add(new int[] {freq.get(num), num});
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; ++i) {
            res[i] = pq.poll()[1];
        }

        return res;
    }
}
```

# Encode And Decode Strings

```java
class Solution {
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            int len = s.length();
            sb.append(len);
            sb.append('#');
            sb.append(s);
        }

        return sb.toString();
    }

    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            // Find the delimiter '#'
            int j = i;
            while (j < str.length() && str.charAt(j) != '#') {
                j++;
            }

            // Parse the length
            int len = Integer.parseInt(str.substring(i, j));

            // Extract the string
            String s = str.substring(j + 1, j + 1 + len);
            res.add(s);

            // Move to the start of next length indicator
            i = j + 1 + len;
        }

        return res;
    }
}
```

# Product Of Array Except Self

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;

        int[] suffix = new int[len];
        Arrays.fill(suffix, 1);
        int[] prefix = new int[len];
        Arrays.fill(prefix, 1);

        for (int i = len - 2; i >= 0; --i) {
            suffix[i] = nums[i + 1] * suffix[i + 1];
        }
        for (int i = 1; i < len; ++i) {
            prefix[i] = nums[i - 1] * prefix[i - 1];
        }

        int[] res = new int[len];
        for (int i = 0; i < len; ++i) {
            res[i] = suffix[i] * prefix[i];
        }

        return res;
    }
}
```

# Valid Sudoku

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int size = 9;
        boolean[][] rows = new boolean[size][size + 1];
        boolean[][] cols = new boolean[size][size + 1];
        boolean[][] squares = new boolean[size][size + 1];

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                char cell = board[i][j];

                if (cell == '.') {
                    continue;
                }

                int value = cell - '0';
                int square = 3 * (i / 3) + (j / 3);
                if (rows[i][value] || cols[j][value] || squares[square][value]) {
                    return false;
                }

                rows[i][value] = true;
                cols[j][value] = true;
                squares[square][value] = true;
            }
        }

        return true;
    }
}
```

# Longest Consecutive Sequence

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> unique = new HashSet<>();
        for (int num : nums) {
            unique.add(num);
        }

        int res = 0;
        for (int num : nums) {
            // Check if this num is the start of a sequence
            if (!unique.contains(num - 1)) {
                int len = 0;
                int temp = num;
                while (unique.contains(temp)) {
                    ++len;
                    ++temp;
                }

                res = Math.max(len, res);
            }
        }

        return res;
    }
}
```

