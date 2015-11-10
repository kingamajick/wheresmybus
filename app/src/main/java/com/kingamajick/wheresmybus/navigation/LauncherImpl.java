package com.kingamajick.wheresmybus.navigation;

import android.app.Activity;
import android.content.Intent;

import com.kingamajick.wheresmybus.nearby.NearbyBusesActivity;

public class LauncherImpl implements Launcher {

    private final Activity mActivity;

    public LauncherImpl(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void nearbyBuses() {
        mActivity.startActivity(new Intent(mActivity, NearbyBusesActivity.class));
    }
}
