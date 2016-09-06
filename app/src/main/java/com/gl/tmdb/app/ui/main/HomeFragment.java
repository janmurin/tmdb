package com.gl.tmdb.app.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gl.tmdb.R;
import com.gl.tmdb.app.base.BaseFragment;
import com.gl.tmdb.content.ApiServices;
import com.gl.tmdb.content.model.MediaListType;
import com.gl.tmdb.content.model.MediaType;
import com.gl.tmdb.content.model.MovieItem;
import com.gl.tmdb.content.model.PersonItem;
import com.gl.tmdb.content.model.TvShowItem;
import com.gl.tmdb.content.network.responses.PagedResponse;
import com.gl.tmdb.content.network.services.MoviesService;
import com.gl.tmdb.content.network.services.PeopleService;
import com.gl.tmdb.content.network.services.TvShowService;
import com.gl.tmdb.events.eventbus.EventBusEvents;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
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

    public static class ThreeItemRecyclerViewAdapter extends RecyclerView.Adapter<ThreeItemRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private final List<MediaListType> categories;
        private final Context context;
        private int mBackground;
        private Map<MediaListType, List<ThreeItemRecyclerViewItem>> mValues;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final ImageView mImageView2;
            public final ImageView mImageView3;
            public final TextView titleTextView2;
            public final TextView titleTextView3;
            public final ImageView mImageView1;
            public final TextView titleTextView1;
            private final TextView headerTextView;
            private final TextView viewAllTextView;

            public ViewHolder(View view) {
                super(view);
                mImageView1 = (ImageView) view.findViewById(R.id.imageView1);
                mImageView2 = (ImageView) view.findViewById(R.id.imageView2);
                mImageView3 = (ImageView) view.findViewById(R.id.imageView3);
                headerTextView = (TextView) view.findViewById(R.id.headerTextView);
                viewAllTextView = (TextView) view.findViewById(R.id.viewAllTextView);
                titleTextView1 = (TextView) view.findViewById(R.id.titleTextView1);
                titleTextView2 = (TextView) view.findViewById(R.id.titleTextView2);
                titleTextView3 = (TextView) view.findViewById(R.id.titleTextView3);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + titleTextView1.getText();
            }
        }

        //public MovieItem getValueAt(int position) {
//            return mValues.get(position);
//        }

        public ThreeItemRecyclerViewAdapter(Context context, Map<MediaListType, List<ThreeItemRecyclerViewItem>> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
            categories = new ArrayList<>();
            for (MediaListType mlt : items.keySet()) {
                categories.add(mlt);
            }
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.three_movies_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.headerTextView.setText(categories.get(position).title);
            holder.viewAllTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("view all for category: " + categories.get(position));
                    EventBus.getDefault().post(new EventBusEvents.ViewAllEvent(categories.get(position)));
                }
            });


            holder.titleTextView1.setText(mValues.get(categories.get(position)).get(0).getTitle());
            holder.titleTextView2.setText(mValues.get(categories.get(position)).get(1).getTitle());
            holder.titleTextView3.setText(mValues.get(categories.get(position)).get(2).getTitle());

            holder.mImageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("position " + position + " kliknuty item " + mValues.get(categories.get(position)).get(0));
                    startDetailActivity(categories.get(position), mValues.get(categories.get(position)).get(0));
                }
            });
            holder.mImageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("position " + position + " kliknuty item " + mValues.get(categories.get(position)).get(1));
                    startDetailActivity(categories.get(position), mValues.get(categories.get(position)).get(1));
                }
            });
            holder.mImageView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("position " + position + " kliknuty item " + mValues.get(categories.get(position)).get(2));
                    startDetailActivity(categories.get(position), mValues.get(categories.get(position)).get(2));
                }
            });

            Glide.with(holder.mImageView1.getContext())
                    .load("http://image.tmdb.org/t/p/w500" + mValues.get(categories.get(position)).get(0).getPosterPath())
                    .centerCrop()
                    .into(holder.mImageView1);
            Glide.with(holder.mImageView2.getContext())
                    .load("http://image.tmdb.org/t/p/w500" + mValues.get(categories.get(position)).get(1).getPosterPath())
                    .centerCrop()
                    .into(holder.mImageView2);
            Glide.with(holder.mImageView3.getContext())
                    .load("http://image.tmdb.org/t/p/w500" + mValues.get(categories.get(position)).get(2).getPosterPath())
                    .centerCrop()
                    .into(holder.mImageView3);
        }

        private void startDetailActivity(MediaListType mediaListType, ThreeItemRecyclerViewItem threeItemRecyclerViewItem) {
            if (mediaListType.mediaType == MediaType.MOVIE) {
                final Intent i = new Intent(context, MovieDetailActivity.class);
                System.out.println("ukladam do intentu movie item: " + threeItemRecyclerViewItem.getMovieItem());
                i.putExtra(MovieDetailActivity.MOVIE_ITEM_KEY, threeItemRecyclerViewItem.getMovieItem());
                context.startActivity(i);
            }
            if (mediaListType.mediaType == MediaType.TV_SHOW) {
//                final Intent i = new Intent(context, MovieDetailActivity.class);
//                i.putExtra(MovieDetailActivity.MOVIE_ITEM_KEY, threeItemRecyclerViewItem.getMovieItem());
//                context.startActivity(i);
            }
            if (mediaListType.mediaType == MediaType.PERSON) {
//                final Intent i = new Intent(context, MovieDetailActivity.class);
//                i.putExtra(MovieDetailActivity.MOVIE_ITEM_KEY, threeItemRecyclerViewItem.getMovieItem());
//                context.startActivity(i);
            }
        }

        @Override
        public int getItemCount() {
            return mValues.keySet().size();
        }


    }


}
