package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents full person information.
 */
public class PersonFull extends PersonBase {

    @SerializedName("also_known_as")
    private List<String> alsoKnownAs;
    @SerializedName("biography")
    private String biography;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("deathday")
    private String deathday;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("place_of_birth")
    private String birthPlace;

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public String getBiography() {
        return biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBirthPlace() {
        return birthPlace;
    }
}
