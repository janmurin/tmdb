package com.gl.tmdb.content.model;

/**
 * Created by zavadpe on 24.3.2016.
 */
public enum MediaListType {
    MOVIES_POPULAR(MediaType.MOVIE),
    MOVIES_TOP_RATED(MediaType.MOVIE),
    MOVIES_UPCOMING(MediaType.MOVIE),
    MOVIES_NOW_PLAYING(MediaType.MOVIE),

    TV_SHOW_POPULAR(MediaType.TV_SHOW),
    TV_SHOW_TOP_RATED(MediaType.TV_SHOW),
    TV_SHOW_ON_THE_AIR(MediaType.TV_SHOW),
    TV_SHOW_AIRING_TODAY(MediaType.TV_SHOW),

    POPULAR_PEOPLE(MediaType.PERSON);

    public final MediaType mediaType;

    MediaListType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}
