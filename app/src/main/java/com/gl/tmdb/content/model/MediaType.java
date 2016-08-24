package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents media type of items returned by API (e.g. search/multi).
 */
public enum MediaType {

    @SerializedName("movie")
    MOVIE,
    @SerializedName("tv")
    TV_SHOW,
    @SerializedName("person")
    PERSON;
}
