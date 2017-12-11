package com.example.omer.wattpad59;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    private List<BookInfo> booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        setTitle(R.string.library); //set toolbar title

        TextView title = (TextView) findViewById(R.id.activityTitleLibrary);
        title.setText("This is activity library");

        //***************************BOTTOM NAVIGATION BAR*****************************************************
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        //bottom navigation color animation
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent = new Intent(LibraryActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_library:
                        Intent intent1 = new Intent(LibraryActivity.this, LibraryActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_add_story_info:
                        Intent intent2 = new Intent(LibraryActivity.this, AddStoryInfoActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_my_books:
                        Intent intent3 = new Intent(LibraryActivity.this, MyBooks_Activity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });
        //***************************BOTTOM NAVIGATION BAR*****************************************************

    }
}
