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
