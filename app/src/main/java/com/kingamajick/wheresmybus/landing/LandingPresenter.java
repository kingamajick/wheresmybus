package com.kingamajick.wheresmybus.landing;

import android.util.Log;

import com.kingamajick.wheresmybus.datamodels.BusRoute;
import com.kingamajick.wheresmybus.datamodels.BusStop;
import com.kingamajick.wheresmybus.navigation.Launcher;
import com.kingamajick.wheresmybus.util.lifecycle.StartStopCallback;
import com.kingamajick.wheresmybus.util.mvp.MVPModel;

public class LandingPresenter implements StartStopCallback, MVPModel.ModelObserver, LandingView.Listener {

    private final LandingView mView;
    private final LandingModel mModel;
    private Launcher mLauncher;

    public LandingPresenter(LandingView view, LandingModel model, Launcher launcher) {
        mView = view;
        mModel = model;
        mLauncher = launcher;
    }

    @Override
    public void onStart() {
        mView.setListener(this);
        mModel.registerObserver(this);
        onDataUpdated(mModel);
    }

    @Override
    public void onStop() {
        mModel.unregisterObserver(this);
        mView.removeListener(this);
    }

    @Override
    public void onDataUpdated(MVPModel MVPModel) {
        mView.displayFavourites(mModel.getFavouriteStops());
    }

    @Override
    public void onRouteClicked(BusStop stop, BusRoute route) {
        Log.i("#RK", "Route + " + route.getName() + " was clicked for stop " + stop.getName());
    }

    @Override
    public void onBusStopClicked(BusStop stop) {
        Log.i("#RK", "Bus stop " + stop.getName() + " was clicked");
    }

    @Override
    public void onShowNearbyBusStopsClicked() {
        mLauncher.nearbyBuses();
    }
}
