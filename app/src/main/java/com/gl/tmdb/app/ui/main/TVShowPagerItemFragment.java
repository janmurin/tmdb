package com.gl.tmdb.app.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gl.tmdb.R;
import com.gl.tmdb.app.base.BaseFragment;
import com.gl.tmdb.content.ApiServices;
import com.gl.tmdb.content.model.MediaListType;
import com.gl.tmdb.content.model.MovieItem;
import com.gl.tmdb.content.model.TvShowItem;
import com.gl.tmdb.content.network.responses.PagedResponse;
import com.gl.tmdb.content.network.services.MoviesService;
import com.gl.tmdb.content.network.services.TvShowService;

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

    public static TVShowPagerItemFragment getInstance(int i) {
        TVShowPagerItemFragment mpf = new TVShowPagerItemFragment();
        Bundle args = new Bundle();
        args.putInt(TAB_ID, i);
        mpf.setArguments(args);
        return mpf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Bundle args = getArguments();
        if (args != null) {
            id = args.getInt(TAB_ID);
        } else {
            throw new RuntimeException("MoviesPagerItemFragment without arguments!!!");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createViewBind(R.layout.fragment_movie_pager_item, inflater, container, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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
            en.enqueue(new Callback<PagedResponse<TvShowItem>>() {
                @Override
                public void onResponse(Call<PagedResponse<TvShowItem>> call, Response<PagedResponse<TvShowItem>> response) {
                    System.out.println("retrofit response size: " + response.body().getResults().size());
                    System.out.println("retrofit response item 0: " + response.body().getResults().get(0).toString());
                    List<TvShowItem> results = response.body().getResults();
                    setupRecyclerView(rv, results);
                }

                @Override
                public void onFailure(Call<PagedResponse<TvShowItem>> call, Throwable t) {
                    System.out.println("retrofit error: " + t.getLocalizedMessage());
                }
            });
        }
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<TvShowItem> results) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new OneItemRecyclerViewAdapter(getActivity(), results));
    }

    public static class OneItemRecyclerViewAdapter extends RecyclerView.Adapter<OneItemRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<TvShowItem> mValues;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final ImageView mImageView1;
            public final TextView titleTextView1;

            public ViewHolder(View view) {
                super(view);
                mImageView1 = (ImageView) view.findViewById(R.id.imageView1);
                titleTextView1 = (TextView) view.findViewById(R.id.titleTextView1);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + titleTextView1.getText();
            }
        }

        public OneItemRecyclerViewAdapter(Context context, List<TvShowItem> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_movie_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            holder.titleTextView1.setText(mValues.get(position).getName());
            holder.mImageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("kliknuty item " + mValues.get(position).getName());
                }
            });

            Glide.with(holder.mImageView1.getContext())
                    .load("http://image.tmdb.org/t/p/w500" + mValues.get(position).getPosterPath())
                    .centerCrop()
                    .into(holder.mImageView1);


        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

}
