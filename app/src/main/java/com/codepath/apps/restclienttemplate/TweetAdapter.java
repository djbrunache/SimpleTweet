package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;
    // Pass in the context and the list of tweets
    public TweetAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        TweetAdapter.ViewHolder viewHolder = new TweetAdapter.ViewHolder(view, listener);
        return viewHolder;
    }

    // Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data at position
         Tweet tweet = tweets.get(position);
        // Bind the tweet with viewholder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // clean all elements of the recyclerview
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    // public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {this.listener = listener; }

    public void setOnItemClickListener(OnItemClickListener listener) { this.listener = listener;
    }

    public interface OnItemClickListener{
        void OnItemClick(View itemView, int position);
    }
    private OnItemClickListener listener;

    // define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView date;
        ImageView media;
        TextView comment;
        TextView reply;
        TextView heart;
        TextView heart_color;
        TextView  ic_rep_change;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener clickListener) {
            super(itemView);

            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            date = itemView.findViewById(R.id.date);
            media = itemView.findViewById(R.id.media);
            comment = itemView.findViewById(R.id.ic_comment);
            reply = itemView.findViewById(R.id.reply);
            heart = itemView.findViewById(R.id.ic_heart);
            heart_color = itemView.findViewById(R.id.heart_color);
            ic_rep_change = itemView.findViewById(R.id.ic_rep_change);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    clickListener.OnItemClick(itemView, getAdapterPosition());
                }
            });
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            date.setText(tweet.getFormattedTimestamp(tweet.createdAt));
            if(tweet.isFavorited) {
                heart.setVisibility(View.INVISIBLE);
                heart_color.setVisibility(View.VISIBLE);

            }
            else {
                heart.setVisibility(View.VISIBLE);
                heart_color.setVisibility(View.INVISIBLE);
            }


            if(tweet.isFavorited) {
                reply.setVisibility(View.INVISIBLE);
                ic_rep_change.setVisibility(View.VISIBLE);

            }
            else {
                reply.setVisibility(View.VISIBLE);
                ic_rep_change.setVisibility(View.INVISIBLE);
            }


            Glide.with(context)
                    .load(tweet.user.profileImageUrl)
                    .transform(new CircleCrop())
                    .into(ivProfileImage);

            if (!tweet.medias.getMediaUrl().isEmpty()) {
                media.setVisibility(itemView.VISIBLE);
                Glide.with(context)
                        .load(tweet.medias.getMediaUrl())
                        .transform(new RoundedCorners(50))
                        .into(media);
            }
            else{
                media.setVisibility(itemView.GONE);
            }


            reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tweet.favorite_count++;
                    reply.setVisibility(View.INVISIBLE);
                    ic_rep_change.setVisibility(View.VISIBLE);
                    ic_rep_change.setText(tweet.getFavorite_count());
                    tweet.isFavorited=true;
                }
            });



            heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tweet.favorite_count++;
                    heart.setVisibility(View.INVISIBLE);
                    heart_color.setVisibility(View.VISIBLE);
                    heart_color.setText(tweet.getFavorite_count());
                    tweet.isFavorited=true;
                }
            });



            heart_color.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tweet.favorite_count--;
                    heart.setVisibility(View.VISIBLE);
                    heart_color.setVisibility(View.INVISIBLE);
                    heart.setText(tweet.getFavorite_count());
                    tweet.isFavorited=false;
                }
            });
            ic_rep_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tweet.favorite_count--;
                    reply.setVisibility(View.VISIBLE);
                    ic_rep_change.setVisibility(View.INVISIBLE);
                    reply.setText(tweet.getFavorite_count());
                    tweet.isFavorited=false;
                }
            });

        }

    }
}
