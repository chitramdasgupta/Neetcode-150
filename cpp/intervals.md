NOTE: In some problems, [1, 2] and [2, 3] are overlapping and in others they
are not. In problems 4 and 5, they are not overlapping.

1. Meeting Rooms

```cpp
class Solution {
public:
  bool canAttendMeetings(vector<Interval> &intervals) {
    for (int i = 1; i < intervals.size(); ++i) {
      if (intervals[i - 1][1] >= intervals[i][0]) {
        return false;
      }
    }

    return true;
  }
};
```

2. Insert Interval ⭐

```cpp
class Solution {
public:
  vector<vector<int>> insert(vector<vector<int>> &intervals,
                             vector<int> &newInterval) {
    vector<vector<int>> res;
    for (vector<int> curr : intervals) {
      if (newInterval.empty() || curr[1] < newInterval[0]) {
        res.push_back(curr);
      } else if (curr[0] > newInterval[1]) {
        res.push_back(newInterval);
        res.push_back(curr);

        newInterval.clear();
      } else {
        // there is an overlap between the current interval and the newInterval
        newInterval[0] = min(newInterval[0], curr[0]);
        newInterval[1] = max(newInterval[1], curr[1]);
      }
    }

    if (!newInterval.empty()) {
      res.push_back(newInterval);
    }

    return res;
  }
};
```

3. Mege Intervals

```cpp
class Solution {
public:
  vector<vector<int>> merge(vector<vector<int>> &intervals) {
    sort(intervals.begin(), intervals.end());

    vector<vector<int>> res;
    for (int i = 0; i < intervals.size() - 1; ++i) {
      if (intervals[i][1] < intervals[i + 1][0]) {
        res.push_back(intervals[i]);
      } else {
        intervals[i + 1][0] = min(intervals[i][0], intervals[i + 1][0]);
        intervals[i + 1][1] = max(intervals[i][1], intervals[i + 1][1]);
      }
    }

    res.push_back(intervals[intervals.size() - 1]);

    return res;
  }
};
```

4. Non Overlapping Intervals

```cpp
class Solution {
public:
  int eraseOverlapIntervals(vector<vector<int>> &intervals) {
    sort(intervals.begin(), intervals.end());

    int res = 0;
    for (int i = 0; i < intervals.size() - 1; ++i) {
      if (intervals[i][1] > intervals[i + 1][0]) {
        ++res;

        if (intervals[i + 1][1] > intervals[i][1]) {
          intervals[i + 1] = intervals[i];
        }
      }
    }

    return res;
  }
};
```

5. Meeting Rooms II

```cpp
class Solution {
public:
  int minMeetingRooms(vector<Interval> &intervals) {
    sort(intervals.begin(), intervals.end());

    priority_queue<int, vector<int>, greater<int>> pq;
    pq.push(intervals[i].end);

    for (int i = 1; i < intervals.size(); ++i) {
      if (intervals[i][0] >= pq.top()) {
        pq.pop();
      }

      pq.add(intervals[i].end)
    }

    return pq.size();
  }
};
```
