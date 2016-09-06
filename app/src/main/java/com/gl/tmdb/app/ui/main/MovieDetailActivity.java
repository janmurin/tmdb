package com.gl.tmdb.app.ui.main;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gl.tmdb.R;
import com.gl.tmdb.content.model.MovieItem;

import java.io.Serializable;

import butterknife.BindView;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE_ITEM_KEY = "movie_item";

    ImageView headerImageView;

    TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        MovieItem movie_item = (MovieItem) getIntent().getSerializableExtra(MOVIE_ITEM_KEY);
        if (movie_item == null) {
            throw new RuntimeException("missing movie item");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoTextView = (TextView)findViewById(R.id.infoTextView);
        headerImageView = (ImageView)findViewById(R.id.headerImageView);

        System.out.println("movie item: " + movie_item);
        System.out.println("infoTextView: " + infoTextView);

        infoTextView.setText(movie_item.getOriginalTitle() + "\n" + movie_item.getReleaseDate() + "\n" + movie_item.getGenreIds());
        Glide.with(headerImageView.getContext())
                .load("http://image.tmdb.org/t/p/w500" + movie_item.getPosterPath())
                .centerCrop()
                .into(headerImageView);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
