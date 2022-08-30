package com.codepath.apps.restclienttemplate;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    ImageView image;
    TextView name;
    TextView userName;
    TextView description;
    ImageView media;
    TextView comment;
    TextView reply;
    TextView heart;
    TextView retweet;
    TextView favorite;
    TextView date;
    TextView heart_color;
    TextView  ic_rep_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        userName = findViewById(R.id.userName);
        description = findViewById(R.id.description);
        media = findViewById(R.id.media);
        comment = findViewById(R.id.ic_comment);
        reply = findViewById(R.id.reply);
        heart = findViewById(R.id.ic_heart);
        retweet = findViewById(R.id.ic_retweet);
        favorite = findViewById(R.id.ic_Favorite);
        date = findViewById(R.id.date);
        heart_color=findViewById(R.id.heart_color);
        ic_rep_change=findViewById(R.id.ic_rep_change);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweets"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_btn);
        getSupportActionBar().setIcon(R.drawable.logo_twitter);
        getSupportActionBar().setTitle("");
        // Make sure the toolbar exists in the activity and is not null

        name.setText(tweet.getUser().getName());
        userName.setText(tweet.getUser().getScreenName());
        description.setText(tweet.getBody());
        date.setText(tweet.getFormattedTimestamp(tweet.createdAt));
        favorite.setText(tweet.getFavorite_count());
        retweet.setText(tweet.getRetweet_count());
//        comment.setText(tweet.getReplyUser());
//        reply.setText(tweet.getRetweets());
//        heart.setText(tweet.getFavorite());

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





        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                .transform(new CircleCrop())
                .into(image);


        if(!tweet.medias.getMediaUrl().isEmpty()) {
            media.setVisibility(VISIBLE);
            Glide.with(this)
                    .load(tweet.medias.getMediaUrl())
                    .transform(new RoundedCorners(50))
                    .into(media);
        }

        else {
            media.setVisibility(GONE);
        }
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tweet.favorite++;
                heart.setVisibility(View.INVISIBLE);
                heart_color.setVisibility(View.VISIBLE);
                heart_color.setText(tweet.getFavorite_count());
                tweet.isFavorited=true;
            }
        });

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
                tweet.favorite_count --;
                reply.setVisibility(View.VISIBLE);
                ic_rep_change.setVisibility(View.INVISIBLE);
                reply.setText(tweet.getFavorite_count());
                tweet.isFavorited=false;
            }
        });


    }
}