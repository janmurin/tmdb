package com.gl.tmdb.content.network.services_rx;

import com.gl.tmdb.content.model.Configuration;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Service for all Configuration API calls.
 */
public interface ConfigurationService {

    @GET("configuration")
    Observable<Configuration> configuration();
}
