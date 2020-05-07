package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
 *
 * postTweet(userId, tweetId): Compose a new tweet.
 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 * follow(followerId, followeeId): Follower follows a followee.
 * unfollow(followerId, followeeId): Follower unfollows a followee.
 * Example:
 *
 * Twitter twitter = new Twitter();
 *
 * // User 1 posts a new tweet (id = 5).
 * twitter.postTweet(1, 5);
 *
 * // User 1's news feed should return a list with 1 tweet id -> [5].
 * twitter.getNewsFeed(1);
 *
 * // User 1 follows user 2.
 * twitter.follow(1, 2);
 *
 * // User 2 posts a new tweet (id = 6).
 * twitter.postTweet(2, 6);
 *
 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 * // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.getNewsFeed(1);
 *
 * // User 1 unfollows user 2.
 * twitter.unfollow(1, 2);
 *
 * // User 1's news feed should return a list with 1 tweet id -> [5],
 * // since user 1 is no longer following user 2.
 * twitter.getNewsFeed(1);
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/design-twitter
 */
public class Twitter {

    Map<Integer, Set<Integer>> followerAndFollowee = new HashMap<>();
    Map<Integer, List<Tweet>> userAndTweet = new HashMap<>();
    int count = 0;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {

    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        List<Tweet> tweets = userAndTweet.get(Integer.valueOf(userId));
        if (tweets == null) {
            tweets = new ArrayList<>();
            userAndTweet.put(Integer.valueOf(userId), tweets);
        }
        tweets.add(new Tweet(count++, tweetId));
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> followees = followerAndFollowee.get(Integer.valueOf(userId));
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Tweet> queue = new PriorityQueue<>(Comparator.comparing(Tweet::getIndex).reversed());
        if ((followees == null || followees.isEmpty()) && (userAndTweet.get(Integer.valueOf(userId)) == null
            || userAndTweet.get(Integer.valueOf(userId)).isEmpty())) {
            return new ArrayList<>();
        } else if ((followees == null || followees.isEmpty())) {
            List<Tweet> tweets = userAndTweet.get(Integer.valueOf(userId));
            for (int i = tweets.size() - 1; i >= (tweets.size() <= 10 ? 0 : (tweets.size() - 10)); i--) {
                queue.add(tweets.get(i));
                for (int j = 0; j < 10; j++) {
                    if (queue.peek() != null) {
                        result.add(queue.poll().getTweetId());
                    } else {
                        break;
                    }
                }
            }
        } else {
            List<Integer> validUsers = new ArrayList<>(followees);
            validUsers.add(Integer.valueOf(userId));
            for (int i = 0; i < validUsers.size(); i++) {
                List<Tweet> tweets = userAndTweet.get(Integer.valueOf(validUsers.get(i)));
                if (tweets == null || tweets.isEmpty()) {
                    continue;
                }
                for (int j = tweets.size() - 1; j >= (tweets.size() <= 10 ? 0 : (tweets.size() - 10)); j--) {
                    queue.add(tweets.get(j));
                }
            }
            for (int i = 0; i < 10; i++) {
                if (queue.peek() != null) {
                    result.add(queue.poll().getTweetId());
                } else {
                    break;
                }
            }
        }
        return result;
    }

    public static class Tweet {

        int index;
        int tweetId;

        public Tweet(int index, int tweetId) {
            this.index = index;
            this.tweetId = tweetId;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getTweetId() {
            return tweetId;
        }

        public void setTweetId(int tweetId) {
            this.tweetId = tweetId;
        }
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        if (followerAndFollowee.get(followerId) == null) {
            followerAndFollowee.put(followerId, new HashSet<>());
        }
        followerAndFollowee.get(followerId).add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followerAndFollowee.get(followerId) == null || followerAndFollowee.get(followerId).isEmpty()) {
            return;
        }
        followerAndFollowee.get(followerId).remove(Integer.valueOf(followeeId));
    }

    public static void main(String[] args) {
        try {
            /**
             *
             ["Twitter","postTweet","getNewsFeed","follow","postTweet","getNewsFeed","unfollow","getNewsFeed"]
             [[],[1,5],[1],[1,2],[2,6],[1],[1,2],[1]]
             */
            Twitter twitter = new Twitter();
            // User 1 follows user 2.
            //            twitter.follow(1, 2);

            // User 2 posts a new tweet (id = 6).
            twitter.postTweet(1, 5);

            // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
            // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
            twitter.getNewsFeed(1);

            twitter.follow(1, 2);

            twitter.postTweet(2, 6);

            twitter.getNewsFeed(1);

            // User 1 unfollows user 2.
            twitter.unfollow(1, 2);

            // User 1's news feed should return a list with 1 tweet id -> [5],
            // since user 1 is no longer following user 2.
            twitter.getNewsFeed(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
