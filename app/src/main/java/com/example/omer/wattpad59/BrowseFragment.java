package com.example.omer.wattpad59;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by omer on 26/11/2017.
 */

public class BrowseFragment extends Fragment {
    private static final String TAG ="Browse_fragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.browse_fragment,container,false);

        // book categories array
        String [] BookCategories={"Action","Fantasy","Horror","comedy","kids","Romance","war"};

        //attach the listview to the view on xml
        ListView listView = (ListView) view.findViewById(R.id.CategoriesMenu);

        //making array strings show in listView
        ArrayAdapter<String> ListViewAdapter=new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                BookCategories
        );

        //setting the adapter
        listView.setAdapter(ListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                    Intent myintent=new Intent(getActivity(),ActionBooksActivity.class);
                    startActivityForResult(myintent,0);
                }
                if(position==1){
                    Intent myintent=new Intent(getActivity(),FantacyBooksActivity.class);
                    startActivityForResult(myintent,0);
                }
                if(position==2){
                    Intent myintent=new Intent(getActivity(),HorrorBooksActivity.class);
                    startActivityForResult(myintent,0);
                }
                if(position==3){
                    Intent myintent=new Intent(getActivity(),ComedyBooksActivity.class);
                    startActivityForResult(myintent,0);
                }
                if(position==4){
                    Intent myintent=new Intent(getActivity(),KidsBooksActivity.class);
                    startActivityForResult(myintent,0);
                }
                if(position==5){
                    Intent myintent=new Intent(getActivity(),RomanceBooksActivity.class);
                    startActivityForResult(myintent,0);
                }
                if(position==6){
                    Intent myintent=new Intent(getActivity(),WarBooksActivity.class);
                    startActivityForResult(myintent,0);
                }
            }
        });

        return view;
    }
}
