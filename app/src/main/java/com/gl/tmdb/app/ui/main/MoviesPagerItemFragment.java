package com.gl.tmdb.app.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.gl.tmdb.content.network.responses.PagedResponse;
import com.gl.tmdb.content.network.services.MoviesService;

import java.io.Serializable;
import java.util.List;

import okhttp3.ResponseBody;
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
            en.enqueue(new Callback<PagedResponse<MovieItem>>() {
                @Override
                public void onResponse(Call<PagedResponse<MovieItem>> call, Response<PagedResponse<MovieItem>> response) {
                    if (response.isSuccessful()) {
                        System.out.println("retrofit response size: " + response.body().getResults().size());
                        System.out.println("retrofit response item 0: " + response.body().getResults().get(0).toString());
                        List<MovieItem> results = response.body().getResults();
                        setupRecyclerView(rv, results);
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

    private void setupRecyclerView(RecyclerView recyclerView, List<MovieItem> results) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new OneItemRecyclerViewAdapter(getActivity(), results));
    }

    public static class OneItemRecyclerViewAdapter extends RecyclerView.Adapter<OneItemRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private final Context context;
        private int mBackground;
        private List<MovieItem> mValues;

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

        public OneItemRecyclerViewAdapter(Context context, List<MovieItem> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_movie_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            holder.titleTextView1.setText(mValues.get(position).getTitle());
            holder.mImageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("kliknuty item " + mValues.get(position).getTitle());
                    final Intent i = new Intent(context, MovieDetailActivity.class);
                    i.putExtra(MovieDetailActivity.MOVIE_ITEM_KEY, mValues.get(position));
                    context.startActivity(i);
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
