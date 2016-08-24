package com.gl.tmdb.app.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.gl.tmdb.R;
import com.gl.tmdb.app.base.BaseActivity;
import com.gl.tmdb.app.ui.main.MainActivity;

/**
 * Launching splash Activity displaying app logo (for now).
 */
public final class SplashActivity extends BaseActivity {

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler.postDelayed(showMain, getResources().getInteger(R.integer.splash_timeout));
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
