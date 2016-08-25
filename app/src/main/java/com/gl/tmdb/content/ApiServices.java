package com.gl.tmdb.content;

import com.gl.tmdb.app.Config;
import com.gl.tmdb.content.network.services.AccountService;
import com.gl.tmdb.content.network.services.AuthService;
import com.gl.tmdb.content.network.services.ConfigurationService;
import com.gl.tmdb.content.network.services.MoviesService;
import com.gl.tmdb.content.network.services.PeopleService;
import com.gl.tmdb.content.network.services.TvShowService;

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
public final class ApiServices {

    public static AuthService getAuthService() {
        return InstanceHolder.instance.retrofit.create(AuthService.class);
    }

    public static AccountService getAccountService() {
        return InstanceHolder.instance.retrofit.create(AccountService.class);
    }

    public static ConfigurationService getConfigurationService() {
        return InstanceHolder.instance.retrofit.create(ConfigurationService.class);
    }

    public static MoviesService getMoviesService() {
        return InstanceHolder.instance.retrofit.create(MoviesService.class);
    }

    public static PeopleService getPeopleService() {
        return InstanceHolder.instance.retrofit.create(PeopleService.class);
    }

    public static TvShowService getTvShowService() {
        return InstanceHolder.instance.retrofit.create(TvShowService.class);
    }

    private static class InstanceHolder {
        public static final ApiServices instance = new ApiServices();
    }

    private Retrofit retrofit;

    private ApiServices() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.Api.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
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
