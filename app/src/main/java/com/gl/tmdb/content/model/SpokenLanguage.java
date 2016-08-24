package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents language spoken in MovieFull or TV show.
 */
public class SpokenLanguage {

    @SerializedName("iso_639_1")
    private String isoCode;
    @SerializedName("name")
    private String name;

    /**
     * @return iso_639_1 ISO code of language
     */
    public String getIsoCode() {
        return isoCode;
    }

    /**
     * @return the name of the language
     */
    public String getName() {
        return name;
    }
}
