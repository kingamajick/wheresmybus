package com.kingamajick.wheresmybus.util;

import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

public abstract class ViewFinder {

    public static ViewFinder create(@NonNull final Window window) {
        return new ViewFinder() {
            @Override
            public <T extends View> T findOptionalViewById(@IdRes int viewId) {
                //noinspection unchecked
                return (T) window.findViewById(viewId);
            }
        };
    }

    public static ViewFinder create(@NonNull final View view) {
        return new ViewFinder() {
            @Override
            public <T extends View> T findOptionalViewById(@IdRes int viewId) {
                //noinspection unchecked
                return (T) view.findViewById(viewId);
            }
        };
    }

    /**
     * Attempts to find a view for a give Id.
     *
     * @param viewId the id of the view to find.
     * @param <T>    the implementation of view.
     * @return the view if it is found.
     * @throws android.content.res.Resources.NotFoundException if the view cannot be found.
     */
    @NonNull
    public final <T extends View> T findViewById(@IdRes int viewId) {
        T view = findOptionalViewById(viewId);
        if (view == null) {
            throw new Resources.NotFoundException("Unable to find view for " + viewId);
        }
        return view;
    }

    @Nullable
    public abstract <T extends View> T findOptionalViewById(@IdRes int viewId);

}
