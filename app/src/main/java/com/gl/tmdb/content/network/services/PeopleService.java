package com.gl.tmdb.content.network.services;

import com.gl.tmdb.content.model.PersonFull;
import com.gl.tmdb.content.model.PersonItem;
import com.gl.tmdb.content.network.responses.PagedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Service for all People API calls.
 */
public interface PeopleService {

    @GET("person/popular")
    Call<PagedResponse<PersonItem>> popular(
            @Query("page") String page          /* optional */
    );

    @GET("person/latest")
    Call<PagedResponse<PersonItem>> latest(
            @Query("page") String page          /* optional */
    );

    @GET("person/{id}")
    Call<PersonFull> person(@Path("id") int id);
}
