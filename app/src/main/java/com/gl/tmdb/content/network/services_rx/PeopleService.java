package com.gl.tmdb.content.network.services_rx;

import com.gl.tmdb.content.model.PersonItem;
import com.gl.tmdb.content.network.responses.PagedResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Service for all People API calls.
 */
public interface PeopleService {

    @GET("person/popular")
    Observable<PagedResponse<PersonItem>> popular(
            @Query("page") String page          /* optional */
    );

    @GET("person/latest")
    Observable<PagedResponse<PersonItem>> latest(
            @Query("page") String page          /* optional */
    );
}
