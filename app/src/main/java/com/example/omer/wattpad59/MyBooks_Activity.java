package com.example.omer.wattpad59;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omer on 01/12/2017.
 */

public class MyBooks_Activity extends AppCompatActivity {

    private static final String TAG="myBooksActivity";
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;
    private List<BookInfo> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybooks_activity);
        setTitle(R.string.myBooks); //set toolbar title

        mListView =(ListView) findViewById(R.id.listView1) ;

        bookList= MyInfoManager.getInstance().getAllBooks();

        //create the list adapter and set the adapter
        customAdapter adapter = new customAdapter(this, bookList);
        mListView.setAdapter(adapter);


        //***************************BOTTOM NAVIGATION BAR*****************************************************
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        //bottom navigation color animation
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent = new Intent(MyBooks_Activity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_library:
                        Intent intent1 = new Intent(MyBooks_Activity.this, LibraryActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_add_story_info:
                        Intent intent2 = new Intent(MyBooks_Activity.this, AddStoryInfoActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_my_books:
                        Intent intent3 = new Intent(MyBooks_Activity.this, MyBooks_Activity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });
        //***************************BOTTOM NAVIGATION BAR*****************************************************
    }

}
