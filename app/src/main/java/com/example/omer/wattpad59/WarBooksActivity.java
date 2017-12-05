package com.example.omer.wattpad59;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


    /**
     * Created by omer on 27/11/2017.
     */

    public class WarBooksActivity extends AppCompatActivity {


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
            bookList.add(new bookTemplate(R.drawable.warbook1,"Whose War Is It?","this is book1"));
            bookList.add(new bookTemplate(R.drawable.warbook2,"World War 2","this is book2"));
            bookList.add(new bookTemplate(R.drawable.warbook3,"World War Z","this is book3"));
            bookList.add(new bookTemplate(R.drawable.warbook4,"The Whell OF Osheim","this is book4"));
            bookList.add(new bookTemplate(R.drawable.warbook5,"War Hawk","this is book5"));

            //Init adapter
            adapter = new customAdapter(getApplicationContext(), bookList);
            bookListView.setAdapter(adapter);
        }
    }
