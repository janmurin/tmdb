package com.gl.tmdb.app.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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
import com.gl.tmdb.content.model.MediaItem;
import com.gl.tmdb.content.model.MovieItem;
import com.gl.tmdb.content.model.PersonItem;
import com.gl.tmdb.content.model.TvShowItem;

import java.util.List;

/**
 * Created by jan.murin on 07-Sep-16.
 */
public class OneItemRecyclerViewAdapter extends RecyclerView.Adapter<OneItemRecyclerViewAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private final Context context;
    private int mBackground;
    private List<?> mValues;

    public OneItemRecyclerViewAdapter(Context context, List<?> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mValues = items;
        this.context = context;
    }

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


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_movie_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Object o = mValues.get(position);
        final String title;
        String posterPath = null;
        if (o instanceof TvShowItem) {
            TvShowItem tvShowItem = (TvShowItem) o;
            title = tvShowItem.getName();
            posterPath = tvShowItem.getPosterPath();
        } else if (o instanceof MovieItem) {
            MovieItem movieItem = (MovieItem) o;
            title = movieItem.getTitle();
            posterPath = movieItem.getPosterPath();
        } else if (o instanceof PersonItem) {
            PersonItem personItem = (PersonItem) o;
            title = personItem.getName();
            posterPath = personItem.getProfilePath();
        } else {
            title = "no title";
        }
        holder.titleTextView1.setText(title);
        holder.mImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("kliknuty item " + title);
                if (o instanceof MovieItem) {
                    final Intent i = new Intent(context, MovieDetailActivity.class);
                    System.out.println("ukladam do intentu movie item: " + o);
                    i.putExtra(MovieDetailActivity.MOVIE_ITEM_KEY, (MovieItem) o);
                    context.startActivity(i);
                }
                if (o instanceof PersonItem) {
                    final Intent i = new Intent(context, PersonDetailActivity.class);
                    i.putExtra(PersonDetailActivity.PERSON_ITEM_KEY, (PersonItem) o);
                    context.startActivity(i);
                }
                if (o instanceof TvShowItem) {
                    final Intent i = new Intent(context, TvShowDetailActivity.class);
                    i.putExtra(TvShowDetailActivity.TVSHOW_ITEM_KEY, (TvShowItem) o);
                    context.startActivity(i);
                }
            }
        });

        Glide.with(holder.mImageView1.getContext())
                .load("http://image.tmdb.org/t/p/w500" + posterPath)
                .centerCrop()
                .into(holder.mImageView1);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}