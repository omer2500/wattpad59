package com.example.omer.wattpad59;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Yarden-PC on 27-Nov-17.
 */

public class AddStoryInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story_info);

        TextView title = (TextView) findViewById(R.id.activityTitleAddStoryInfo);
        title.setText("This is activity add story info");

        //***************************BOTTOM NAVIGATION BAR*****************************************************
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        //bottom navigation color animation
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent = new Intent(AddStoryInfoActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_library:
                        Intent intent1 = new Intent(AddStoryInfoActivity.this, LibraryActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_add_story_info:
                        Intent intent2 = new Intent(AddStoryInfoActivity.this, AddStoryInfoActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });
        //***************************BOTTOM NAVIGATION BAR*****************************************************

    }
}
