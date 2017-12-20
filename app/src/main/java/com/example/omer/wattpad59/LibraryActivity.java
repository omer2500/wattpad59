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
                Intent intent;
                switch (item.getItemId()){
                    case R.id.ic_home:
                        intent = new Intent(LibraryActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_library:
                        intent = new Intent(LibraryActivity.this, LibraryActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_add_story_info:
                        intent = new Intent(LibraryActivity.this, AddStoryInfoActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_my_books:
                        intent = new Intent(LibraryActivity.this, MyBooks_Activity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
        //***************************BOTTOM NAVIGATION BAR*****************************************************

    }
}
