package com.kingamajick.wheresmybus.util.lifecycle;

/**
 * User to register interest in activity or fragments start stop methods.
 */
public interface StartStopCallback extends LifecycleCallback {

    void onStart();

    void onStop();
}
