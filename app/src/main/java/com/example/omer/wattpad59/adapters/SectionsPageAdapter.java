package com.example.omer.wattpad59.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omer on 26/11/2017.
 */
//This class helps us manage the fragments. It saves the fragment and the fragment's name.

public class SectionsPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList=new ArrayList<>();
    private final List<String> mFragmenTitletList=new ArrayList<>();


    //Add fragment to the list
    public void addFragment(Fragment fragment,String title){
        mFragmentList.add(fragment);
        mFragmenTitletList.add(title);
    }

    //Constructor
    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    //Get the fragment title
    public CharSequence getPageTitle(int position){
        return mFragmenTitletList.get(position);
    }

    //Get the fragment
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
