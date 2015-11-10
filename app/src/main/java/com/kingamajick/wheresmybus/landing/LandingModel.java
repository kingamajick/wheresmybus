package com.kingamajick.wheresmybus.landing;

import android.support.annotation.NonNull;

import com.kingamajick.wheresmybus.datamodels.BusStop;
import com.kingamajick.wheresmybus.util.mvp.MVPModel;

import java.util.List;

public interface LandingModel extends MVPModel {

    @NonNull
    List<BusStop> getFavouriteStops();

}
