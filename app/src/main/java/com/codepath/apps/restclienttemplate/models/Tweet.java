package com.codepath.apps.restclienttemplate.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"))
public class Tweet {
    @ColumnInfo
    public String body;

    @ColumnInfo
    public String createdAt;

    @ColumnInfo
    @PrimaryKey
    public long id;

    @Ignore
    public User user;

    @Ignore
    public User retweet;

    @ColumnInfo
    public int retweets;

    @ColumnInfo
    public int favorite;

    @ColumnInfo
    public String replyUser;

    @Ignore
    public Media medias;

    @Ignore
    public Url url;

    @ColumnInfo
    public long userId;

    @ColumnInfo
    public int favorite_count;

    @ColumnInfo
    public int retweet_count;

    @ColumnInfo
    public boolean isFavorited;

    @ColumnInfo
    public boolean isRetweet;

    public Tweet() {}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        User user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.user = user;
        tweet.userId = user.id;
        tweet.medias = Media.fromJson(jsonObject.getJSONObject("entities")) ;
        tweet.retweet_count= jsonObject.getInt("retweet_count");
        tweet.retweets = jsonObject.getInt("retweet_count");
        tweet.favorite = jsonObject.getInt("favorite_count");
        tweet.favorite_count = jsonObject.getInt("favorite_count");
        tweet.replyUser = jsonObject.getString("in_reply_to_user_id");
        tweet.isRetweet = jsonObject.getBoolean("retweeted");
        tweet.isFavorited = jsonObject.getBoolean("favorited");
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

    public String getFormattedTimestamp(String createdAt) {
        return TimeFormatter.getTimeDifference(createdAt);
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }


    public String getFavorite_count() {
        return favorite_count + "     Favorites";
    }

    public String getRetweet_count() {
        return   retweet_count + "      Retweets";
    }

    public String getReplyUser() {
        return replyUser;
    }

    public int getRetweets() {
        return retweets;
    }

    public int getFavorite() {
        return favorite;
    }

    public Media getMedias() {
        return medias;
    }

    public Url getUrl() {
        return url;
    }

    public User getRetweet() {
        return retweet;
    }

    public boolean isFavorited() {
        return isFavorited;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setMedia(Media media) {
        this.medias = media;
    }
}
