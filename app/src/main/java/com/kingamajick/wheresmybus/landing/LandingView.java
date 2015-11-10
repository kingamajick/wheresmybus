package com.kingamajick.wheresmybus.landing;

import android.support.annotation.NonNull;

import com.kingamajick.wheresmybus.datamodels.BusRoute;
import com.kingamajick.wheresmybus.datamodels.BusStop;
import com.kingamajick.wheresmybus.util.mvp.MVPView;

import java.util.List;

/**
 * Describes the main landing screen for the application.
 */
public interface LandingView extends MVPView<LandingView.Listener> {

    interface Listener {
        void onBusStopClicked(BusStop stop);

        void onRouteClicked(BusStop stop, BusRoute route);

        void onShowNearbyBusStopsClicked();
    }


    void displayFavourites(@NonNull List<BusStop> stops);

}
