package com.gl.tmdb.events.auth;

/**
 * Event triggered when user sign in status changes.
 */
public final class ESignInChange {

    public final boolean signedIn;
    public final String userName;
    public final String errorMessage;

    public ESignInChange(boolean signedIn, String userName, String errorMessage) {
        this.signedIn = signedIn;
        this.userName = userName;
        this.errorMessage = errorMessage;
    }
}
