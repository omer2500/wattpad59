package com.example.omer.wattpad59;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by omer on 27/11/2017.
 */

public class ActionBooksActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter customAdapter;

    int [] images={R.drawable.actionbook1,R.drawable.actionbook2,R.drawable.actionbook3,R.drawable.actionbook4,R.drawable.actionbook5};

    String [] names={"BREAKER","The Eye Of Minds","Loose Ends", "Hunger Games","In The Blood"};

    String[] description={"this is book 1","this is book 2","this is book 3","this is book 4","this is book 5"};


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        setTitle(R.string.action); //set toolbar title

        //connecting the view to the activity
        listView=(ListView)findViewById(R.id.listView);

        customAdapter=new CustomAdapter();

        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    class CustomAdapter extends BaseAdapter{

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
