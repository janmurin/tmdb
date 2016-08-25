package com.gl.tmdb.app.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.gl.tmdb.R;
import com.gl.tmdb.app.base.BaseActivity;
import com.gl.tmdb.app.ui.main.MainActivity;
import com.gl.tmdb.content.ApiServices;
import com.gl.tmdb.content.model.Configuration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Launching splash Activity displaying app logo (for now).
 */
public final class SplashActivity extends BaseActivity {

    public static final String TAG = "SplashActivity";

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler.postDelayed(showMain, getResources().getInteger(R.integer.splash_timeout));

        // API call Example
        Call<Configuration> confCall = ApiServices.getConfigurationService().configuration();
        confCall.enqueue(new Callback<Configuration>() {
            @Override
            public void onResponse(Call<Configuration> call, Response<Configuration> response) {
                Log.d(TAG, "getConfiguration -> success: "+response.body().toString());
            }

            @Override
            public void onFailure(Call<Configuration> call, Throwable t) {
                Log.d(TAG, "getConfiguration -> failure: "+t.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
        super.onBackPressed();
    }

    private Runnable showMain = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
            SplashActivity.this.finish();
        }
    };
}
