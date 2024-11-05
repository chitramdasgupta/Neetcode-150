# Valid Palindrome

```java
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
                continue;
            }

            if (!Character.isLetterOrDigit(s.charAt(right))) {
                --right;
                continue;
            }

            if(Character.toLowerCase(s.charAt(left)) !=
               Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            ++left;
            --right;
        }

        return true;
    }
}
```

# Two Sum I I Input Array Is Sorted

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        int[] res = new int[2];
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                res[0] = left + 1;
                res[1] = right + 1;
                break;
            } else if (numbers[left] + numbers[right] > target) {
                --right;
            } else {
                ++left;
            }
        }

        return res;
    }
}
```

# Sum

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                if (left > i + 1 && nums[left] == nums[left - 1]) {
                    ++left;
                    continue;
                }

                if (nums[left] + nums[right] == -nums[i]) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
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
}
```

# Container With Most Water

```java
class Solution {
    public int maxArea(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int distance = right - left;
            res = Math.max(distance * Math.min(height[left], height[right]), res);

            if (height[left] < height[right]) {
                ++left;
            } else {
                --right;
            }
        }

        return res;
    }
}
```
