package com.example.omer.wattpad59;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by omer on 26/11/2017.
 */

public class browse_fragment extends Fragment {
    private static final String TAG ="Browse_fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.browse_fragment,container,false);

        return view;
    }
}
