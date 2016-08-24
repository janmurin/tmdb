package com.gl.tmdb.content.network.services_rx;

import com.gl.tmdb.content.model.Session;
import com.gl.tmdb.content.model.Token;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Service for all Authentication related API calls.
 */
public interface AuthService {

    @GET("authentication/token/new")
    Observable<Token> newToken();

    @GET("authentication/token/validate_with_login")
    Observable<Token> validateTokenLogin(
            @Query("request_token") String requestToken,
            @Query("username") String userName,
            @Query("password") String password
    );

    @GET("authentication/session/new")
    Observable<Session> newSession(
            @Query("request_token") String requestToken
    );

    @GET("authentication/guest_session/new")
    Observable<Session> newGuestSession();
}
