package com.kingamajick.wheresmybus.util.lifecycle;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class LifecycleDelegator implements CreateDestroyCallback, StartStopCallback {

    private boolean mCreated = false;
    private boolean mStarted = false;
    private List<CreateDestroyCallback> mCreateDestroyCallbacks = new ArrayList<>();
    private List<StartStopCallback> mStartStopCallbacks = new ArrayList<>();

    @Override
    public void onCreate() {
        mCreated = true;
        mStarted = false;
        for (CreateDestroyCallback callback : mCreateDestroyCallbacks) {
            callback.onCreate();
        }
    }

    @Override
    public void onStart() {
        mStarted = true;
        for (StartStopCallback callback : mStartStopCallbacks) {
            callback.onStart();
        }
    }

    @Override
    public void onStop() {
        for (StartStopCallback callback : mStartStopCallbacks) {
            callback.onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (CreateDestroyCallback callback : mCreateDestroyCallbacks) {
            callback.onDestroy();
        }
    }

    public void registerLifecycleCallback(@NonNull LifecycleCallback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }

        if (callback instanceof CreateDestroyCallback) {
            CreateDestroyCallback createDestroyCallback = (CreateDestroyCallback) callback;
            mCreateDestroyCallbacks.add(createDestroyCallback);
            if (mCreated) {
                createDestroyCallback.onCreate();
            }
        }

        if (callback instanceof StartStopCallback) {
            StartStopCallback startStopCallback = (StartStopCallback) callback;
            mStartStopCallbacks.add(startStopCallback);
            if (mStarted) {
                startStopCallback.onStart();
            }
        }
    }
}
