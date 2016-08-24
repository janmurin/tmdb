package com.gl.tmdb.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Simple static helper class for Preferences manipulation.
 */
@SuppressWarnings("unused")
public class PrefsUtils {

    public static final String PREFS_NAME = "droidmdb_app_prefs";

    /* KEYS */
    public static final String KEY_FIRST_LAUNCH = "first_launch";
    public static final String KEY_GUEST_SESSION_ID = "guest_session_id";
    public static final String KEY_SIGNEDIN_ACCOUNT = "signed_in_account";
    public static final String KEY_HOME_REFRESH_TS = "key_home_refresh_ts";

    private static SharedPreferences sSharedPreferences;

    public static void init(final Context context) {
        sSharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void setBoolean(final String key, final boolean value) {
        getSharedPrefsEditor().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(final String key, final boolean defaultValue) {
        return getSharedPrefs().getBoolean(key, defaultValue);
    }

    public static void setInt(final String key, final int value) {
        getSharedPrefsEditor().putInt(key, value).commit();
    }

    public static int getInt(final String key, final int defaultValue) {
        return getSharedPrefs().getInt(key, defaultValue);
    }

    public static void setLong(final String key, final long value) {
        getSharedPrefsEditor().putLong(key, value).commit();
    }

    public static long getLong(final String key, final long defaultValue) {
        return getSharedPrefs().getLong(key, defaultValue);
    }

    public static void setString(final String key, final String value) {
        getSharedPrefsEditor().putString(key, value).commit();
    }

    public static String getString(final String key, final String defaultValue) {
        return getSharedPrefs().getString(key, defaultValue);
    }

    public static void remove(final String key) {
        getSharedPrefsEditor().remove(key).commit();
    }

    public static void clearAll() {
        getSharedPrefsEditor().clear().commit();
    }

    private static SharedPreferences getSharedPrefs() {
        return sSharedPreferences;
    }

    private static SharedPreferences.Editor getSharedPrefsEditor() {
        return getSharedPrefs().edit();
    }
}
