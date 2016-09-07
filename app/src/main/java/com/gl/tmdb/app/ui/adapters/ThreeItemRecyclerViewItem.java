package com.gl.tmdb.app.ui.adapters;

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
    private final PersonItem personItem;
    private final MovieItem movieItem;
    private final TvShowItem tvShowItem;

    public ThreeItemRecyclerViewItem(MovieItem movieItem) {
        this.title = movieItem.getOriginalTitle();
        this.id = movieItem.getId();
        this.posterPath = movieItem.getPosterPath();
        this.personItem = null;
        this.movieItem = movieItem;
        this.tvShowItem = null;
    }

    public ThreeItemRecyclerViewItem(TvShowItem tvShowItem) {
        this.title = tvShowItem.getName();
        this.id = tvShowItem.getId();
        this.posterPath = tvShowItem.getPosterPath();
        this.personItem = null;
        this.movieItem = null;
        this.tvShowItem = tvShowItem;
    }

    public ThreeItemRecyclerViewItem(PersonItem personItem) {
        this.title = personItem.getName();
        this.id = personItem.getId();
        this.posterPath = personItem.getProfilePath();
        this.personItem = personItem;
        this.movieItem = null;
        this.tvShowItem = null;
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

    public PersonItem getPersonItem() {
        return personItem;
    }

    public MovieItem getMovieItem() {
        return movieItem;
    }

    public TvShowItem getTvShowItem() {
        return tvShowItem;
    }
}
