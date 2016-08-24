package com.gl.tmdb.content.network;

import android.util.Log;

import rx.Subscriber;

/**
 * Base class for api subscriber.
 */
public class ApiSubscriber<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        Log.e("ApiSubscriber", "onError", e);
        onFailure(e);
        onCompleted();
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
        onCompleted();
    }

    @Override
    public void onCompleted() {
    }

    public void onSuccess(T response) {
    }

    public void onFailure(Throwable e) {
    }
}
