package com.gl.tmdb.content.network.services_rx;

import com.gl.tmdb.content.model.Account;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Service for all Account API calls.
 */
public interface AccountService {

    @GET("account")
    Observable<Account> account(
            @Query("session_id") String sessionId
    );

    @GET("account/{id}/lists")
    Observable<Account> lists(
            @Path("id") String id,
            @Query("session_id") String sessionId,
            @Query("page") String page,             /* optional */
            @Query("language") String language      /* optional */
    );

    @GET("account/{id}/lists")
    Observable<Account> favoriteMovies(
            @Path("id") String id,
            @Query("session_id") String sessionId,
            @Query("page") String page,             /* optional */
            @Query("sort_by") String sortBy,        /* optional */
            @Query("language") String language      /* optional */
    );

    @GET("account/{id}/lists")
    Observable<Account> favoriteTv(
            @Path("id") String id,
            @Query("session_id") String sessionId,
            @Query("page") String page,             /* optional */
            @Query("sort_by") String sortBy,        /* optional */
            @Query("language") String language      /* optional */
    );
}
