package com.kingamajick.wheresmybus.landing;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingamajick.wheresmybus.R;
import com.kingamajick.wheresmybus.datamodels.BusRoute;
import com.kingamajick.wheresmybus.datamodels.BusStop;
import com.kingamajick.wheresmybus.util.ViewFinder;
import com.kingamajick.wheresmybus.util.decorators.DividerItemDecoration;
import com.kingamajick.wheresmybus.util.decorators.RouteListItemDecorator;
import com.kingamajick.wheresmybus.util.mvp.BaseMVPView;

import java.util.ArrayList;
import java.util.List;

public class LandingViewImpl extends BaseMVPView<LandingView.Listener> implements LandingView {

    private final RecyclerView mLandingView;
    private final Listener mListenerProxy = new Listener() {
        @Override
        public void onBusStopClicked(BusStop stop) {
            if (getListener() != null) {
                getListener().onBusStopClicked(stop);
            }
        }

        @Override
        public void onRouteClicked(BusStop stop, BusRoute route) {
            if (getListener() != null) {
                getListener().onRouteClicked(stop, route);
            }
        }

        @Override
        public void onShowNearbyBusStopsClicked() {
            if (getListener() != null) {
                getListener().onShowNearbyBusStopsClicked();
            }
        }
    };

    public LandingViewImpl(ViewFinder viewFinder) {
        mLandingView = viewFinder.findViewById(R.id.landing_list);
        mLandingView.setLayoutManager(new LinearLayoutManager(mLandingView.getContext(), LinearLayoutManager.VERTICAL, false));
        mLandingView.addItemDecoration(new DividerItemDecoration(mLandingView.getContext(), LinearLayoutManager.VERTICAL));
        mLandingView.setAdapter(new LandingContentAdapter(mListenerProxy));

        FloatingActionButton nearbyBuses = viewFinder.findViewById(R.id.landing_nearby_buses);
        nearbyBuses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListenerProxy.onShowNearbyBusStopsClicked();
            }
        });
    }

    @Override
    public void displayFavourites(@NonNull List<BusStop> stops) {
        ((LandingContentAdapter) mLandingView.getAdapter()).setBusStops(stops);
    }

    private static class LandingContentAdapter extends RecyclerView.Adapter {

        private List<BusStop> mBusStops = new ArrayList<>();
        private Listener mListener;

        private LandingContentAdapter(@NonNull Listener listener) {
            mListener = listener;
        }

        public void setBusStops(List<BusStop> busStops) {
            mBusStops.clear();
            mBusStops.addAll(busStops);
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new FavouriteStopViewHolder(inflater.inflate(R.layout.list_item_bus_stop, parent, false), mListener);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((FavouriteStopViewHolder) holder).bind(mBusStops.get(position));
        }

        @Override
        public int getItemCount() {
            return mBusStops.size();
        }

        private static class FavouriteStopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private final TextView mBusStopName;
            private final TextView mBusStopCode;
            private final RecyclerView mRoutes;

            private Listener mListener;
            private BusStop mBoundTo;

            public FavouriteStopViewHolder(View itemView, final Listener listener) {
                super(itemView);
                mListener = listener;
                mBusStopName = (TextView) itemView.findViewById(R.id.list_item_bus_stop_name);
                mBusStopCode = (TextView) itemView.findViewById(R.id.list_item_bus_stop_code);
                mRoutes = (RecyclerView) itemView.findViewById(R.id.list_item_bus_stop_routes);
                mRoutes.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
                mRoutes.addItemDecoration(new RouteListItemDecorator(itemView.getContext()));
                mRoutes.setAdapter(new RoutesContentAdapter(new RoutesContentAdapter.ParentBusStopCallback() {

                    @Override
                    public BusStop getParentBusstop() {
                        return mBoundTo;
                    }
                }, listener));
                itemView.setOnClickListener(this);
            }

            public void bind(BusStop busStop) {
                mBoundTo = busStop;
                mBusStopName.setText(busStop.getName());
                mBusStopCode.setText(busStop.getCode());
                ((RoutesContentAdapter) mRoutes.getAdapter()).setRoutes(busStop.getRoutes());
            }

            @Override
            public void onClick(View v) {
                mListener.onBusStopClicked(mBoundTo);
            }
        }
    }

    private static class RoutesContentAdapter extends RecyclerView.Adapter {

        private interface ParentBusStopCallback {
            BusStop getParentBusstop();
        }

        private List<BusRoute> mBusRoutes = new ArrayList<>();
        private ParentBusStopCallback mParent;
        private Listener mListener;

        public RoutesContentAdapter(ParentBusStopCallback parent, Listener listener) {
            mParent = parent;
            mListener = listener;
        }

        public void setRoutes(List<BusRoute> busRoutes) {
            mBusRoutes.clear();
            mBusRoutes.addAll(busRoutes);
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new RoutesViewHolder(inflater.inflate(R.layout.list_item_route, parent, false), mParent, mListener);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((RoutesViewHolder) holder).bind(mBusRoutes.get(position));
        }

        @Override
        public int getItemCount() {
            return mBusRoutes.size();
        }

        private static class RoutesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private final TextView mRouteName;

            private Listener mListener;
            private ParentBusStopCallback mParent;
            private BusRoute mBoundTo;

            public RoutesViewHolder(View itemView, ParentBusStopCallback parent, Listener listener) {
                super(itemView);
                mListener = listener;
                mParent = parent;
                mRouteName = (TextView) itemView.findViewById(R.id.list_item_route_name);
                mRouteName.setOnClickListener(this);

            }

            public void bind(BusRoute route) {
                mBoundTo = route;
                mRouteName.setText(route.getName());
            }

            @Override
            public void onClick(View v) {
                mListener.onRouteClicked(mParent.getParentBusstop(), mBoundTo);
            }
        }
    }


}
