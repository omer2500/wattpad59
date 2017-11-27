package com.example.omer.wattpad59;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by omer on 26/11/2017.
 */

public class BrowseFragment extends Fragment {
    private static final String TAG ="Browse_fragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.browse_fragment,container,false);

        String [] BookCategories={"Action","Fantacy","Horror"};

        ListView listView = (ListView) view.findViewById(R.id.CategoriesMenu);


        ArrayAdapter<String> ListViewAdapter=new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                BookCategories
        );

        listView.setAdapter(ListViewAdapter);


        return view;
    }
}
