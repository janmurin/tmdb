package com.gl.tmdb.events.network;

/**
 * Event triggered when network status changes.
 */
public final class ENetworkStatusChange {

    public final boolean connected;

    public ENetworkStatusChange(boolean connected) {
        this.connected = connected;
    }
}
