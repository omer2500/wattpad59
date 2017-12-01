package com.example.omer.wattpad59;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by omer on 01/12/2017.
 */

public class MyBooks_Activity extends AppCompatActivity {

    int [] images={R.drawable.actionbook1,R.drawable.actionbook2,R.drawable.actionbook3,R.drawable.actionbook4,R.drawable.actionbook5};

    String [] names={"BREAKER","The Eye Of Minds","Loose Ends", "Hunger Games","In The Blood"};

    String[] description={"this is book 1","this is book 2","this is book 3","this is book 4","this is book 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybooks_activity);

        setTitle(R.string.myBooks); //set toolbar title

        //connecting the view to the ativity
        ListView listView = (ListView) findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);


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
        class CustomAdapter extends BaseAdapter {

            //return the size of the list
            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view=getLayoutInflater().inflate(R.layout.custom_listview,null);

                //assign the views for a variable to set things up
                ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
                TextView bookName=(TextView)view.findViewById(R.id.bookName);
                TextView bookDesc=(TextView)view.findViewById(R.id.bookDescription);

                //setting the text and image of the custom view
                imageView.setImageResource(images[i]);
                bookName.setText(names[i]);
                bookDesc.setText(description[i]);

                return view;
            }
        }

    }
