package com.kingamajick.wheresmybus.util.mvp;

import android.support.annotation.NonNull;

/**
 * Interface describing an model which can have multiple observers.
 */
public interface MVPModel {

    interface ModelObserver {
        void onDataUpdated(MVPModel MVPModel);
    }

    void registerObserver(@NonNull ModelObserver modelObserver);

    void unregisterObserver(@NonNull ModelObserver modelObserver);

}
