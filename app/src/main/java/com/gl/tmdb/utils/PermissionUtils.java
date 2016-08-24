package com.gl.tmdb.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Useful methods for permission checking on Android M and higher.
 */
@SuppressWarnings("unused")
public final class PermissionUtils {

    public static boolean hasPermission(final Context context, final String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean hasAllPermissions(final Context context, final String ... permissions) {
        boolean all = true;
        for (String permission : permissions) {
            all &= hasPermission(context, permission);
        }
        return all;
    }
}
