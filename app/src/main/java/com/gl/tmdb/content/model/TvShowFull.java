package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents full TV show information.
 */
public class TvShowFull extends TvShowBase {

    @SerializedName("homepage")
    private String homepage;
    @SerializedName("type")
    private String type;
    @SerializedName("status")
    private String status;
    @SerializedName("created_by")
    private List<PersonItem> createdBy = new ArrayList<>();
    @SerializedName("episode_run_time")
    private List<Integer> episodeRunTime = new ArrayList<>();
    @SerializedName("in_production")
    private Boolean inProduction;
    @SerializedName("languages")
    private List<String> languages = new ArrayList<>();
    @SerializedName("last_air_date")
    private String lastAirDate;
    @SerializedName("networks")
    private List<Network> networks = new ArrayList<>();
    @SerializedName("number_of_episodes")
    private Integer numberOfEpisodes;
    @SerializedName("number_of_seasons")
    private Integer numberOfSeasons;
    @SerializedName("seasons")
    private List<Season> seasons = new ArrayList<>();
    @SerializedName("genres")
    private List<Genre> genres = new ArrayList<>();
    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies = new ArrayList<>();


}
