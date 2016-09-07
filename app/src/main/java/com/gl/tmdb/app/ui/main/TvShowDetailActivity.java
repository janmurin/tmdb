package com.gl.tmdb.app.ui.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gl.tmdb.R;
import com.gl.tmdb.app.base.BaseActivity;
import com.gl.tmdb.content.ApiServices;
import com.gl.tmdb.content.model.PersonFull;
import com.gl.tmdb.content.model.TvShowFull;
import com.gl.tmdb.content.model.TvShowItem;
import com.gl.tmdb.content.network.services.TvShowService;

import java.io.InputStream;
import java.util.Scanner;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;

public class TvShowDetailActivity extends BaseActivity {

    public static final String TVSHOW_ITEM_KEY = "tvshow_item";

    ImageView headerImageView;

    TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        setTitle("TvShow detail");
        TvShowItem tvshowItem = (TvShowItem) getIntent().getSerializableExtra(TVSHOW_ITEM_KEY);
        if (tvshowItem == null) {
            throw new RuntimeException("missing movie item");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoTextView = (TextView) findViewById(R.id.infoTextView);
        headerImageView = (ImageView) findViewById(R.id.headerImageView);

        System.out.println("movie item: " + tvshowItem);
        System.out.println("infoTextView: " + infoTextView);

        infoTextView.setText(tvshowItem.getName() + "\nPopularity: " + tvshowItem.getPopularity() + "\n");
        Glide.with(headerImageView.getContext())
                .load("http://image.tmdb.org/t/p/w500" + tvshowItem.getPosterPath())
                .centerCrop()
                .into(headerImageView);

        TvShowService api3 = ApiServices.getTvShowService();
        Call<TvShowFull> en3 = api3.tvShow(tvshowItem.getId(),"EN");
        en3.enqueue(new Callback<TvShowFull>() {
            @Override
            public void onResponse(Call<TvShowFull> call, Response<TvShowFull> response) {
                System.out.println("retrofit response size: " + response.body());
                System.out.println("retrofit response item 0: " + response.body());
                infoTextView.setText(infoTextView.getText() + "\n" + response.body().getOverview());
            }

            @Override
            public void onFailure(Call<TvShowFull> call, Throwable t) {
                System.out.println("retrofit error: " + t.getLocalizedMessage());
                if (t instanceof HttpException) {
                    ResponseBody body = ((HttpException) t).response().errorBody();

                    System.out.println("body: " + getStringUTFFromInputStream(body.byteStream()));

                }

            }
        });
    }

    public static String getStringUTFFromInputStream(InputStream is) {
        Scanner scanner = new Scanner(is, "utf-8");
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        return sb.toString();
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
