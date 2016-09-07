package com.gl.tmdb.app.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gl.tmdb.R;
import com.gl.tmdb.app.base.BaseFragment;
import com.gl.tmdb.content.model.MediaListType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan.murin on 06-Sep-16.
 */
public class TVShowsFragment extends BaseFragment {

    public static final MediaListType[] tabs = {MediaListType.TV_SHOW_AIRING_TODAY, MediaListType.TV_SHOW_ON_THE_AIR, MediaListType.TV_SHOW_POPULAR, MediaListType.TV_SHOW_TOP_RATED};
    public static final String TAG = TVShowsFragment.class.getSimpleName();
    public static final int DRAWER_POS = 2;
    //private RecyclerView rv;

    public static TVShowsFragment newInstance() {
        return new TVShowsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
        getActivity().setTitle("Tv Shows");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return createViewBind(R.layout.fragment_tvshows, inflater, container, this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        // rv = (RecyclerView) view.findViewById(R.id.recyclerview);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.tvshowsViewPager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tvshowsTabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getActivity().getSupportFragmentManager());

        for (int i = 0; i < tabs.length; i++) {
            adapter.addFragment(TVShowPagerItemFragment.getInstance(i), tabs[i].title);
        }
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }


    }
}