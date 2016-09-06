package com.gl.tmdb.content.model;

import android.database.Cursor;

import com.gl.tmdb.content.local.DataContract;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents basic Movie information as returned in a list of movies.
 */
public class MovieItem extends MovieBase implements Serializable{

    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<>();

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public static List<MovieItem> fromCursor(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            List<MovieItem> movieItems = new ArrayList<>(cursor.getCount());
            do {
                MovieItem item = new MovieItem();
                item.id = cursor.getInt(cursor.getColumnIndex(DataContract.MovieItems.ID));
                item.title = cursor.getString(cursor.getColumnIndex(DataContract.MovieItems.TITLE));
                item.originalTitle = cursor.getString(cursor.getColumnIndex(DataContract.MovieItems.ORIGINAL_TITLE));
                item.originalLanguage = cursor.getString(cursor.getColumnIndex(DataContract.MovieItems.ORIGINAL_LANGUAGE));
                item.overview = cursor.getString(cursor.getColumnIndex(DataContract.MovieItems.OVERVIEW));
                item.posterPath = cursor.getString(cursor.getColumnIndex(DataContract.MovieItems.POSTER_PATH));
                //TODO
                movieItems.add(item);
            } while (cursor.moveToNext());
            return movieItems;
        }
        return null;
    }
}
