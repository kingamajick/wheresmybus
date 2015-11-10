package com.kingamajick.wheresmybus.datamodels;

public class BusRoute {

    private final String mName;
    private final String mTowards;
    private final long mNext;

    public BusRoute(String name, String towards, long next) {
        mName = name;
        mTowards = towards;
        mNext = next;
    }

    public String getName() {
        return mName;
    }

    public String getTowards() {
        return mTowards;
    }

    public long getNext() {
        return mNext;
    }
}
