package com.example.omer.wattpad59.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.omer.wattpad59.BooksActivity;
import com.example.omer.wattpad59.R;
import com.example.omer.wattpad59.interfaces.CallBackListener;
import com.example.omer.wattpad59.network.utils.NetworkConnector;
import com.example.omer.wattpad59.network.utils.NetworkResListener;
import com.example.omer.wattpad59.network.utils.ResStatus;

import org.json.JSONObject;

/**
 * Created by omer on 26/11/2017.
 */

public class BrowseFragment extends Fragment implements CallBackListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.browse_fragment,container,false);

        // book categories array
        String [] BookCategories={"Action","Fantasy","Horror","comedy","kids","Romance","war"};

        //attach the listView to the view on xml
        ListView listView = (ListView) view.findViewById(R.id.CategoriesMenu);

      //  NetworkConnector.getInstance().updateBooksFeed(this);

        //making array strings show in listView
        ArrayAdapter<String> ListViewAdapter=new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                BookCategories
        );

        //setting the adapter
        listView.setAdapter(ListViewAdapter);

        //Show list according to chosen category when clicking on the category item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //initialize the intent
                Intent myIntent;

                //according to user choice the activity will open and show him the
                //book of the category that he choose
                if(position==0){
                    myIntent=new Intent(getActivity(),BooksActivity.class);
                    myIntent.putExtra("str","action");
                    startActivityForResult(myIntent,0);
                }
                if(position==1){
                    myIntent=new Intent(getActivity(),BooksActivity.class);
                    myIntent.putExtra("str","fantasy");
                    startActivityForResult(myIntent,0);
                }
                if(position==2){
                    myIntent=new Intent(getActivity(),BooksActivity.class);
                    myIntent.putExtra("str","horror");
                    startActivityForResult(myIntent,0);
                }
                if(position==3){
                    myIntent=new Intent(getActivity(),BooksActivity.class);
                    myIntent.putExtra("str","comedy");
                    startActivityForResult(myIntent,0);
                }
                if(position==4){
                    myIntent=new Intent(getActivity(),BooksActivity.class);
                    myIntent.putExtra("str","kids");
                    startActivityForResult(myIntent,0);
                }
                if(position==5){
                    myIntent=new Intent(getActivity(),BooksActivity.class);
                    myIntent.putExtra("str","romance");
                    startActivityForResult(myIntent,0);
                }
                if(position==6){
                    myIntent=new Intent(getActivity(),BooksActivity.class);
                    myIntent.putExtra("str","war");
                    startActivityForResult(myIntent,0);
                }
            }
        });
        return view;
    }

    @Override
    public void onSaveButtonClicked() {

    }

//    @Override
//    public void onPreUpdate() {
//
//    }
//
//    @Override
//    public void onBookUpdate(byte[] res, ResStatus status) {
//
//    }
//
//    @Override
//    public void onBookUpdate(JSONObject res, ResStatus status) {
//
//    }
//
//    @Override
//    public void onBookUpdate(Bitmap res, ResStatus status) {
//
//    }
}
