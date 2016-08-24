package com.gl.tmdb.content.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents paged list of item response returned by API call.
 */
public class PagedResponse<T> {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<T> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public List<T> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
