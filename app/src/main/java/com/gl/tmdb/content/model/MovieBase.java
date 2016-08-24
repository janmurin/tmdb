package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents basic movie information and serves as a base class
 * for {@link MovieItem} and {@link MovieFull}.
 */
public class MovieBase extends MediaItem {

    @SerializedName("title")
    protected String title;
    @SerializedName("original_title")
    protected String originalTitle;
    @SerializedName("original_language")
    protected String originalLanguage;
    @SerializedName("overview")
    protected String overview;
    @SerializedName("poster_path")
    protected String posterPath;
    @SerializedName("release_date")
    protected String releaseDate;
    @SerializedName("popularity")
    protected Double popularity;
    @SerializedName("vote_count")
    protected Integer voteCount;
    @SerializedName("vote_average")
    protected Double voteAverage;
    @SerializedName("video")
    protected Boolean video;
    @SerializedName("adult")
    protected Boolean adult;
    @SerializedName("backdrop_path")
    protected String backdropPath;

    public MovieBase() {
        mediaType = MediaType.MOVIE;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
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

    public String getReleaseDate() {
        return releaseDate;
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

    public Boolean getVideo() {
        return video;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }
}
