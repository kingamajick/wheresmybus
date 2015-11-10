package com.kingamajick.wheresmybus.nearby;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingamajick.wheresmybus.R;
import com.kingamajick.wheresmybus.util.lifecycle.LifecycleDelegator;

public class NearbyBusesMapFragment extends Fragment {

    public static NearbyBusesMapFragment newInstance() {
        return new NearbyBusesMapFragment();
    }

    private final LifecycleDelegator mLifecycleDelegator = new LifecycleDelegator();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_nearby_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();

        mLifecycleDelegator.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

        mLifecycleDelegator.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mLifecycleDelegator.onDestroy();
    }
}
