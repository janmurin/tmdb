package com.gl.tmdb.content.model;

import com.google.gson.annotations.SerializedName;

/**
 * Response received when requesting new session ID.
 */
public final class Session {

    @SerializedName("session_id")
    private String sessionId;
    @SerializedName("success")
    private Boolean success;

    public String getSessionId() {
        return sessionId;
    }

    public Boolean isSuccess() {
        return success;
    }
}
