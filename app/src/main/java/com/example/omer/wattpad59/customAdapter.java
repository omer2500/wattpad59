package com.example.omer.wattpad59;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NgocTri on 11/15/2015.
 */
public class customAdapter extends BaseAdapter {

    private Context mContext;
    private List<bookTemplate> bookList;

    //Constructor
    public customAdapter(Context mContext, List<bookTemplate> bookList) {
        this.mContext = mContext;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.custom_listview, null);

        TextView bookName = (TextView)v.findViewById(R.id.bookName);
        TextView bookDescription = (TextView)v.findViewById(R.id.bookDescription);
        ImageView bookImage = (ImageView)v.findViewById(R.id.imageView);

        //Set text for the view
        bookName.setText(bookList.get(position).getBookName());
        bookDescription.setText(String.valueOf(bookList.get(position).getBookDescription()));
        bookImage.setImageResource(bookList.get(position).getImage());

        return v;
    }
}