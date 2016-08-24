package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents basic person information and serves as a base class
 * for {@link PersonItem} and {@link PersonFull}.
 */
public class PersonBase extends MediaItem {

    @SerializedName("name")
    protected String name;
    @SerializedName("popularity")
    protected Double popularity;
    @SerializedName("profile_path")
    protected String profilePath;
    @SerializedName("adult")
    protected Boolean adult;
    @SerializedName("known_for")
    protected List<MediaItem> knownFor = new ArrayList<>();

    public PersonBase() {
        mediaType = MediaType.PERSON;
    }

    public String getName() {
        return name;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public List<MediaItem> getKnownFor() {
        return knownFor;
    }
}
