package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents production country for Moview or TV show.
 */
public class ProductionCountry {

    @SerializedName("iso_3166_1")
    private String isoCode;
    @SerializedName("name")
    private String name;

    /**
     * @return iso_3166_1 ISO code of country
     */
    public String getIsoCode() {
        return isoCode;
    }

    /**
     * @return the name of the country
     */
    public String getName() {
        return name;
    }
}
