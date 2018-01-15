package com.example.omer.wattpad59.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.omer.wattpad59.R;
import com.example.omer.wattpad59.adapters.customAdapter2;
import com.example.omer.wattpad59.core.BookInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omer on 26/11/2017.
 */

public class RecommendedFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancecState){
        View view=inflater.inflate(R.layout.recommended_fragment,container,false);

        return view;
    }

}
