package com.gl.tmdb.content.network.services_rx;

import com.gl.tmdb.content.model.TvShowItem;
import com.gl.tmdb.content.network.responses.PagedResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Service for all TvShow API calls.
 */
public interface TvShowService {

    @GET("tv/popular")
    Observable<PagedResponse<TvShowItem>> popular(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );

    @GET("tv/top_rated")
    Observable<PagedResponse<TvShowItem>> topRated(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );

    @GET("tv/airing_today")
    Observable<PagedResponse<TvShowItem>> airingToday(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );

    @GET("tv/on_the_air")
    Observable<PagedResponse<TvShowItem>> onTheAir(
            @Query("page") String page,         /* optional */
            @Query("language") String language  /* optional */
    );
}
