package com.gl.tmdb.app.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gl.tmdb.R;
import com.gl.tmdb.app.base.BaseFragment;
import com.gl.tmdb.app.ui.adapters.EndlessRecyclerViewScrollListener;
import com.gl.tmdb.app.ui.adapters.OneItemRecyclerViewAdapter;
import com.gl.tmdb.content.ApiServices;
import com.gl.tmdb.content.model.PersonItem;
import com.gl.tmdb.content.network.responses.PagedResponse;
import com.gl.tmdb.content.network.services.PeopleService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jan.murin on 06-Sep-16.
 */
public class PersonsFragment extends BaseFragment {

    public static final String TAG = PersonsFragment.class.getSimpleName();
    public static final int DRAWER_POS = 3;
    private RecyclerView rv;
    public final List<PersonItem> personItems = new ArrayList<>();

    public static PersonsFragment newInstance() {
        return new PersonsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getActivity().setTitle("Popular persons");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createViewBind(R.layout.fragment_persons, inflater, container, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = (RecyclerView) view.findViewById(R.id.recyclerview);

        PeopleService peopleService = ApiServices.getPeopleService();
        Call<PagedResponse<PersonItem>> en = peopleService.popular("1");
        en.enqueue(new Callback<PagedResponse<PersonItem>>() {
            @Override
            public void onResponse(Call<PagedResponse<PersonItem>> call, Response<PagedResponse<PersonItem>> response) {
                System.out.println("retrofit response size: " + response.body().getResults().size());
                if(response.body().getResults().size()>0) {
                    System.out.println("retrofit response item 0: " + response.body().getResults().get(0).toString());
                    List<PersonItem> results = response.body().getResults();
                    personItems.addAll(results);
                    setupRecyclerView(rv, personItems);
                }
            }

            @Override
            public void onFailure(Call<PagedResponse<PersonItem>> call, Throwable t) {
                System.out.println("retrofit error: " + t.getLocalizedMessage());
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<PersonItem> results) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        recyclerView.setAdapter(new OneItemRecyclerViewAdapter(getActivity(), results));
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener((GridLayoutManager) recyclerView.getLayoutManager()) {
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
        PeopleService peopleService = ApiServices.getPeopleService();
        Call<PagedResponse<PersonItem>> en = peopleService.popular("" + page);
        en.enqueue(new Callback<PagedResponse<PersonItem>>() {
            @Override
            public void onResponse(Call<PagedResponse<PersonItem>> call, Response<PagedResponse<PersonItem>> response) {
                System.out.println("retrofit response size: " + response.body().getResults().size());
                if(response.body().getResults().size()>0) {
                    System.out.println("retrofit response item 0: " + response.body().getResults().get(0).toString());
                    List<PersonItem> results = response.body().getResults();
                    personItems.addAll(results);
                    //setupRecyclerView(rv, personItems);
                    rv.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PagedResponse<PersonItem>> call, Throwable t) {
                System.out.println("retrofit error: " + t.getLocalizedMessage());
            }
        });
    }


}
