package com.gl.tmdb.content.model;

/**
 * Created by zavadpe on 24.3.2016.
 */
public enum MediaListType {
    MOVIES_POPULAR(MediaType.MOVIE, "Popular"),
    MOVIES_TOP_RATED(MediaType.MOVIE, "Top rated"),
    MOVIES_UPCOMING(MediaType.MOVIE, "Upcoming"),
    MOVIES_NOW_PLAYING(MediaType.MOVIE, "Now playing"),

    TV_SHOW_POPULAR(MediaType.TV_SHOW, "Popular"),
    TV_SHOW_TOP_RATED(MediaType.TV_SHOW, "Top rated"),
    TV_SHOW_ON_THE_AIR(MediaType.TV_SHOW, "On the air"),
    TV_SHOW_AIRING_TODAY(MediaType.TV_SHOW, "Airing today"),

    POPULAR_PEOPLE(MediaType.PERSON, "Popular persons");

    public final MediaType mediaType;
    public final String title;

    MediaListType(MediaType mediaType, String title) {
        this.mediaType = mediaType;
        this.title = title;
    }
}
