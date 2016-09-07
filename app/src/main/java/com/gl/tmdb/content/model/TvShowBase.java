package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents basic TV show information and serves as a base class
 * for {@link TvShowItem} and {@link TvShowFull}.
 */
public class TvShowBase extends MediaItem {

    @SerializedName("name")
    protected String name;
    @SerializedName("original_name")
    protected String originalName;
    @SerializedName("original_language")
    protected String originalLanguage;
    @SerializedName("overview")
    protected String overview;
    @SerializedName("poster_path")
    protected String posterPath;
    @SerializedName("first_air_date")
    protected String firstAirDate;
    @SerializedName("popularity")
    protected Double popularity;
    @SerializedName("vote_count")
    protected Integer voteCount;
    @SerializedName("vote_average")
    protected Double voteAverage;
    @SerializedName("backdrop_path")
    protected String backdropPath;
    @SerializedName("origin_country")
    protected List<String> originCountry = new ArrayList<String>();

    public TvShowBase() {
        mediaType = MediaType.TV_SHOW;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public Double getPopularity() {
        return popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    @Override
    public String toString() {
        return "TvShowBase{" +
                "name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", overview='" + overview + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", firstAirDate='" + firstAirDate + '\'' +
                ", popularity=" + popularity +
                ", voteCount=" + voteCount +
                ", voteAverage=" + voteAverage +
                ", backdropPath='" + backdropPath + '\'' +
                ", originCountry=" + originCountry +
                '}';
    }
}
