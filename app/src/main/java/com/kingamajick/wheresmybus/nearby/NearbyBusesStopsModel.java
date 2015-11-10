package com.kingamajick.wheresmybus.nearby;

import android.support.annotation.NonNull;

import com.kingamajick.wheresmybus.datamodels.BusStop;
import com.kingamajick.wheresmybus.util.mvp.MVPModel;

import java.util.List;

public interface NearbyBusesStopsModel extends MVPModel {

    @NonNull
    List<BusStop> getNearbyStops();

}
