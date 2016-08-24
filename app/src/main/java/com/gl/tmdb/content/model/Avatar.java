package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents Avatar for user Account.
 */
public class Avatar {

    public static class Gravatar {

        public static final String GRAVATAR_FORMAT = "http://www.gravatar.com/avatar/%s?s=%s";

        @SerializedName("hash")
        public String hash;

        public String getGravatarUrl(int size) {
            return String.format(GRAVATAR_FORMAT, hash, size);
        }
    }

    @SerializedName("gravatar")
    private Gravatar gravatar;

    public Gravatar getGravatar() {
        return gravatar;
    }
}
