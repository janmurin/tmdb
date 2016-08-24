package com.gl.tmdb.content.local;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import static com.gl.tmdb.content.local.DataContract.AUTHORITY;
import static com.gl.tmdb.content.local.DataContract.Codes;
import static com.gl.tmdb.content.local.DataContract.Tables;
import static com.gl.tmdb.content.local.DataContract.MovieItems;
import static com.gl.tmdb.content.local.DataContract.TvShowItems;
import static com.gl.tmdb.content.local.DataContract.PersonItems;
import static com.gl.tmdb.content.local.DataContract.ItemLists;

/**
 * ContentProvider implementation for storing themoviedb content in database.
 */
public class DbContentProvider extends ContentProvider {

    public static final String TAG = "DbContentProvider";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, Tables.MOVIE_ITEMS, Codes.MOVIE_ITEMS);
        uriMatcher.addURI(AUTHORITY, Tables.MOVIE_ITEMS_ID, Codes.MOVIE_ITEMS_ID);
        uriMatcher.addURI(AUTHORITY, Tables.TV_SHOW_ITEMS, Codes.TV_SHOW_ITEMS);
        uriMatcher.addURI(AUTHORITY, Tables.TV_SHOW_ITEMS_ID, Codes.TV_SHOW_ITEMS_ID);
        uriMatcher.addURI(AUTHORITY, Tables.PERSON_ITEMS, Codes.PERSON_ITEMS);
        uriMatcher.addURI(AUTHORITY, Tables.PERSON_ITEMS_ID, Codes.PERSON_ITEMS_ID);
        uriMatcher.addURI(AUTHORITY, Tables.ITEM_LISTS, Codes.ITEM_LISTS);
    }

    private SQLiteDatabase database;
    private DbOpenHelper dbOpenHelper;

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        dbOpenHelper = new DbOpenHelper(getContext());
        database = dbOpenHelper.getWritableDatabase();
        return database != null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case Codes.MOVIE_ITEMS:
                return MovieItems.CONTENT_TYPE;
            case Codes.MOVIE_ITEMS_ID:
                return MovieItems.CONTENT_TYPE_ITEM;
            case Codes.TV_SHOW_ITEMS:
                return TvShowItems.CONTENT_TYPE;
            case Codes.TV_SHOW_ITEMS_ID:
                return TvShowItems.CONTENT_TYPE_ITEM;
            case Codes.PERSON_ITEMS:
                return PersonItems.CONTENT_TYPE;
            case Codes.PERSON_ITEMS_ID:
                return PersonItems.CONTENT_TYPE_ITEM;
            case Codes.ITEM_LISTS:
                return ItemLists.CONTENT_TYPE;
            default:
                Log.e(TAG, String.format("Trying to resolve unknown Uri: %s", uri));
                return null;
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case Codes.MOVIE_ITEMS:
                queryBuilder.setTables(Tables.MOVIE_ITEMS);
                break;
            case Codes.MOVIE_ITEMS_ID:
                queryBuilder.setTables(Tables.MOVIE_ITEMS);
                queryBuilder.appendWhere(MovieItems.ID + "=" + uri.getLastPathSegment());
                break;
            case Codes.TV_SHOW_ITEMS:
                queryBuilder.setTables(Tables.TV_SHOW_ITEMS);
                break;
            case Codes.TV_SHOW_ITEMS_ID:
                queryBuilder.setTables(Tables.TV_SHOW_ITEMS);
                queryBuilder.appendWhere(TvShowItems.ID + "=" + uri.getLastPathSegment());
                break;
            case Codes.PERSON_ITEMS:
                queryBuilder.setTables(Tables.PERSON_ITEMS);
                break;
            case Codes.PERSON_ITEMS_ID:
                queryBuilder.setTables(Tables.PERSON_ITEMS_ID);
                queryBuilder.appendWhere(PersonItems.ID + "=" + uri.getLastPathSegment());
                break;
            case Codes.ITEM_LISTS:
                queryBuilder.setTables(Tables.ITEM_LISTS);
                break;
            default:
                Log.e(TAG, String.format("Trying to resolve unknown Uri: %s", uri));
                return null;
        }
        if (TextUtils.isEmpty(sortOrder)) {
            sortOrder = BaseColumns._ID;
        }

        Cursor cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        Context context = getContext();
        if (context != null) {
            cursor.setNotificationUri(context.getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        long rowId = 0;
        switch (uriMatcher.match(uri)) {
            case Codes.MOVIE_ITEMS:
                rowId = database.insert(Tables.MOVIE_ITEMS, null, values);
                break;
            case Codes.TV_SHOW_ITEMS:
                rowId = database.insert(Tables.TV_SHOW_ITEMS, null, values);
                break;
            case Codes.PERSON_ITEMS:
                rowId = database.insert(Tables.PERSON_ITEMS, null, values);
                break;
            case Codes.ITEM_LISTS:
                rowId = database.insert(Tables.ITEM_LISTS, null, values);
                break;
            default:
                Log.e(TAG, String.format("Trying to resolve unknown Uri: %s", uri));
                return null;
        }
        Uri newUri = ContentUris.withAppendedId(uri, rowId);
        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(newUri, null);
        }
        return newUri;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        String tableName;
        int numInserted = 0;
        switch (uriMatcher.match(uri)) {
            case Codes.MOVIE_ITEMS:
                tableName = Tables.MOVIE_ITEMS;
                break;
            case Codes.TV_SHOW_ITEMS:
                tableName = Tables.TV_SHOW_ITEMS;
                break;
            case Codes.PERSON_ITEMS:
                tableName = Tables.PERSON_ITEMS;
                break;
            case Codes.ITEM_LISTS:
                tableName = Tables.ITEM_LISTS;
                break;
            default:
                Log.e(TAG, String.format("Trying to resolve unknown Uri: %s", uri));
                return 0;
        }
        Context context = getContext();
        if (context != null) {
            database.beginTransaction();
            try {
                for (ContentValues cv : values) {
                    database.insertOrThrow(tableName, null, cv);
                }
                database.setTransactionSuccessful();
                context.getContentResolver().notifyChange(uri, null);
                numInserted = values.length;
            } catch (SQLException e) {
                Log.w(TAG, "BulkInsert failed!", e);
            } finally {
                database.endTransaction();
            }
        }
        return numInserted;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        int delCount = 0;
        switch (uriMatcher.match(uri)) {
            case Codes.MOVIE_ITEMS:
                delCount = database.delete(Tables.MOVIE_ITEMS, selection, selectionArgs);
                break;
            case Codes.TV_SHOW_ITEMS:
                delCount = database.delete(Tables.TV_SHOW_ITEMS, selection, selectionArgs);
                break;
            case Codes.PERSON_ITEMS:
                delCount = database.delete(Tables.PERSON_ITEMS, selection, selectionArgs);
                break;
            case Codes.ITEM_LISTS:
                delCount = database.delete(Tables.ITEM_LISTS, selection, selectionArgs);
                break;
            default:
                Log.e(TAG, String.format("Trying to resolve unknown Uri: %s", uri));
                return 0;
        }
        Context context = getContext();
        if (context != null && delCount > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return delCount;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int updateCount = 0;
        switch (uriMatcher.match(uri)) {
            case Codes.MOVIE_ITEMS:
                updateCount = database.update(Tables.MOVIE_ITEMS, values, selection, selectionArgs);
                break;
            case Codes.TV_SHOW_ITEMS:
                updateCount = database.update(Tables.TV_SHOW_ITEMS, values, selection, selectionArgs);
                break;
            case Codes.PERSON_ITEMS:
                updateCount = database.update(Tables.PERSON_ITEMS, values, selection, selectionArgs);
                break;
            case Codes.ITEM_LISTS:
                updateCount = database.update(Tables.ITEM_LISTS, values, selection, selectionArgs);
                break;
            default:
                Log.e(TAG, String.format("Trying to resolve unknown Uri: %s", uri));
                return 0;
        }
        Context context = getContext();
        if (context != null && updateCount > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return updateCount;
    }
}
