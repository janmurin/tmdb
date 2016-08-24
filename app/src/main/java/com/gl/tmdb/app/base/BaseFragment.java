package com.gl.tmdb.app.base;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Base class for all {@link Fragment}s in the application.
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;

    protected <F extends BaseFragment> View createViewBind(int layoutRes, LayoutInflater inflater, ViewGroup container, F childFragment) {
        rootView = inflater.inflate(layoutRes, container, false);
        // bind common UI components
        ButterKnife.bind(childFragment, rootView);
        return rootView;
    }
}
