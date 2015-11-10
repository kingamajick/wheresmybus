package com.kingamajick.wheresmybus.landing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kingamajick.wheresmybus.R;
import com.kingamajick.wheresmybus.navigation.LauncherImpl;
import com.kingamajick.wheresmybus.util.ViewFinder;
import com.kingamajick.wheresmybus.util.lifecycle.LifecycleDelegator;

public class LandingActivity extends AppCompatActivity {

    private final LifecycleDelegator mLifecycleDelegator = new LifecycleDelegator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mLifecycleDelegator.onCreate();

        final LandingPresenter presenter = new LandingPresenter(new LandingViewImpl(ViewFinder.create(getWindow())), new LandingModelImpl(), new LauncherImpl(this));
        mLifecycleDelegator.registerLifecycleCallback(presenter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.default_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        mLifecycleDelegator.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_find_bus_stops:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        mLifecycleDelegator.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLifecycleDelegator.onDestroy();
    }
}
