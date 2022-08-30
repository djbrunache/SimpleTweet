package com.codepath.apps.restclienttemplate.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TweetDao {

    @Query("SELECT Tweet.body AS tweet_body, Tweet.createdAt AS tweet_createdAt, Tweet.favorite AS tweet_favorite, Tweet.favorite_count AS tweet_favorite_count, Tweet.isFavorited AS tweet_isFavorited, " +
            "Tweet.isRetweet AS tweet_isRetweet, Tweet.retweets AS tweet_retweets, Tweet.id AS tweet_id, Media.*, User.*" +
            " FROM Media, Tweet INNER JOIN User ON Tweet.userId = User.id ORDER BY Tweet.createdAt DESC LIMIT 5")
    List<TweetWithUser> recentItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(Tweet... tweets);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(Media... media);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(User... users);
}
