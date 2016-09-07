package com.gl.tmdb.app.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gl.tmdb.R;
import com.gl.tmdb.app.ui.main.MovieDetailActivity;
import com.gl.tmdb.app.ui.main.PersonDetailActivity;
import com.gl.tmdb.app.ui.main.TvShowDetailActivity;
import com.gl.tmdb.content.model.MediaListType;
import com.gl.tmdb.content.model.MediaType;
import com.gl.tmdb.content.model.TvShowItem;
import com.gl.tmdb.events.eventbus.EventBusEvents;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jan.murin on 07-Sep-16.
 */
public class ThreeItemRecyclerViewAdapter extends RecyclerView.Adapter<ThreeItemRecyclerViewAdapter.ViewHolder> {

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
            final Intent i = new Intent(context, TvShowDetailActivity.class);
            i.putExtra(TvShowDetailActivity.TVSHOW_ITEM_KEY, threeItemRecyclerViewItem.getTvShowItem());
            context.startActivity(i);
        }
        if (mediaListType.mediaType == MediaType.PERSON) {
                final Intent i = new Intent(context, PersonDetailActivity.class);
                i.putExtra(PersonDetailActivity.PERSON_ITEM_KEY, threeItemRecyclerViewItem.getPersonItem());
                context.startActivity(i);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.keySet().size();
    }


}
