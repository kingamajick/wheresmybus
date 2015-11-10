package com.kingamajick.wheresmybus.util.lifecycle;

/**
 * User to register interest in activity or fragments create destroy methods.
 */
public interface CreateDestroyCallback extends LifecycleCallback {

    void onCreate();

    void onDestroy();
}
