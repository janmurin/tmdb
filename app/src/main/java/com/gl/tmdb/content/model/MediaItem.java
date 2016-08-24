package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

/**
 * Base class for media items.
 */
public class MediaItem {

    @SerializedName("id")
    protected Integer id;
    @SerializedName("media_type")
    protected MediaType mediaType;

    public Integer getId() {
        return id;
    }

    public MediaType getMediaType() {
        return mediaType;
    }
}
