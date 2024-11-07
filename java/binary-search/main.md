# Binary Search

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int res = -1;
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
}
```

# Search A2 D Matrix

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = rows * cols - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            int rowIndex = mid / cols;
            int colIndex = mid % cols;
            if (matrix[rowIndex][colIndex] > target) {
                right = mid - 1;
            } else if (matrix[rowIndex][colIndex] < target) {
                left = mid + 1;
            } else {
                res = mid;
                break;
            }
        }

        return res != -1;
    }
}
```

# Koko Eating Bananas

```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Arrays.stream(piles).max().getAsInt();
        int res = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canEat(piles, h, mid)) {
                right = mid - 1;
                res = Math.min(mid, res);
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    private boolean canEat(int[] piles, int h, int k) {
        double hours = 0;
        for (double pile : piles) {
            hours += Math.ceil(pile / k);

            if (hours > h) {
                return false;
            }
        }

        return true;
    }
}
```

# Find Minimum In Rotated Sorted Array

```java
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int res = nums[0];
        while (left <= right) {
            if (nums[left] <= nums[right]) {
                res = Math.min(nums[left], res);
                break;
            }

            int mid = left + (right - left) / 2;
            res = Math.min(nums[mid], res);
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }
}
```

# Search In Rotated Sorted Array

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                res = mid;
                break;
            }

            // If we are in left subarray
            if (nums[mid] >= nums[left]) {
                // If target is less than left or greater than mid
                // move to the right portion
                if (target < nums[left] || target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target > nums[right] || target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return res;
    }
}
```

# Time Based Key Value Store

```java
class TimeMap {
    private final Map<String, List<String[]>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        List<String[]> list = map.getOrDefault(key, new ArrayList<>());
        list.add(new String[] {value, String.valueOf(timestamp)});
        map.put(key, list);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }

        List<String[]> values = map.get(key);
        int left = 0;
        int right = values.size() - 1;
        String res = "";
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (Integer.parseInt(values.get(mid)[1]) < timestamp) {
                left = mid + 1;
                res = values.get(mid)[0];
            } else if (Integer.parseInt(values.get(mid)[1]) > timestamp) {
                right = mid - 1;
            } else {
                res = values.get(mid)[0];
                break;
            }
        }

        return res;
    }
}
```

