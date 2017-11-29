package com.example.omer.wattpad59;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

        private static final String TAG="MainActivity";

        private SectionsPageAdapter mSectionsPageAdapter;

        private ViewPager mViewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Log.d(TAG,"onCreate: String.");


//***************************BOTTOM NAVIGATION BAR*****************************************************
            BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavView_Bar);
            BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.ic_home:
                            break;

                        case R.id.ic_library:
                            Intent intent1 = new Intent(MainActivity.this, LibraryActivity.class);
                            startActivity(intent1);
                            break;

                        case R.id.ic_add_story_info:
                            Intent intent2 = new Intent(MainActivity.this, AddStoryInfoActivity.class);
                            startActivity(intent2);
                            break;
                    }
                    return false;
                }
            });


//*********************************FRAGMENT*******************************************************
            mSectionsPageAdapter=new SectionsPageAdapter(getSupportFragmentManager());

            //Setup up the ViewPager with the sections adapter.
            mViewPager=(ViewPager)findViewById(R.id.container);
            setupViewPager(mViewPager);

            TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);
        }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter=new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new BrowseFragment(),"Browse");
        adapter.addFragment(new RecommendedFragment(),"Recommanded");
        viewPager.setAdapter(adapter);

    }
}
