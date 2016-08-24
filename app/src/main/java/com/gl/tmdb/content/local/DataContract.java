package com.gl.tmdb.content.local;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * The contract between the content provider and components using it.
 */
public final class DataContract {

    public static final String AUTHORITY = "com.gl.droidmdb";

    /* The content:// style URL for the top-level authority */
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    private static final String buildContentTypeDir(String name) {
        return "vnd.android.cursor.dir/" + AUTHORITY + "." + name;
    }

    private static final String buildContentTypeItem(String name) {
        return "vnd.android.cursor.item/" + AUTHORITY + "." + name;
    }

    public interface Tables {
        String MOVIE_ITEMS      = "movie_items";
        String MOVIE_ITEMS_ID   = "movie_items/*";
        String TV_SHOW_ITEMS    = "tv_show_items";
        String TV_SHOW_ITEMS_ID = "tv_show_items/*";
        String PERSON_ITEMS     = "person_items";
        String PERSON_ITEMS_ID  = "person_items/*";
        String ITEM_LISTS       = "item_lists";
    }

    public interface Codes {
        int MOVIE_ITEMS         = 100;
        int MOVIE_ITEMS_ID      = 101;
        int TV_SHOW_ITEMS       = 200;
        int TV_SHOW_ITEMS_ID    = 201;
        int PERSON_ITEMS        = 300;
        int PERSON_ITEMS_ID     = 301;
        int ITEM_LISTS          = 400;
    }

    protected interface MediaItemColumns {
        String ID                   = "item_id";            //int
        String MEDIA_TYPE           = "media_type";         //string
    }

    protected  interface MovieItemColumns {
        String TITLE                = "title";              //string
        String ORIGINAL_TITLE       = "original_title";     //string
        String ORIGINAL_LANGUAGE    = "original_language";  //string
        String OVERVIEW             = "overview";           //string
        String POSTER_PATH          = "poster_path";        //string
        String RELEASE_DATE         = "release_date";       //string
        String POPULARITY           = "popularity";         //double
        String VOTE_COUNT           = "vote_count";         //int
        String VOTE_AVERAGE         = "vote_average";       //double
        String VIDEO                = "video";              //bool
        String ADULT                = "adult";              //bool
        String BACKDROP_PATH        = "backdrop_path";      //string
        String GENRE_IDS            = "genre_ids";          //comma-separated list of int
    }

    protected  interface TvShowItemColumns {
        String NAME                 = "name";               //string
        String ORIGINAL_NAME        = "original_name";      //string
        String ORIGINAL_LANGUAGE    = "original_language";  //string
        String OVERVIEW             = "overview";           //string
        String POSTER_PATH          = "poster_path";        //string
        String FIRST_AIR_DATE       = "first_air_date";     //string
        String POPULARITY           = "popularity";         //double
        String VOTE_COUNT           = "vote_count";         //int
        String VOTE_AVERAGE         = "vote_average";       //double
        String BACKDROP_PATH        = "backdrop_path";      //string
        String ORIGIN_COUNTRY       = "origin_country";     //comma-separated list of string
        String GENRE_IDS            = "genre_ids";          //comma-separated list of int
    }

    protected  interface PersonItemColumns {
        String NAME                 = "name";               //string
        String POPULARITY           = "popularity";         //double
        String ADULT                = "adult";              //bool
        String PROFILE_PATH         = "profile_path";       //string
        String KNOWN_FOR            = "known_for";          //comma-separated list of int
    }

    protected interface ItemListColumns {
        String LIST_TYPE            = "list_type";          //int
        String ITEM_ID              = "item_id";            //int
    }

    public static final class MovieItems implements BaseColumns, MediaItemColumns, MovieItemColumns {
        public static final String CONTENT_TYPE         = buildContentTypeDir(Tables.MOVIE_ITEMS);
        public static final Uri CONTENT_URI             = Uri.withAppendedPath(AUTHORITY_URI, Tables.MOVIE_ITEMS);
        public static final String CONTENT_TYPE_ITEM    = buildContentTypeItem(Tables.MOVIE_ITEMS_ID);
    }

    public static final class TvShowItems implements BaseColumns, MediaItemColumns, TvShowItemColumns {
        public static final String CONTENT_TYPE         = buildContentTypeDir(Tables.TV_SHOW_ITEMS);
        public static final Uri CONTENT_URI             = Uri.withAppendedPath(AUTHORITY_URI, Tables.TV_SHOW_ITEMS);
        public static final String CONTENT_TYPE_ITEM    = buildContentTypeItem(Tables.TV_SHOW_ITEMS_ID);
    }

    public static final class PersonItems implements BaseColumns, MediaItemColumns, PersonItemColumns {
        public static final String CONTENT_TYPE         = buildContentTypeDir(Tables.PERSON_ITEMS);
        public static final Uri CONTENT_URI             = Uri.withAppendedPath(AUTHORITY_URI, Tables.PERSON_ITEMS);
        public static final String CONTENT_TYPE_ITEM    = buildContentTypeItem(Tables.PERSON_ITEMS_ID);
    }

    public static final class ItemLists implements BaseColumns, ItemListColumns {
        public static final String CONTENT_TYPE         = buildContentTypeDir(Tables.ITEM_LISTS);
        public static final Uri CONTENT_URI             = Uri.withAppendedPath(AUTHORITY_URI, Tables.ITEM_LISTS);
    }

    private DataContract() {/*protected*/}
}
