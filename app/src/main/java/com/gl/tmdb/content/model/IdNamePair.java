package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

/**
 * Base class for all id/name pair based classes.
 */
public class IdNamePair {

    @SerializedName("id")
    protected Integer id;
    @SerializedName("name")
    protected String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
