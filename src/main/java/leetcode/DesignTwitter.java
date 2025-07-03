package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

class DesignTwitter {
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
//        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
//        twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
//        twitter.follow(1, 2);    // User 1 follows user 2.
//        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
//        twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
//        twitter.unfollow(1, 2);  // User 1 unfollows user 2.
//        twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.

        twitter.postTweet(1, 5);
        twitter.postTweet(1, 3);
        List<Integer> newsFeed = twitter.getNewsFeed(1);
        System.out.println(newsFeed);
    }
}

class Twitter {

    Map<Integer, List<Tweet>> userToTweets = new HashMap<>();
    Map<Integer, Set<Integer>> userToFollowers = new HashMap<>();

    public Twitter() {}

    public void postTweet(int userId, int tweetId) {
        var tweet = new Tweet(System.nanoTime(), tweetId);
        userToTweets.computeIfAbsent(userId, k -> new ArrayList<>())
                .add(tweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        var userTweets = userToTweets.getOrDefault(userId, new ArrayList<>());
        var followingTweets = userToFollowers
                .getOrDefault(userId, new HashSet<>())
                .stream()
                .flatMap(user -> userToTweets.getOrDefault(user, new ArrayList<>()).stream())
                .toList();

        return Stream.of(userTweets, followingTweets)
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Tweet::timestamp).reversed())
                .limit(10)
                .peek(System.out::println)
                .map(Tweet::tweetId)
                .toList();
    }

    public void follow(int followerId, int followeeId) {
        userToFollowers.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        userToFollowers.getOrDefault(followerId, new HashSet<>())
                .remove(Integer.valueOf(followeeId));
    }
}

record Tweet(long timestamp, int tweetId) {}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */