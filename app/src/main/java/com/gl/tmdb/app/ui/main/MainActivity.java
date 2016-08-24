package com.gl.tmdb.app.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gl.tmdb.R;
import com.gl.tmdb.app.base.BaseActivity;
import com.gl.tmdb.app.custom.NavigationHeader;

import butterknife.BindView;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;

/**
 * MainActivity serves as main view point of the application hosting
 * all main fragments and navigation.
 */
public class MainActivity extends BaseActivity {

    private static final long NAVIGATION_CLOSE_DELAY = 250;

    protected @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    protected @BindView(R.id.nav_view) NavigationView navigationView;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentBind(R.layout.activity_main, this);
        setupToolbar(false);
        setupNavigationView();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        // check if there's any retained content fragment, if not, show home
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment == null) {
            showContentFragment(HomeFragment.newInstance(), HomeFragment.TAG);
            navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(navItemListener);
        NavigationHeader navHeader = (NavigationHeader) navigationView.getHeaderView(0);

        boolean signedIn = false;
        if (signedIn) {
            //navHeader.showAccount(signedInAccount, navClickListener);
        } else {
            navHeader.showSignIn(navClickListener);
        }
    }

    private void showContentFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment, tag)
                .commit();
    }

    private void onNavigationItem(int id) {
        switch (id) {
            case R.id.nav_home:
                break;
            case R.id.nav_discover:
                break;
            case R.id.nav_movies:
                break;
            case R.id.nav_tvshows:
                break;
            case R.id.nav_people:
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_about:
                break;
        }
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    protected OnNavigationItemSelectedListener navItemListener = new OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            final int id = item.getItemId();
            closeDrawer();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onNavigationItem(id);
                }
            }, NAVIGATION_CLOSE_DELAY);
            return true;
        }
    };

    protected View.OnClickListener navClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int id = v.getId();
            switch (id) {
                case R.id.account_layout:
                    Log.d(TAG, "account clicked");
                    break;
                case R.id.sign_layout:
                    Log.d(TAG, "signin clicked");
                    break;
            }
        }
    };
}
