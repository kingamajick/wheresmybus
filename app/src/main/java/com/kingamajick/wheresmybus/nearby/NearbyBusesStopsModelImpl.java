package com.kingamajick.wheresmybus.nearby;

import android.support.annotation.NonNull;

import com.kingamajick.wheresmybus.datamodels.BusRoute;
import com.kingamajick.wheresmybus.datamodels.BusStop;
import com.kingamajick.wheresmybus.util.mvp.BaseMVPModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NearbyBusesStopsModelImpl extends BaseMVPModel implements NearbyBusesStopsModel {

    @NonNull
    @Override
    public List<BusStop> getNearbyStops() {
        final Random rand = new Random();
        List<BusStop> stops = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<BusRoute> routes = new ArrayList<>();
            final int routeCount = rand.nextInt(14) + 1;
            for (int j = 0; j < routeCount; j++) {
                BusRoute route = new BusRoute("R" + j, "", 0L);
                routes.add(route);
            }
            BusStop stop = new BusStop("BusStop" + i, String.valueOf((char) (65 + i)), routes);
            stops.add(stop);
        }
        return stops;
    }
}
