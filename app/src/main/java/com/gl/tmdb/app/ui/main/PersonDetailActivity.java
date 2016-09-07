package com.gl.tmdb.app.ui.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gl.tmdb.R;
import com.gl.tmdb.app.base.BaseActivity;
import com.gl.tmdb.app.ui.adapters.ThreeItemRecyclerViewItem;
import com.gl.tmdb.content.ApiServices;
import com.gl.tmdb.content.model.MediaListType;
import com.gl.tmdb.content.model.MovieItem;
import com.gl.tmdb.content.model.PersonFull;
import com.gl.tmdb.content.model.PersonItem;
import com.gl.tmdb.content.network.responses.PagedResponse;
import com.gl.tmdb.content.network.services.PeopleService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;

public class PersonDetailActivity extends BaseActivity {

    public static final String PERSON_ITEM_KEY = "person_item";

    ImageView headerImageView;

    TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        setTitle("Person detail");
        PersonItem personItem = (PersonItem) getIntent().getSerializableExtra(PERSON_ITEM_KEY);
        if (personItem == null) {
            throw new RuntimeException("missing movie item");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoTextView = (TextView) findViewById(R.id.infoTextView);
        headerImageView = (ImageView) findViewById(R.id.headerImageView);

        System.out.println("movie item: " + personItem);
        System.out.println("infoTextView: " + infoTextView);

        infoTextView.setText(personItem.getName() + "\nPopularity: " + personItem.getPopularity() + "\n");
        Glide.with(headerImageView.getContext())
                .load("http://image.tmdb.org/t/p/w500" + personItem.getProfilePath())
                .centerCrop()
                .into(headerImageView);

        PeopleService api3 = ApiServices.getPeopleService();
        Call<PersonFull> en3 = api3.person(personItem.getId());
        en3.enqueue(new Callback<PersonFull>() {
            @Override
            public void onResponse(Call<PersonFull> call, Response<PersonFull> response) {
                System.out.println("retrofit response size: " + response.body());
                System.out.println("retrofit response item 0: " + response.body());
                infoTextView.setText(infoTextView.getText() + "\n" + response.body().getBiography());
            }

            @Override
            public void onFailure(Call<PersonFull> call, Throwable t) {
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
