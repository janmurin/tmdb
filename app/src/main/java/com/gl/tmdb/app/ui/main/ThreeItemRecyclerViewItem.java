package com.gl.tmdb.app.ui.main;

import com.gl.tmdb.content.model.MovieItem;
import com.gl.tmdb.content.model.PersonItem;
import com.gl.tmdb.content.model.TvShowItem;

/**
 * Created by jan.murin on 05-Sep-16.
 */
public class ThreeItemRecyclerViewItem {

    private final String title;
    private final Integer id;
    private final String posterPath;

    public ThreeItemRecyclerViewItem(MovieItem movieItem) {
        this.title = movieItem.getOriginalTitle();
        this.id = movieItem.getId();
        this.posterPath = movieItem.getPosterPath();
    }

    public ThreeItemRecyclerViewItem(TvShowItem tvShowItem) {
        this.title = tvShowItem.getName();
        this.id = tvShowItem.getId();
        this.posterPath = tvShowItem.getPosterPath();
    }

    public ThreeItemRecyclerViewItem(PersonItem personItem) {
        this.title = personItem.getName();
        this.id = personItem.getId();
        this.posterPath = personItem.getProfilePath();
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Integer getId() {
        return id;
    }
}
