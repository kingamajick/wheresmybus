package com.kingamajick.wheresmybus.util.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Describes a view which may have a single listener registered to it.
 *
 * @param <T> the listener type.
 */
public interface MVPView<T> {

    /**
     * Set the listener on the view.
     *
     * @param listener
     */
    void setListener(@NonNull T listener);

    /**
     * Remove the previously added listener from this view.
     *
     * @param listener
     */
    void removeListener(@NonNull T listener);

    /**
     * @return <code>true</code> if a listener is currently registered with the view.
     */
    boolean hasListener();

    /**
     * @return the registered listener if one exists.
     */
    @Nullable
    T getListener();
}
