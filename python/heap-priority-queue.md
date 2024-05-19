`heapify()` takes `O(n)` time.

`heappush()`  and `heappop()` take `O(log n)` time. Thus, performing `heappush()`
`n` times will take `O(n log (n))` time.

# 1. Kth Largest Element in a Stream

```python
class KthLargest:
  def __init__(self, k: int, nums: List[int]):
    self.minHeap = nums
    self.k = k

    heapq.heapify(self.minHeap)

    while len(self.minHeap) > k:
      heapq.heappop(self.minHeap)

  def add(self, val: int) -> int:
    if len(self.minHeap) < self.k:
      heapq.heappush(self.minHeap, val)
      return self.minHeap[0]

    if val <= self.minHeap[0]:
      return self.minHeap[0]

    heapq.heappop(self.minHeap)
    heapq.heappush(self.minHeap, val)
    return self.minHeap[0]
```

The above code can be made concise like so:

```python
class KthLargest:
  def __init__(self, k: int, nums: List[int]):
    self.minHeap, self.k = nums, k

    heapq.heapify(self.minHeap)

    while len(self.minHeap) > k:
      heapq.heappop(self.minHeap)

  def add(self, val: int) -> int:
    if len(self.minHeap) < self.k:
      heapq.heappush(self.minHeap, val)
    elif val > self.minHeap[0]:
      heapq.heapreplace(self.minHeap, val)

    return self.minHeap[0]
```

# 2. Last Stone Weight

```python
class Solution:
  def lastStoneWeight(self, stones: List[int]) -> int:
    max_heap = [-stone for stone in stones]
    heapq.heapify(max_heap) # max heap

    while len(max_heap) >= 2:
      y, x = -heapq.heappop(max_heap), -heapq.heappop(max_heap)

      if x != y:
        heapq.heappush(max_heap, -(y - x))

    return -max_heap[0] if len(max_heap) == 1 else 0
```

# 3. K Closest Points to Origin

```python
class Solution:
  def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
    maxHeap = []
    heapq.heapify(maxHeap)

    for point in points:
      distance = -abs(point[0] ** 2 + point[1] ** 2)
      heapq.heappush(maxHeap, [distance, point[0], point[1]])

    while len(maxHeap) > k:
      heapq.heappop(maxHeap)

    return [[point[1], point[2]] for point in maxHeap]
```

# 4. Kth Largest Element In An Array

```python
class Solution:
  def findKthLargest(self, nums: List[int], k: int) -> int:
    minHeap = []
    heapq.heapify(minHeap)

    for num in nums:
      if len(minHeap) < k:
        heapq.heappush(minHeap, num)
        continue

      if num > minHeap[0]:
        heapq.heapreplace(minHeap, num)

    return minHeap[0]
```

# 5. Task Scheduler

We make a max heap of all the task frequncies, and also maintain a waiting queue
of the (frequencies remaining, time next available).

We continue with the loop (incrementing the time at each iteration) until
both the task frequencies and the waiting queue is empty. After we process the
most frequent task, we send it in the waiting queue (and it will be transferred
from the queue to the tasks at the time it becomes available again).

```python
class Solution:
  def leastInterval(self, tasks: List[str], n: int) -> int:
    task_counts = [-freq for freq in Counter(tasks).values()]
    heapq.heapify(task_counts)  # max heap of the remaining frequency

    waiting_tasks = deque()  # Queue of (frequency remaining, next available time)
    time = 0
    while task_counts or waiting_tasks:
      time += 1

      if task_counts:
        # Process the most frequent task
        freq = -heapq.heappop(task_counts) - 1
        if freq > 0:
            waiting_tasks.append((freq, time + n))

      if waiting_tasks and waiting_tasks[0][1] == time:
        freq, _ = waiting_tasks.popleft()
        heapq.heappush(task_counts, -freq)

    return time
```

# 6. Design Twitter

We maintain two data structures: a list of tweets, and a dictionary for followers,
which maps a userId to a set of other userIds.

```python
class Twitter:
  def __init__(self):
    self.tweets = [] # A list of (userId, tweetId)
    self.followMap = defaultdict(set) # A hash map of userId : set(userId)

  def postTweet(self, userId: int, tweetId: int) -> None:
    self.tweets.append((userId, tweetId))

  def getNewsFeed(self, userId: int) -> List[int]:
    res = []
    for tweet in reversed(self.tweets):
      if tweet[0] == userId or tweet[0] in self.followMap[userId]:
        res.append(tweet[1])

      if len(res) == 10: break

    return res

  def follow(self, followerId: int, followeeId: int) -> None:
    self.followMap[followerId].add(followeeId)

  def unfollow(self, followerId: int, followeeId: int) -> None:
    if followeeId in self.followMap[followerId]:
      self.followMap[followerId].remove(followeeId)
```
