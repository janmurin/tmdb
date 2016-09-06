package com.gl.tmdb.events.eventbus;

import com.gl.tmdb.content.model.MediaListType;

/**
 * Created by jan.murin on 06-Sep-16.
 */
public final class EventBusEvents {


    public static class ViewAllEvent {
        public final MediaListType mediaListType;

        public ViewAllEvent(MediaListType mediaListType) {
            this.mediaListType=mediaListType;
        }
    }
}
