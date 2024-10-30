# Kth Largest Element In A Stream

```java
class KthLargest {
    private final PriorityQueue<Integer> pq = new PriorityQueue<>();
    private final int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (pq.size() < k) {
            pq.add(val);
            return pq.peek();
        }

        if (val > pq.peek()) {
            pq.poll();
            pq.add(val);
        }

        return pq.peek();
    }
}
```

# Last Stone Weight

```java
class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> (int) a).reversed());
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            int y = pq.poll();
            int x = pq.poll();

            if (y > x) {
                pq.offer(y - x);
            }
        }

        return pq.isEmpty() ? 0 : pq.peek();
    }
}
```

# K Closest Points To Origin

```java
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int[] point : points) {
            int distance = Math.abs(point[0] * point[0] + point[1] * point[1]);

            if (pq.size() < k) {
                pq.offer(new int[] {distance, point[0], point[1]});
                continue;
            }

            if (distance < pq.peek()[0]) {
                pq.poll();
                pq.add(new int[] {distance, point[0], point[1]});
            }
        }

        int[][] res = new int[k][2];
        int index = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            res[index++] = new int[] {curr[1], curr[2]};
        }

        return res;
    }
}
```

# Kth Largest Element In An Array

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            if (pq.size() < k) {
                pq.offer(num);
                continue;
            }

            if (num > pq.peek()) {
                pq.poll();
                pq.add(num);
            }
        }

        return pq.peek();
    }
}
```

# Task Scheduler

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task :tasks) {
            freq[task - 'A'] += 1;
        }

        PriorityQueue<int[]> processing = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Arrays.sort(freq);
        int[] sortedFreq = IntStream.range(0, freq.length)
                                .map(i -> freq[freq.length - 1 - i])
                                .toArray();

        for (int i = 0; i < sortedFreq.length; ++i) {
            if (sortedFreq[i] > 0) {
                processing.offer(new int[] {i + 1, sortedFreq[i]});
            }
        }

        int time = 0;
        while (!processing.isEmpty()) {
            ++time;
            if (time < processing.peek()[0]) {
                continue;
            }

            int[] currProcess = processing.poll();
            currProcess[1] -= 1;

            if (currProcess[1] > 0) {
                processing.offer(new int[] {currProcess[0] + n + 1, currProcess[1]});
            }
        }

        return time;
    }
}
```

# Design Twitter

```java
class Twitter {
    private final List<int[]> tweets;
    private final Map<Integer, Set<Integer>> following;

    public Twitter() {
        tweets = new ArrayList<>();
        following = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweets.add(new int[] {userId, tweetId});
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        int count = 0;
        for (int i = tweets.size() - 1; i >= 0; --i) {
            if (res.size() == 10) {
                break;
            }

            int[] tweet = tweets.get(i);
            if ((following.get(userId) != null && following.get(userId).contains(tweet[0])) ||
                 tweet[0] == userId) {
                res.add(tweet[1]);
            }
        }

        return res;
    }

    public void follow(int followerId, int followeeId) {
        if (!following.containsKey(followerId)) {
            following.put(followerId, new HashSet<>());
        }

        following.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (!following.containsKey(followerId)) {
            return;
        }

        following.get(followerId).remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
```

