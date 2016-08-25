package com.gl.tmdb.content.network.services;

import com.gl.tmdb.content.model.MovieItem;
import com.gl.tmdb.content.network.responses.PagedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Service for all Movies API calls.
 */
public interface MoviesService {

    @GET("movie/popular")
    Call<PagedResponse<MovieItem>> popular(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );

    @GET("movie/top_rated")
    Call<PagedResponse<MovieItem>> topRated(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );

    @GET("movie/upcoming")
    Call<PagedResponse<MovieItem>> upcoming(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );

    @GET("movie/now_playing")
    Call<PagedResponse<MovieItem>> nowPlaying(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );
}
