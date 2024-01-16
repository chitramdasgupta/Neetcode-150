1. Kth Largest Element in a Stream

```cpp
class KthLargest {
public:
  KthLargest(int k, vector<int> &nums) {
    for(int num : nums) {
      if (minHeap.size() < k) {
        minHeap.push(num);
        continue;
      }

      if (num > minHeap.top()) {
        minHeap.pop();
        minHeap.push(num);
      }
    }
  }

  int add(int val) {
    if (minHeap.size() < k) {
      minHeap.push(val);
      return minHeap.top();
    }

    if (val <= minHeap.top()) {
      return minHeap.top();
    }

    minHeap.pop();
    minHeap.push(val);

    return minHeap.top();
  }

private:
  priority_queue<int, vector<int>, greater<int>> minHeap;
};
```

2. Last Stone Weight

```cpp
class Solution {
public:
  int lastStoneWeight(vector<int> &stones) {
    priority_queue<int> maxHeap;

    for (int stone : stones) {
      maxHeap.push(stone);
    }

    while (maxHeap.size() > 1) {
      int y = maxHeap.top();
      maxHeap.pop();
      int x = maxHeap.top();
      maxHeap.pop();

      if (y > x) {
        maxHeap.push(y - x);
      }
    }

    return maxHeap.size() == 1 ? maxHeap.top() : 0;
  }
};
```

3. K Closest Points to Origin

```cpp
class Solution {
public:
  vector<vector<int>> kClosest(vector<vector<int>> &points, int k) {
    priority_queue<vector<int>, vector<vector<int>>, CompareVectors> minHeap;

    for (vector<int> point : points) {
      int dist = (point[0] * point[0]) + (point[1] * point[1]);
      minHeap.push({dist, point[0], point[1]});
    }

    vector<vector<int>> res;
    while (k > 0) {
      vector<int> point = minHeap.top();
      minHeap.pop();
      res.push_back({point[1], point[2]});
      --k;
    }

    return res;
  }

private:
  struct CompareVectors {
    bool operator()(const vector<int> &a, const vector<int> &b) {
      return a[0] > b[0];
    }
  };
};
```

4. Kth Largest Element in an Array

```cpp
class Solution {
public:
  int findKthLargest(vector<int> &nums, int k) {
    priority_queue<int, vector<int>, greater<int>> minHeap;
    for (int num : nums) {
      if (minHeap.size() < k) {
        minHeap.push(num);
        continue;
      }

      if (num > minHeap.top()) {
        minHeap.push(num);
        minHeap.pop();
      }
    }

    return minHeap.top();
  }
};
```

5. Task Scheduler

```cpp
class Solution {
public:
  int leastInterval(vector<char> &tasks, int n) {
    vector<int> counter(26, 0);
    for (char &task : tasks) {
      ++counter[task - 'A'];
    }
    priority_queue<int> freqAvail;
    for (int c : counter) {
      if (c > 0) {
        freqAvail.push(c);
      }
    }

    queue<pair<int, int>> processing; // {freq, time_avail_again}
    int time = 0;
    while (!freqAvail.empty() || !processing.empty()) {
      ++time;

      if (!freqAvail.empty()) {
        // we deduct 1 because we are processing the task at this unit of time
        int freq = freqAvail.top() - 1;
        freqAvail.pop();

        if (freq > 0) {
          processing.push({freq, time + n});
        }
      }

      // check if the processing queue is not empty and the task at the front
      // is available to be kept in the freqAvail max heap
      if (!processing.empty() && processing.front().second == time) {
        freqAvail.push(processing.front().first); // push the remaining frequency
        processing.pop();
      }
    }

    return time;
  }
};
```

6. Design Twitter

```cpp
class Twitter {
public:
  Twitter() {}

  void postTweet(int userId, int tweetId) {
    tweets.push_back({userId, tweetId});
  }

  vector<int> getNewsFeed(int userId) {
    unordered_set<int> followings = following[userId];
    int k = 10;
    vector<int> res;
    for (int i = tweets.size() - 1; i >= 0; --i) {
      if (tweets[i].first == userId || followings.contains(tweets[i].first)) {
        res.push_back(tweets[i].second);
        --k;
      }

      if (k <= 0) {
        break;
      }
    }

    return res;
  }

  void follow(int followerId, int followeeId) {
    following[followerId].insert(followeeId);
  }

  void unfollow(int followerId, int followeeId) {
    if (following[followerId].contains(followeeId)) {
      following[followerId].erase(followeeId);
    }
  }

private:
  vector<pair<int, int>> tweets;
  unordered_map<int, unordered_set<int>> following;
};
```
