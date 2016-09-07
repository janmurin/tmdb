package com.gl.tmdb.app.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gl.tmdb.R;
import com.gl.tmdb.app.base.BaseFragment;
import com.gl.tmdb.app.ui.adapters.EndlessRecyclerViewScrollListener;
import com.gl.tmdb.app.ui.adapters.OneItemRecyclerViewAdapter;
import com.gl.tmdb.content.ApiServices;
import com.gl.tmdb.content.model.MediaListType;
import com.gl.tmdb.content.model.MovieItem;
import com.gl.tmdb.content.model.TvShowItem;
import com.gl.tmdb.content.network.responses.PagedResponse;
import com.gl.tmdb.content.network.services.MoviesService;
import com.gl.tmdb.content.network.services.TvShowService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jan.murin on 06-Sep-16.
 */
public class TVShowPagerItemFragment extends BaseFragment {

    public static final String TAG = TVShowPagerItemFragment.class.getSimpleName();
    public static final String TAB_ID = "id";
    private RecyclerView rv;
    private int id;
    private final List<TvShowItem> tvShowItems = new ArrayList<>();

    public static TVShowPagerItemFragment getInstance(int i) {
        TVShowPagerItemFragment mpf = new TVShowPagerItemFragment();
        Bundle args = new Bundle();
        args.putInt(TAB_ID, i);
        mpf.setArguments(args);
        return mpf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreate with id: "+id);
        super.onCreate(savedInstanceState);
       // setRetainInstance(true);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getInt(TAB_ID);
        } else {
            throw new RuntimeException("without arguments!!!");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy with id: "+id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach with id: "+id);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG,"onDetach with id: "+id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView with id: "+id);
        return createViewBind(R.layout.fragment_movie_pager_item, inflater, container, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onViewCreated with id: "+id);
        super.onViewCreated(view, savedInstanceState);
        rv = (RecyclerView) view.findViewById(R.id.recyclerview);

        TvShowService api = ApiServices.getTvShowService();
        Call<PagedResponse<TvShowItem>> en = null;
        if (TVShowsFragment.tabs[id] == MediaListType.TV_SHOW_TOP_RATED) {
            en = api.topRated("1", "EN");
        }
        if (TVShowsFragment.tabs[id] == MediaListType.TV_SHOW_POPULAR) {
            en = api.popular("1", "EN");
        }
        if (TVShowsFragment.tabs[id] == MediaListType.TV_SHOW_ON_THE_AIR) {
            en = api.onTheAir("1", "EN");
        }
        if (TVShowsFragment.tabs[id] == MediaListType.TV_SHOW_AIRING_TODAY) {
            en = api.airingToday("1", "EN");
        }
        if (en != null) {
            System.out.println("enqueued callback");
            en.enqueue(new Callback<PagedResponse<TvShowItem>>() {
                @Override
                public void onResponse(Call<PagedResponse<TvShowItem>> call, Response<PagedResponse<TvShowItem>> response) {
                    System.out.println("retrofit response size: " + response.body().getResults().size());
                    if(response.body().getResults().size()>0) {
                        System.out.println("retrofit response item 0: " + response.body().getResults().get(0).toString());
                        List<TvShowItem> results = response.body().getResults();
                        tvShowItems.addAll(results);
                        setupRecyclerView(rv, tvShowItems);
                    }
                }

                @Override
                public void onFailure(Call<PagedResponse<TvShowItem>> call, Throwable t) {
                    System.out.println("retrofit error: " + t.getLocalizedMessage());
                }
            });
        } else {
            throw new RuntimeException("en is null");
        }
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<TvShowItem> results) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new OneItemRecyclerViewAdapter(getActivity(), results));
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                customLoadMoreDataFromApi(page);
            }
        });
    }

    // Append more data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void customLoadMoreDataFromApi(int page) {
        // Send an API request to retrieve appropriate data using the offset value as a parameter.
        //  --> Deserialize API response and then construct new objects to append to the adapter
        //  --> Notify the adapter of the changes
        page++;
        System.out.println("loading page " + page);
        TvShowService api = ApiServices.getTvShowService();
        Call<PagedResponse<TvShowItem>> en = null;
        if (TVShowsFragment.tabs[id] == MediaListType.TV_SHOW_TOP_RATED) {
            en = api.topRated("" + page, "EN");
        }
        if (TVShowsFragment.tabs[id] == MediaListType.TV_SHOW_POPULAR) {
            en = api.popular("" + page, "EN");
        }
        if (TVShowsFragment.tabs[id] == MediaListType.TV_SHOW_ON_THE_AIR) {
            en = api.onTheAir("" + page, "EN");
        }
        if (TVShowsFragment.tabs[id] == MediaListType.TV_SHOW_AIRING_TODAY) {
            en = api.airingToday("" + page, "EN");
        }
        if (en != null) {
            en.enqueue(new Callback<PagedResponse<TvShowItem>>() {
                @Override
                public void onResponse(Call<PagedResponse<TvShowItem>> call, Response<PagedResponse<TvShowItem>> response) {
                    System.out.println("retrofit response size: " + response.body().getResults().size());
                    if(response.body().getResults().size()>0) {
                        System.out.println("retrofit response item 0: " + response.body().getResults().get(0).toString());
                        List<TvShowItem> results = response.body().getResults();
                        tvShowItems.addAll(results);
                        rv.getAdapter().notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<PagedResponse<TvShowItem>> call, Throwable t) {
                    System.out.println("retrofit error: " + t.getLocalizedMessage());
                }
            });
        } else {
            System.out.println("en is null");
        }
    }


}
