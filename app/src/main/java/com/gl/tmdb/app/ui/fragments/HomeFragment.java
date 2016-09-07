package com.gl.tmdb.app.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gl.tmdb.R;
import com.gl.tmdb.app.base.BaseFragment;
import com.gl.tmdb.app.ui.adapters.ThreeItemRecyclerViewAdapter;
import com.gl.tmdb.app.ui.adapters.ThreeItemRecyclerViewItem;
import com.gl.tmdb.content.ApiServices;
import com.gl.tmdb.content.model.MediaListType;
import com.gl.tmdb.content.model.MovieItem;
import com.gl.tmdb.content.model.PersonItem;
import com.gl.tmdb.content.model.TvShowItem;
import com.gl.tmdb.content.network.responses.PagedResponse;
import com.gl.tmdb.content.network.services.MoviesService;
import com.gl.tmdb.content.network.services.PeopleService;
import com.gl.tmdb.content.network.services.TvShowService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Home content Fragment.
 */
public final class HomeFragment extends BaseFragment {

    public static final String TAG = "HomeFragment";
    public static final int DRAWER_POS = 0;
    private RecyclerView rv;
    private Map<MediaListType, List<ThreeItemRecyclerViewItem>> listMap;
    private int gateCounter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getActivity().setTitle("Home");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createViewBind(R.layout.fragment_home, inflater, container, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = (RecyclerView) view.findViewById(R.id.recyclerview);
        listMap = new HashMap<>();
        gateCounter = 3;
        MoviesService api = ApiServices.getMoviesService();
        Call<PagedResponse<MovieItem>> en = api.nowPlaying("1", "EN");
        en.enqueue(new Callback<PagedResponse<MovieItem>>() {
            @Override
            public void onResponse(Call<PagedResponse<MovieItem>> call, Response<PagedResponse<MovieItem>> response) {
                System.out.println("retrofit response size: " + response.body().getResults().size());
                System.out.println("retrofit response item 0: " + response.body().getResults().get(0).toString());
                listMap.put(MediaListType.MOVIES_NOW_PLAYING, new ArrayList<ThreeItemRecyclerViewItem>());
                listMap.get(MediaListType.MOVIES_NOW_PLAYING).add(new ThreeItemRecyclerViewItem(response.body().getResults().get(0)));
                listMap.get(MediaListType.MOVIES_NOW_PLAYING).add(new ThreeItemRecyclerViewItem(response.body().getResults().get(1)));
                listMap.get(MediaListType.MOVIES_NOW_PLAYING).add(new ThreeItemRecyclerViewItem(response.body().getResults().get(2)));
                //setupRecyclerView(rv, filmy);
                if (listMap.keySet().size() == gateCounter) {
                    // mame vsetky
                    setupRecyclerView(rv, listMap);
                }
            }

            @Override
            public void onFailure(Call<PagedResponse<MovieItem>> call, Throwable t) {
                System.out.println("retrofit error: " + t.getLocalizedMessage());
            }
        });

        TvShowService api2 = ApiServices.getTvShowService();
        Call<PagedResponse<TvShowItem>> en2 = api2.airingToday("1", "EN");
        en2.enqueue(new Callback<PagedResponse<TvShowItem>>() {
            @Override
            public void onResponse(Call<PagedResponse<TvShowItem>> call, Response<PagedResponse<TvShowItem>> response) {
                System.out.println("retrofit response size: " + response.body().getResults().size());
                System.out.println("retrofit response item 0: " + response.body().getResults().get(0).toString());
                listMap.put(MediaListType.TV_SHOW_AIRING_TODAY, new ArrayList<ThreeItemRecyclerViewItem>());
                listMap.get(MediaListType.TV_SHOW_AIRING_TODAY).add(new ThreeItemRecyclerViewItem(response.body().getResults().get(0)));
                listMap.get(MediaListType.TV_SHOW_AIRING_TODAY).add(new ThreeItemRecyclerViewItem(response.body().getResults().get(1)));
                listMap.get(MediaListType.TV_SHOW_AIRING_TODAY).add(new ThreeItemRecyclerViewItem(response.body().getResults().get(2)));
                //setupRecyclerView(rv, filmy);
                if (listMap.keySet().size() == gateCounter) {
                    // mame vsetky
                    setupRecyclerView(rv, listMap);
                }
            }

            @Override
            public void onFailure(Call<PagedResponse<TvShowItem>> call, Throwable t) {
                System.out.println("retrofit error: " + t.getLocalizedMessage());
            }
        });

        PeopleService api3 = ApiServices.getPeopleService();
        Call<PagedResponse<PersonItem>> en3 = api3.popular("1");
        en3.enqueue(new Callback<PagedResponse<PersonItem>>() {
            @Override
            public void onResponse(Call<PagedResponse<PersonItem>> call, Response<PagedResponse<PersonItem>> response) {
                System.out.println("retrofit response size: " + response.body().getResults().size());
                System.out.println("retrofit response item 0: " + response.body().getResults().get(0).toString());
                listMap.put(MediaListType.POPULAR_PEOPLE, new ArrayList<ThreeItemRecyclerViewItem>());
                listMap.get(MediaListType.POPULAR_PEOPLE).add(new ThreeItemRecyclerViewItem(response.body().getResults().get(0)));
                listMap.get(MediaListType.POPULAR_PEOPLE).add(new ThreeItemRecyclerViewItem(response.body().getResults().get(1)));
                listMap.get(MediaListType.POPULAR_PEOPLE).add(new ThreeItemRecyclerViewItem(response.body().getResults().get(2)));
                //setupRecyclerView(rv, filmy);
                if (listMap.keySet().size() == gateCounter) {
                    // mame vsetky
                    setupRecyclerView(rv, listMap);
                }
            }

            @Override
            public void onFailure(Call<PagedResponse<PersonItem>> call, Throwable t) {
                System.out.println("retrofit error: " + t.getLocalizedMessage());
            }
        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//        Log.d(TAG, "onStart");
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }

    private void setupRecyclerView(RecyclerView recyclerView, Map<MediaListType, List<ThreeItemRecyclerViewItem>> results) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new ThreeItemRecyclerViewAdapter(getActivity(), results));
    }




}
