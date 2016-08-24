package com.gl.tmdb.app.base;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gl.tmdb.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * Base class for Activities in the application.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = getClass().getSimpleName();

    @Nullable
    @BindView(R.id.toolbar) protected Toolbar toolbar;

    protected CompositeSubscription subscriptions = new CompositeSubscription();

    protected <A extends BaseActivity> void setContentBind(final int layoutRes, A childActivity) {
        setContentView(layoutRes);
        // bind common UI components
        ButterKnife.bind(childActivity, childActivity);
    }

    protected void setupToolbar(boolean homeAsUpEnabled) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
                getSupportActionBar().setDisplayShowHomeEnabled(homeAsUpEnabled);
            }
        }
    }

    protected void setToolbarTitle(final String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
