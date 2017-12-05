package com.example.omer.wattpad59;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


    /**
     * Created by omer on 27/11/2017.
     */

    public class FantacyBooksActivity extends AppCompatActivity {


        private ListView bookListView;
        private customAdapter adapter;
        private List<bookTemplate> bookList;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.books_activity);

            bookListView = (ListView)findViewById(R.id.listView);

            bookList = new ArrayList<>();
            //Add sample data for list
            //We can get data from DB, webservice here
            bookList.add(new bookTemplate(R.drawable.fantacybook1,"The Hundred Thousand Kindoms","this is book1"));
            bookList.add(new bookTemplate(R.drawable.fantacybook2,"The Stone In The Skull","this is book2"));
            bookList.add(new bookTemplate(R.drawable.fantacybook3,"Harry Potter","this is book3"));
            bookList.add(new bookTemplate(R.drawable.fantacybook4,"Dragon Rider","this is book4"));
            bookList.add(new bookTemplate(R.drawable.fantacybook5,"The White Dragon","this is book5"));

            //Init adapter
            adapter = new customAdapter(getApplicationContext(), bookList);
            bookListView.setAdapter(adapter);
        }
    }

