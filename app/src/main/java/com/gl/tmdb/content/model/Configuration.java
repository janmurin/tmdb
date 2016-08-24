package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents system wide configuration information..
 */
public class Configuration {

    public static class Images {
        @SerializedName("base_url")
        public String baseUrl;
        @SerializedName("secure_base_url")
        public String secureBaseUrl;
        @SerializedName("backdrop_sizes")
        public List<String> backdropSizes = new ArrayList<>();
        @SerializedName("logo_sizes")
        public List<String> logoSizes = new ArrayList<>();
        @SerializedName("poster_sizes")
        public List<String> posterSizes = new ArrayList<>();
        @SerializedName("profile_sizes")
        public List<String> profileSizes = new ArrayList<>();
        @SerializedName("still_sizes")
        public List<String> stillSizes = new ArrayList<>();
    }

    @SerializedName("images")
    private Images images;
    @SerializedName("change_keys")
    private List<String> changeKeys = new ArrayList<>();

    public Images getImages() {
        return images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }
}
