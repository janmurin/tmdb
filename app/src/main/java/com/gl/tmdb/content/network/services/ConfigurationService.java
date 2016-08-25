package com.gl.tmdb.content.network.services;

import com.gl.tmdb.content.model.Configuration;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Service for all Configuration API calls.
 */
public interface ConfigurationService {

    @GET("configuration")
    Call<Configuration> configuration();
}
