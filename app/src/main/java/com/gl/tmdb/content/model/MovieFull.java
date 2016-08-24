package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents full movie information.
 */
public class MovieFull extends MovieBase {

    @SerializedName("genres")
    private List<Genre> genres = new ArrayList<>();
    @SerializedName("runtime")
    private Integer runtime;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("budget")
    private Integer budget;
    @SerializedName("revenue")
    private Integer revenue;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies = new ArrayList<>();
    @SerializedName("production_countries")
    private List<ProductionCountry> productionCountries = new ArrayList<>();
    @SerializedName("spoken_languages")
    private List<SpokenLanguage> spokenLanguages = new ArrayList<>();
    @SerializedName("status")
    private String status;
//    @SerializedName("belongs_to_collection")
//    private Object belongsToCollection;

    public List<Genre> getGenres() {
        return genres;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public Integer getBudget() {
        return budget;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

//    public Object getBelongsToCollection() {
//        return belongsToCollection;
//    }
}
