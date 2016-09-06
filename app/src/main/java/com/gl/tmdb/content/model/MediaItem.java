package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Base class for media items.
 */
public class MediaItem implements Serializable{

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
