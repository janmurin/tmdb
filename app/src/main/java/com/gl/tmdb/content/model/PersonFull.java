package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents full person information.
 */
public class PersonFull extends PersonBase {

    @SerializedName("also_known_as")
    private String alsoKnownAs;
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
}
