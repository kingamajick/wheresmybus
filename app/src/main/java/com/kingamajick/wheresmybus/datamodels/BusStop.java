package com.kingamajick.wheresmybus.datamodels;

import android.support.annotation.NonNull;

import java.util.List;

public class BusStop {

    private final String mName;
    private final String mCode;
    private final List<BusRoute> mRoutes;

    public BusStop(@NonNull String name, @NonNull String code, @NonNull List<BusRoute> routes) {
        mName = name;
        mCode = code;
        mRoutes = routes;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getCode() {
        return mCode;
    }

    @NonNull
    public List<BusRoute> getRoutes() {
        return mRoutes;
    }
}
