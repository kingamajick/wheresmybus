package com.kingamajick.wheresmybus.util.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic implementation of {@link MVPModel}, tt is expected that implementations will call {@link #notifyObservers()} on the main thread.
 */
public class BaseMVPModel implements MVPModel {

    public final List<ModelObserver> mModelObservers = new ArrayList<>();

    @Override
    public void registerObserver(@NonNull ModelObserver modelObserver) {
        if (modelObserver == null) {
            throw new IllegalArgumentException("ModelObserver must not be null");
        }
        mModelObservers.add(modelObserver);
    }

    @Override
    public void unregisterObserver(@NonNull ModelObserver modelObserver) {
        if (modelObserver == null) {
            throw new IllegalArgumentException("ModelObserver must not be null");
        }
        if (!mModelObservers.contains(modelObserver)) {
            throw new IllegalArgumentException("Trying to unregister modelObserver which isn't registered");
        }
        mModelObservers.remove(modelObserver);
    }

    /**
     * Notify any interested observers.
     */
    @UiThread
    protected void notifyObservers() {
        for (int i = mModelObservers.size() - 1; i >= 0; i--) {
            mModelObservers.get(i).onDataUpdated(this);
        }
    }
}
