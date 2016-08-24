package com.gl.tmdb.content.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gl.tmdb.utils.DbUtils;

/**
 * Database open helper.
 */
public final class DbOpenHelper extends SQLiteOpenHelper {

    public static final String TAG = "DbOpenHelper";

    public static final String DB_NAME = "droidmdb_db";
    public static final int DB_VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        db.execSQL(TableQueries.createMovieItemsTable());
        db.execSQL(TableQueries.createTvShowItemsTable());
        db.execSQL(TableQueries.createPersonItemsTable());
        db.execSQL(TableQueries.createItemListsTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("onUpgrade(old: %s, new: %s)", oldVersion, newVersion));
        db.execSQL(DbUtils.dropTable(DataContract.Tables.MOVIE_ITEMS));
        db.execSQL(DbUtils.dropTable(DataContract.Tables.TV_SHOW_ITEMS));
        db.execSQL(DbUtils.dropTable(DataContract.Tables.PERSON_ITEMS));
        db.execSQL(DbUtils.dropTable(DataContract.Tables.ITEM_LISTS));
        onCreate(db);
    }
}
