package com.gl.tmdb.content.local;

import com.gl.tmdb.utils.DbUtils;

/**
 * Contains static queries for DB tables creation.
 */
final class TableQueries {

    public static String createMovieItemsTable() {
        return DbUtils.TableBuilder.table(DataContract.Tables.MOVIE_ITEMS)
                .primaryKey(DataContract.MovieItems._ID)
                .columnIntUnique(DataContract.MovieItems.ID)
                .columnText(DataContract.MovieItems.MEDIA_TYPE)
                .columnText(DataContract.MovieItems.TITLE)
                .columnText(DataContract.MovieItems.ORIGINAL_TITLE)
                .columnText(DataContract.MovieItems.ORIGINAL_LANGUAGE)
                .columnText(DataContract.MovieItems.OVERVIEW)
                .columnText(DataContract.MovieItems.POSTER_PATH)
                .columnText(DataContract.MovieItems.RELEASE_DATE)
                .columnReal(DataContract.MovieItems.POPULARITY)
                .columnInt(DataContract.MovieItems.VOTE_COUNT)
                .columnReal(DataContract.MovieItems.VOTE_AVERAGE)
                .columnInt(DataContract.MovieItems.VIDEO)
                .columnInt(DataContract.MovieItems.ADULT)
                .columnText(DataContract.MovieItems.BACKDROP_PATH)
                .columnText(DataContract.MovieItems.GENRE_IDS)
                .build();
    }

    public static String createTvShowItemsTable() {
        return DbUtils.TableBuilder.table(DataContract.Tables.TV_SHOW_ITEMS)
                .primaryKey(DataContract.TvShowItems._ID)
                .columnIntUnique(DataContract.TvShowItems.ID)
                .columnText(DataContract.TvShowItems.MEDIA_TYPE)
                .columnText(DataContract.TvShowItems.NAME)
                .columnText(DataContract.TvShowItems.ORIGINAL_NAME)
                .columnText(DataContract.TvShowItems.ORIGINAL_LANGUAGE)
                .columnText(DataContract.TvShowItems.OVERVIEW)
                .columnText(DataContract.TvShowItems.POSTER_PATH)
                .columnText(DataContract.TvShowItems.FIRST_AIR_DATE)
                .columnReal(DataContract.TvShowItems.POPULARITY)
                .columnInt(DataContract.TvShowItems.VOTE_COUNT)
                .columnReal(DataContract.TvShowItems.VOTE_AVERAGE)
                .columnText(DataContract.TvShowItems.BACKDROP_PATH)
                .columnText(DataContract.TvShowItems.ORIGIN_COUNTRY)
                .columnText(DataContract.TvShowItems.GENRE_IDS)
                .build();
    }

    public static String createPersonItemsTable() {
        return DbUtils.TableBuilder.table(DataContract.Tables.PERSON_ITEMS)
                .primaryKey(DataContract.PersonItems._ID)
                .columnIntUnique(DataContract.PersonItems.ID)
                .columnText(DataContract.PersonItems.MEDIA_TYPE)
                .columnText(DataContract.PersonItems.NAME)
                .columnReal(DataContract.PersonItems.POPULARITY)
                .columnInt(DataContract.PersonItems.ADULT)
                .columnText(DataContract.PersonItems.PROFILE_PATH)
                .columnText(DataContract.PersonItems.KNOWN_FOR)
                .build();
    }

    public static String createItemListsTable() {
        return DbUtils.TableBuilder.table(DataContract.Tables.ITEM_LISTS)
                .primaryKey(DataContract.ItemLists._ID)
                .columnInt(DataContract.ItemLists.LIST_TYPE)
                .columnInt(DataContract.ItemLists.ITEM_ID)
                .build();
    }
}
