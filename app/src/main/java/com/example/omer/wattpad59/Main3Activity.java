package com.example.omer.wattpad59;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

        private static final String TAG="MainActivity";

        private SectionsPageAdapter mSectionsPageAdapter;

        private ViewPager mViewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main3);
            Log.d(TAG,"onCreate: String.");

//***************************BOTTOM NAVIGATION BAR*****************************************************


            BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavView_Bar);
            BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);



//*********************************FRAGMENT*******************************************************
            mSectionsPageAdapter=new SectionsPageAdapter(getSupportFragmentManager());

            //Setp up the ViewPager with the sections adapter.
            mViewPager=(ViewPager)findViewById(R.id.container);
            setupViewPager(mViewPager);

            TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);
        }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter=new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new browse_fragment(),"Browse");
        adapter.addFragment(new recommanded_fragment(),"Recommanded");
        viewPager.setAdapter(adapter);

    }
}
