package com.gl.tmdb.content.network.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Represents error response returned by invalid API call.
 */
public class ErrorResponse {

    @SerializedName("status_code")
    private Integer code;
    @SerializedName("status_message")
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
