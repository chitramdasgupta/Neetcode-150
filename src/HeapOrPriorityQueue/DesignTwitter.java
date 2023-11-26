package HeapOrPriorityQueue;

import java.util.*;

// Time - O(n)
// Space - O(n)
public class DesignTwitter {
    private final Map<Integer, Set<Integer>> followMap;
    private final Map<Integer, List<int[]>> tweetMap;
    private int count;

    public DesignTwitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    // Time - O(1)
    public void postTweet(int userId, int tweetId) {
        if (!tweetMap.containsKey(userId)) {
            tweetMap.put(userId, new ArrayList<>());
        }
        tweetMap.get(userId).add(new int[]{count, tweetId});
        count += 1;
    }

    // The idea: We iterate over all the followees of the given user, and we
    // add the most recent tweet made by them (the followees) in the max heap
    // The max heap sorts the details by the count (or it returns the most
    // recent at each poll). We then see which tweet among the last tweets of
    // the followees was the most recent; we add that tweet to the result, and
    // also add the previous tweet by that followee (if there is indeed a tweet).
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                Integer.compare(b[0], a[0])
        );

        // We add the user itself to the followMap
        if (!followMap.containsKey(userId)) {
            followMap.put(userId, new HashSet<>());
        }

        followMap.get(userId).add(userId);
        // We iterate over all the followees and add their most recent
        // tweets to the priority queue
        for (int followee : followMap.get(userId)) {
            if (tweetMap.containsKey(followee)) {
                int idx = tweetMap.get(followee).size() - 1;
                int[] tweet = tweetMap.get(followee).get(idx);
                pq.add(new int[]{tweet[0], tweet[1], followee, idx - 1});
            }
        }

        // We now try to build up our answer until our priority queue is empty,
        // or we have already added 10 tweets to the result
        while (!pq.isEmpty() && res.size() < 10) {
            // Add the most recent tweet to the result
            int[] details = pq.poll();
            res.add(details[1]);
            // Add the previous tweet to the tweet we just added to the result.
            // The previous tweet will be added only if there is exists a previous
            // tweet.
            int idx = details[details.length - 1];
            if (idx >= 0) {
                int[] tweet = tweetMap.get(details[2]).get(idx);
                pq.add(new int[]{tweet[0], tweet[1], details[2], idx - 1});
            }
        }

        return res;
    }

    // Time - O(1)
    public void follow(int followerId, int followeeId) {
        if (!followMap.containsKey(followerId)) {
            followMap.put(followerId, new HashSet<Integer>());
        }
        followMap.get(followerId).add(followeeId);
    }

    // Time - O(1)
    public void unfollow(int followerId, int followeeId) {
        if (!followMap.containsKey(followerId) || !followMap.get(followerId).contains(followeeId)) {
            return;
        }
        followMap.get(followerId).remove(followeeId);
    }
}
