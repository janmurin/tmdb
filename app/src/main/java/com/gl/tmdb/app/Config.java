package com.gl.tmdb.app;

/**
 * Global application configuration values and constants.
 */
public class Config {

    public static final long REFRESH_TRESHOLD = 15*60*1000; //15min
    public static final boolean USE_LEAK_CANARY = true;

    public static class Api {
        /* Defines range of mock API response time in ms  */
        public static final long[] MOCK_RESPONSE_DELAY = {250, 750};

        public static final String BASE_URL = "http://api.themoviedb.org/3/";
        public static final String KEY_PARAM = "api_key";
        public static final String KEY = "488165ee8e6af1b7c0783e3c4441ac08";
    }
}
