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

public class RecommendedFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancecState){
        View view=inflater.inflate(R.layout.recommended_fragment,container,false);

        return view;
    }
}
