package com.gl.tmdb.content;

import com.gl.tmdb.app.Config;
import com.gl.tmdb.content.network.services_rx.AccountService;
import com.gl.tmdb.content.network.services_rx.AuthService;
import com.gl.tmdb.content.network.services_rx.ConfigurationService;
import com.gl.tmdb.content.network.services_rx.MoviesService;
import com.gl.tmdb.content.network.services_rx.PeopleService;
import com.gl.tmdb.content.network.services_rx.TvShowService;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiManager implementation.
 */
final class ApiServices {

    private Retrofit retrofit;

    public ApiServices() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.Api.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public AuthService getAuthService() {
        return retrofit.create(AuthService.class);
    }

    public AccountService getAccountService() {
        return retrofit.create(AccountService.class);
    }

    public ConfigurationService getConfigurationService() {
        return retrofit.create(ConfigurationService.class);
    }

    public MoviesService getMoviesService() {
        return retrofit.create(MoviesService.class);
    }

    public PeopleService getPeopleService() {
        return retrofit.create(PeopleService.class);
    }

    public TvShowService getTvShowService() {
        return retrofit.create(TvShowService.class);
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ApiTokenInterceptor())
                .addInterceptor(logInterceptor)
                .build();
        return okHttpClient;
    }

    private static class ApiTokenInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            HttpUrl url = chain.request().url()
                    .newBuilder()
                    .addQueryParameter(Config.Api.KEY_PARAM, Config.Api.KEY)
                    .build();
            Request request = chain.request().newBuilder().url(url).build();
            return chain.proceed(request);
        }
    }
}
