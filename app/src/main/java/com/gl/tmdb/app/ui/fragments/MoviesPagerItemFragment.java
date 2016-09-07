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
import com.gl.tmdb.content.model.PersonItem;
import com.gl.tmdb.content.network.responses.PagedResponse;
import com.gl.tmdb.content.network.services.MoviesService;
import com.gl.tmdb.content.network.services.PeopleService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jan.murin on 06-Sep-16.
 */
public class MoviesPagerItemFragment extends BaseFragment {

    public static final String TAG = MoviesPagerItemFragment.class.getSimpleName();
    public static final String TAB_ID = "id";
    private RecyclerView rv;
    private int id;
    private final List<MovieItem> movieItems=new ArrayList<>();

    public static MoviesPagerItemFragment getInstance(int i) {
        MoviesPagerItemFragment mpf = new MoviesPagerItemFragment();
        Bundle args = new Bundle();
        args.putInt(TAB_ID, i);
        mpf.setArguments(args);
        return mpf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate with id: "+id);
       // setRetainInstance(true);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getInt(TAB_ID);
        } else {
            throw new RuntimeException("MoviesPagerItemFragment without arguments!!!");
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy with id: "+id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView with id: "+id);
        return createViewBind(R.layout.fragment_movie_pager_item, inflater, container, this);
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onViewCreated with id: "+id);
        super.onViewCreated(view, savedInstanceState);
        rv = (RecyclerView) view.findViewById(R.id.recyclerview);

        MoviesService api = ApiServices.getMoviesService();
        Call<PagedResponse<MovieItem>> en = null;
        if (MoviesFragment.tabs[id] == MediaListType.MOVIES_POPULAR) {
            en = api.popular("1", "EN");
        }
        if (MoviesFragment.tabs[id] == MediaListType.MOVIES_TOP_RATED) {
            en = api.topRated("1", "EN");
        }
        if (MoviesFragment.tabs[id] == MediaListType.MOVIES_UPCOMING) {
            en = api.upcoming("1", "EN");
        }
        if (MoviesFragment.tabs[id] == MediaListType.MOVIES_NOW_PLAYING) {
            en = api.nowPlaying("1", "EN");
        }
        if (en != null) {
            System.out.println("enqueued callback");
            en.enqueue(new Callback<PagedResponse<MovieItem>>() {
                @Override
                public void onResponse(Call<PagedResponse<MovieItem>> call, Response<PagedResponse<MovieItem>> response) {
                    if (response.isSuccessful()) {
                        System.out.println("retrofit response size: " + response.body().getResults().size());
                        if(response.body().getResults().size()>0) {
                            System.out.println("retrofit response item 0: " + response.body().getResults().get(0).toString());
                            List<MovieItem> results = response.body().getResults();
                            movieItems.addAll(results);
                            setupRecyclerView(rv, movieItems);
                        }
                    } else {
                        System.out.println("response not successfull");
                    }
                }

                @Override
                public void onFailure(Call<PagedResponse<MovieItem>> call, Throwable t) {
                    System.out.println("retrofit error: " + t.getLocalizedMessage());
                }


            });
        }else{
            throw new RuntimeException("EN IS NULL");
        }
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<MovieItem> results) {
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
        MoviesService api = ApiServices.getMoviesService();
        Call<PagedResponse<MovieItem>> en = null;
        if (MoviesFragment.tabs[id] == MediaListType.MOVIES_POPULAR) {
            en = api.popular(""+page, "EN");
        }
        if (MoviesFragment.tabs[id] == MediaListType.MOVIES_TOP_RATED) {
            en = api.topRated(""+page, "EN");
        }
        if (MoviesFragment.tabs[id] == MediaListType.MOVIES_UPCOMING) {
            en = api.upcoming(""+page, "EN");
        }
        if (MoviesFragment.tabs[id] == MediaListType.MOVIES_NOW_PLAYING) {
            en = api.nowPlaying(""+page, "EN");
        }
        if (en != null) {
            en.enqueue(new Callback<PagedResponse<MovieItem>>() {
                @Override
                public void onResponse(Call<PagedResponse<MovieItem>> call, Response<PagedResponse<MovieItem>> response) {
                    if (response.isSuccessful()) {
                        System.out.println("retrofit response size: " + response.body().getResults().size());
                        if(response.body().getResults().size()>0) {
                            System.out.println("retrofit response item 0: " + response.body().getResults().get(0).toString());
                            List<MovieItem> results = response.body().getResults();
                            movieItems.addAll(results);
                            rv.getAdapter().notifyDataSetChanged();
                        }
                    } else {
                        System.out.println("response not successfull");
                    }
                }

                @Override
                public void onFailure(Call<PagedResponse<MovieItem>> call, Throwable t) {
                    System.out.println("retrofit error: " + t.getLocalizedMessage());
                }


            });
        }
    }


}
