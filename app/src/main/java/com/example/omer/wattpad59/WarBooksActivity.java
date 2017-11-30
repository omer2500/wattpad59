package com.example.omer.wattpad59;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by omer on 30/11/2017.
 */

public class WarBooksActivity extends AppCompatActivity {

    int [] images={R.drawable.warbook1,R.drawable.warbook2,R.drawable.warbook3,R.drawable.warbook4,R.drawable.warbook5};

    String [] names={"Whose War Is It?","World War 2","World War Z", "The Whell OF Osheim","War Hawk"};

    String[] description={"this is book 1","this is book 2","this is book 3","this is book 4","this is book 5"};


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.war_books_activity);

        //connecting the view to the ativity
        ListView listView=(ListView)findViewById(R.id.listView);

        CustomAdapter customAdapter=new CustomAdapter();

        listView.setAdapter(customAdapter);
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