package com.gl.tmdb.content.local;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.gl.tmdb.content.model.MovieItem;
import com.gl.tmdb.content.model.PersonItem;
import com.gl.tmdb.content.model.TvShowItem;

import java.util.List;

import static com.gl.tmdb.content.local.DataContract.*;

/**
 * Created by zavadpe on 18.3.2016.
 */
public class DbProviderHelper {

    public static void insertMovieItems(Context context, List<MovieItem> movieItems) {
        ContentResolver cr = context.getContentResolver();
        ContentValues cv;
        ContentValues[] values = new ContentValues[movieItems.size()];
        for (int i = 0; i < movieItems.size(); i++) {
            cv = new ContentValues();
            MovieItem movieItem = movieItems.get(i);
            cv.put(MovieItems.ID, movieItem.getId());
            cv.put(MovieItems.TITLE, movieItem.getTitle());
            cv.put(MovieItems.ORIGINAL_TITLE, movieItem.getOriginalTitle());
            cv.put(MovieItems.ORIGINAL_LANGUAGE, movieItem.getOriginalLanguage());
            cv.put(MovieItems.OVERVIEW, movieItem.getOverview());
            cv.put(MovieItems.POSTER_PATH, movieItem.getPosterPath());
            cv.put(MovieItems.RELEASE_DATE, movieItem.getReleaseDate());
            cv.put(MovieItems.POPULARITY, movieItem.getPopularity());
            cv.put(MovieItems.VOTE_COUNT, movieItem.getVoteCount());
            cv.put(MovieItems.VOTE_AVERAGE, movieItem.getVoteAverage());
            cv.put(MovieItems.VIDEO, movieItem.getVideo());
            cv.put(MovieItems.ADULT, movieItem.getAdult());
            cv.put(MovieItems.BACKDROP_PATH, movieItem.getBackdropPath());
            //cv.put(MovieItems.GENRE_IDS, movieItem.getGenreIds().toString());
            values[i] = cv;
        }
        cr.bulkInsert(DataContract.MovieItems.CONTENT_URI, values);
    }

    public static void insertTvShowItems(Context context, List<TvShowItem> tvShowItems) {
        ContentResolver cr = context.getContentResolver();
        ContentValues cv;
        ContentValues[] values = new ContentValues[tvShowItems.size()];
        for (int i = 0; i < tvShowItems.size(); i++) {
            cv = new ContentValues();
            TvShowItem tvShowItem = tvShowItems.get(i);
            cv.put(TvShowItems.ID, tvShowItem.getId());
            cv.put(TvShowItems.NAME, tvShowItem.getName());
            cv.put(TvShowItems.ORIGINAL_NAME, tvShowItem.getOriginalName());
            cv.put(TvShowItems.ORIGINAL_LANGUAGE, tvShowItem.getOriginalLanguage());
            cv.put(TvShowItems.OVERVIEW, tvShowItem.getOverview());
            cv.put(TvShowItems.POSTER_PATH, tvShowItem.getPosterPath());
            cv.put(TvShowItems.FIRST_AIR_DATE, tvShowItem.getFirstAirDate());
            cv.put(TvShowItems.POPULARITY, tvShowItem.getPopularity());
            cv.put(TvShowItems.VOTE_COUNT, tvShowItem.getVoteCount());
            cv.put(TvShowItems.VOTE_AVERAGE, tvShowItem.getVoteAverage());
            cv.put(TvShowItems.BACKDROP_PATH, tvShowItem.getBackdropPath());
            //cv.put(TvShowItems.ORIGIN_COUNTRY, tvShowItem.getOriginCountry());
            //cv.put(MovieItems.GENRE_IDS, tvShowItem.getGenreIds().toString());
            values[i] = cv;
        }
        cr.bulkInsert(DataContract.TvShowItems.CONTENT_URI, values);
    }

    public static void insertPersonItems(Context context, List<PersonItem> personItems) {
        ContentResolver cr = context.getContentResolver();
        ContentValues cv;
        ContentValues[] values = new ContentValues[personItems.size()];
        for (int i = 0; i < personItems.size(); i++) {
            cv = new ContentValues();
            PersonItem person = personItems.get(i);
            cv.put(PersonItems.ID, person.getId());
            cv.put(PersonItems.NAME, person.getName());
            cv.put(PersonItems.POPULARITY, person.getPopularity());
            cv.put(PersonItems.ADULT, person.getAdult());
            cv.put(PersonItems.PROFILE_PATH, person.getProfilePath());
            //cv.put(PersonItems.KNOWN_FOR, person.getKnownFor());
            values[i] = cv;
        }
        cr.bulkInsert(DataContract.PersonItems.CONTENT_URI, values);
    }

}
