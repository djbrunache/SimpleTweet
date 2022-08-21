package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {
    public String body;
    public String createdAt;
    public long id;
    public User user;
    public User retweet;
    public int retweets;
    public int favorite;
    public String replyUser;
    public Media medias;
    public Url url;
    public int favorite_count;
    public int retweet_count;
    public boolean isFavorited;
    public boolean isRetweet;

    public Tweet() {}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
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
