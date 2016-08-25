package com.gl.tmdb.content.network.services;

import com.gl.tmdb.content.model.TvShowItem;
import com.gl.tmdb.content.network.responses.PagedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Service for all TvShow API calls.
 */
public interface TvShowService {

    @GET("tv/popular")
    Call<PagedResponse<TvShowItem>> popular(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );

    @GET("tv/top_rated")
    Call<PagedResponse<TvShowItem>> topRated(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );

    @GET("tv/airing_today")
    Call<PagedResponse<TvShowItem>> airingToday(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );

    @GET("tv/on_the_air")
    Call<PagedResponse<TvShowItem>> onTheAir(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );
}
