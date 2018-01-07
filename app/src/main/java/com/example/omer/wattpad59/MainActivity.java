package com.example.omer.wattpad59;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.omer.wattpad59.adapters.SectionsPageAdapter;
import com.example.omer.wattpad59.database.MyInfoManager;
import com.example.omer.wattpad59.fragments.BrowseFragment;
import com.example.omer.wattpad59.fragments.RecommendedFragment;
import com.example.omer.wattpad59.network.utils.NetworkConnector;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyInfoManager.getInstance().openDatabase(this);
        NetworkConnector.getInstance().initialize(this);

        setTitle(R.string.home); //set toolbar title

        Log.d(TAG,"onCreate: String.");


//***************************BOTTOM NAVIGATION BAR*****************************************************
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        //bottom navigation color animation
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()){
                        case R.id.ic_home:
                            break;

                        case R.id.ic_library:
                            intent = new Intent(MainActivity.this, LibraryActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.ic_add_story_info:
                            intent = new Intent(MainActivity.this, AddStoryInfoActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.ic_my_books:
                             intent= new Intent(MainActivity.this, MyBooks_Activity.class);
                            startActivity(intent);
                            break;
                    }
                    return false;
                }
            });


//*********************************FRAGMENT*******************************************************
            mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

            //Setup up the ViewPager with the sections adapter.
            mViewPager=(ViewPager)findViewById(R.id.container);
            setupViewPager(mViewPager);

            TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);
        }

    //Show the "Browse" and "Recommended" fragments
    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter=new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new BrowseFragment(),"Browse");
        adapter.addFragment(new RecommendedFragment(),"Recommended");
        viewPager.setAdapter(adapter);

    }
}
