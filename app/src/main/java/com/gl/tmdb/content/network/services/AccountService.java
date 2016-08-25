package com.gl.tmdb.content.network.services;

import com.gl.tmdb.content.model.Account;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Service for all Account API calls.
 */
public interface AccountService {

    @GET("account")
    Call<Account> account(
            @Query("session_id") String sessionId
    );

    @GET("account/{id}/lists")
    Call<Account> lists(
            @Path("id") String id,
            @Query("session_id") String sessionId,
            @Query("page") String page,             /* optional */
            @Query("language") String language      /* optional */
    );

    @GET("account/{id}/lists")
    Call<Account> favoriteMovies(
            @Path("id") String id,
            @Query("session_id") String sessionId,
            @Query("page") String page,             /* optional */
            @Query("sort_by") String sortBy,        /* optional */
            @Query("language") String language      /* optional */
    );

    @GET("account/{id}/lists")
    Call<Account> favoriteTv(
            @Path("id") String id,
            @Query("session_id") String sessionId,
            @Query("page") String page,             /* optional */
            @Query("sort_by") String sortBy,        /* optional */
            @Query("language") String language      /* optional */
    );
}
