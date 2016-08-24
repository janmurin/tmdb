package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

/**
 * Response received when requesting new token.
 */
public final class Token {

    @SerializedName("expires_at")
    private String expiresAt;
    @SerializedName("request_token")
    private String requestToken;
    @SerializedName("success")
    private Boolean success;

    public String getExpiresAt() {
        return expiresAt;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public Boolean isSuccess() {
        return success;
    }
}
