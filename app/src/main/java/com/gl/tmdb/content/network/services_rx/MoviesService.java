package com.gl.tmdb.content.network.services_rx;

import com.gl.tmdb.content.model.MovieItem;
import com.gl.tmdb.content.network.responses.PagedResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Service for all Movies API calls.
 */
public interface MoviesService {

    @GET("movie/popular")
    Observable<PagedResponse<MovieItem>> popular(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );

    @GET("movie/top_rated")
    Observable<PagedResponse<MovieItem>> topRated(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );

    @GET("movie/upcoming")
    Observable<PagedResponse<MovieItem>> upcoming(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );

    @GET("movie/now_playing")
    Observable<PagedResponse<MovieItem>> nowPlaying(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );
}
