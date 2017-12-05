package com.example.omer.wattpad59;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omer on 30/11/2017.
 */


    public class ComedyBooksActivity extends AppCompatActivity {


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
            bookList.add(new bookTemplate(R.drawable.comedybook1,"Popeye","this is book1"));
            bookList.add(new bookTemplate(R.drawable.comedybook2,"I Must Say","this is book2"));
            bookList.add(new bookTemplate(R.drawable.comedybook3,"The Best Intentions","this is book3"));
            bookList.add(new bookTemplate(R.drawable.comedybook4,"Maxx Comedy","this is book4"));
            bookList.add(new bookTemplate(R.drawable.comedybook5,"Language OF A Broken Heart","this is book5"));


            //Init adapter
            adapter = new customAdapter(getApplicationContext(), bookList);
            bookListView.setAdapter(adapter);
        }
    }
