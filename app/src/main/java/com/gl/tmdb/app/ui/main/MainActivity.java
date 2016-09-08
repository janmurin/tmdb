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
import com.gl.tmdb.app.ui.fragments.HomeFragment;
import com.gl.tmdb.app.ui.fragments.MoviesFragment;
import com.gl.tmdb.app.ui.fragments.PersonsFragment;
import com.gl.tmdb.app.ui.fragments.TVShowsFragment;
import com.gl.tmdb.content.model.MediaListType;
import com.gl.tmdb.events.eventbus.EventBusEvents;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;

/**
 * MainActivity serves as main view point of the application hosting
 * all main fragments and navigation.
 */
public class MainActivity extends BaseActivity {

    private static final long NAVIGATION_CLOSE_DELAY = 250;

    protected
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    protected
    @BindView(R.id.nav_view)
    NavigationView navigationView;
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

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusEvent(EventBusEvents.ViewAllEvent viewAllEvent) {
        if (viewAllEvent.mediaListType == MediaListType.MOVIES_NOW_PLAYING) {
            displayMovies();
        }
        if (viewAllEvent.mediaListType == MediaListType.POPULAR_PEOPLE) {
            displayPersons();
        }
        if (viewAllEvent.mediaListType == MediaListType.TV_SHOW_AIRING_TODAY) {
            displayTvShows();
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

    private void displayHome() {
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);
        if (fragmentByTag == null) {
            showContentFragment(HomeFragment.newInstance(), HomeFragment.TAG);
            System.out.println("fragment home vytvoreny");
        } else {
            showContentFragment(fragmentByTag, HomeFragment.TAG);
            System.out.println("fragment home obnoveny");
        }
        navigationView.getMenu().getItem(HomeFragment.DRAWER_POS).setChecked(true);
    }

    private void displayMovies() {
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(MoviesFragment.TAG);
        if (fragmentByTag == null) {
            showContentFragment(MoviesFragment.newInstance(), MoviesFragment.TAG);
            System.out.println("fragment movies vytvoreny");
        } else {
            showContentFragment(fragmentByTag, MoviesFragment.TAG);
            System.out.println("fragment movies obnoveny");
        }
        navigationView.getMenu().getItem(MoviesFragment.DRAWER_POS).setChecked(true);
    }

    private void displayTvShows() {
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(TVShowsFragment.TAG);
        if (fragmentByTag == null) {
            showContentFragment(TVShowsFragment.newInstance(), TVShowsFragment.TAG);
            System.out.println("fragment tvshows vytvoreny");
        } else {
            showContentFragment(fragmentByTag, TVShowsFragment.TAG);
            System.out.println("fragment tvshows obnoveny");
        }
        navigationView.getMenu().getItem(TVShowsFragment.DRAWER_POS).setChecked(true);
    }

    private void displayPersons() {
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(PersonsFragment.TAG);
        if (fragmentByTag == null) {
            showContentFragment(PersonsFragment.newInstance(), PersonsFragment.TAG);
            System.out.println("fragment persons vytvoreny");
        } else {
            showContentFragment(fragmentByTag, PersonsFragment.TAG);
            System.out.println("fragment persons obnoveny");
        }
        navigationView.getMenu().getItem(PersonsFragment.DRAWER_POS).setChecked(true);
    }


    private void showContentFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    private void onNavigationItem(int id) {
        switch (id) {
            case R.id.nav_home:
                //showContentFragment(HomeFragment.newInstance(), HomeFragment.TAG);
                displayHome();
                break;
            case R.id.nav_discover:
                break;
            case R.id.nav_movies:
                displayMovies();
                break;
            case R.id.nav_tvshows:
                displayTvShows();
                break;
            case R.id.nav_people:
                displayPersons();
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
