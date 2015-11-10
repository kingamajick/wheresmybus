package com.kingamajick.wheresmybus.util.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * All callbacks to the listener will be called on the main thread
 *
 * @param <T>
 */
public class BaseMVPView<T> implements MVPView<T> {

    @Nullable
    public T mListener;

    @Override
    public void setListener(@NonNull T listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }
        if (mListener != null && mListener != listener) {
            throw new IllegalStateException("Attempt to register listener before previous listener unregister");
        }
        mListener = listener;
    }

    @Override
    public void removeListener(@NonNull T listener) {
        if (listener == null) {
            throw new IllegalArgumentException("listener must not be null");
        }
        if (mListener != null && mListener != listener) {
            throw new IllegalStateException("Attempt to register listener which hasn't been previously registered");
        }
        mListener = null;
    }

    @Override
    public boolean hasListener() {
        return mListener != null;
    }

    @Override
    @Nullable
    public T getListener() {
        return mListener;
    }
}
