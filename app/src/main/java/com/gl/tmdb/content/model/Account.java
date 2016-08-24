package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents basic information for a single account.
 */
public class Account {

    @SerializedName("avatar")
    private Avatar avatar;
    @SerializedName("id")
    private Integer id;
    @SerializedName("iso_639_1")
    private String languageIsoCode;
    @SerializedName("iso_3166_1")
    private String countryIsoCode;
    @SerializedName("name")
    private String name;
    @SerializedName("include_adult")
    private Boolean includeAdult;
    @SerializedName("username")
    private String username;

    /* SessionId stored with Account in Preferences for signed in user */
    @SerializedName("sesion_id")
    private String sessionId;


    public Avatar getAvatar() {
        return avatar;
    }

    public Integer getId() {
        return id;
    }

    /**
     * @return iso_639_1 ISO code of language associated with Account
     */
    public String getLanguageIsoCode() {
        return languageIsoCode;
    }

    /**
     * @return iso_3166_1 ISO code of country associated with Account
     */
    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public String getName() {
        return name;
    }

    public Boolean getIncludeAdult() {
        return includeAdult;
    }

    public String getUsername() {
        return username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
