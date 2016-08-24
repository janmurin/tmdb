package com.gl.tmdb.content.model;

import android.database.Cursor;

import com.gl.tmdb.content.local.DataContract;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents basic TV show information as returned in a list of TV shows.
 */
public class TvShowItem extends TvShowBase {

    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<>();

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public static List<TvShowItem> fromCursor(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            List<TvShowItem> tvShowItems = new ArrayList<>(cursor.getCount());
            do {
                TvShowItem item = new TvShowItem();
                item.id = cursor.getInt(cursor.getColumnIndex(DataContract.TvShowItems.ID));
                item.name = cursor.getString(cursor.getColumnIndex(DataContract.TvShowItems.NAME));
                item.originalName = cursor.getString(cursor.getColumnIndex(DataContract.TvShowItems.ORIGINAL_NAME));
                item.originalLanguage = cursor.getString(cursor.getColumnIndex(DataContract.TvShowItems.ORIGINAL_LANGUAGE));
                item.overview = cursor.getString(cursor.getColumnIndex(DataContract.TvShowItems.OVERVIEW));
                item.posterPath = cursor.getString(cursor.getColumnIndex(DataContract.TvShowItems.POSTER_PATH));
                //TODO
                tvShowItems.add(item);
            } while (cursor.moveToNext());
            return tvShowItems;
        }
        return null;
    }
}
