package com.kingamajick.wheresmybus.nearby;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kingamajick.wheresmybus.R;
import com.kingamajick.wheresmybus.util.lifecycle.LifecycleDelegator;

public class NearbyBusesActivity extends AppCompatActivity {

    private final LifecycleDelegator mLifecycleDelegator = new LifecycleDelegator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        final TabLayout tabBar = (TabLayout) findViewById(R.id.tabBar);
        tabBar.addTab(tabBar.newTab().setText("Map"), true);
        tabBar.addTab(tabBar.newTab().setText("List"));

        final NearbyPagerAdapter adapter = new NearbyPagerAdapter(getSupportFragmentManager());
        final ViewPager pager = (ViewPager) findViewById(R.id.nearby_pager);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabBar));

    }

    public class NearbyPagerAdapter extends FragmentPagerAdapter {

        public NearbyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return NearbyBusesMapFragment.newInstance();
                case 1:
                    return NearbyBusesListFragment.newInstance();
            }
            throw new IllegalArgumentException("Unknown position " + position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
