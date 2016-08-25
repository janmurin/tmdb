package com.gl.tmdb.content.network.services;

import com.gl.tmdb.content.model.Session;
import com.gl.tmdb.content.model.Token;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Service for all Authentication related API calls.
 */
public interface AuthService {

    @GET("authentication/token/new")
    Call<Token> newToken();

    @GET("authentication/token/validate_with_login")
    Call<Token> validateTokenLogin(
            @Query("request_token") String requestToken,
            @Query("username") String userName,
            @Query("password") String password
    );

    @GET("authentication/session/new")
    Call<Session> newSession(
            @Query("request_token") String requestToken
    );

    @GET("authentication/guest_session/new")
    Call<Session> newGuestSession();
}
