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
import android.widget.ListView;
import android.widget.TextView;

import com.example.omer.wattpad59.adapters.customAdapter2;
import com.example.omer.wattpad59.core.BookInfo;

import java.util.ArrayList;
import java.util.List;


public class LibraryActivity extends AppCompatActivity {

    BooksActivity ba=new BooksActivity();
    public List<BookInfo> bookListfav=ba.getBookListfav();
    private ListView bookListView;
    private customAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
//        bookListfav=new ArrayList<>();
        setTitle(R.string.library); //set toolbar title
        bookListView = (ListView)findViewById(R.id.listView3);


        //Init adapter
        adapter = new customAdapter2(getApplicationContext(), bookListfav);
        bookListView.setAdapter(adapter);




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




