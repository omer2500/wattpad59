package com.example.omer.wattpad59.adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omer.wattpad59.R;
import com.example.omer.wattpad59.core.BookInfo;
import com.example.omer.wattpad59.database.MyInfoManager;
import com.example.omer.wattpad59.network.utils.NetworkConnector;
import com.example.omer.wattpad59.network.utils.NetworkResListener;
import com.example.omer.wattpad59.network.utils.ResStatus;

import org.json.JSONObject;

import java.util.List;

public class booksAdapter extends BaseAdapter {

    private Context mContext;
    private List<BookInfo> bookList;

    //Constructor
    public booksAdapter(Context mContext, List<BookInfo> bookList) {
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
        final BookInfo book= bookList.get(position);

        TextView bookName = (TextView)v.findViewById(R.id.bookName);
        TextView bookDescription = (TextView)v.findViewById(R.id.bookDescription);
        //ImageView bookImage = (ImageView)v.findViewById(R.id.imageView);

        //Set text for the view
        bookName.setText(book.getName());
        bookDescription.setText(String.valueOf(book.getDescription()));
        //bookImage.setImageBitmap(bookList.get(position).getImage());

        final ImageView bookImageView = (ImageView) v.findViewById(R.id.imageView);

        Bitmap bm = book.getImage();
        if(bm!=null){
            bookImageView.setImageBitmap(bm);
        }

        NetworkConnector.getInstance().sendRequestToServer(NetworkConnector.GET_BOOK_IMAGE_REQ, book, new NetworkResListener() {
            @Override
            public void onPreUpdate() {
                //Toast.makeText(context,"start download img... id=" + book.getId(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBookUpdate(byte[] res, ResStatus status) {

            }

            @Override
            public void onBookUpdate(JSONObject res, ResStatus status) {

            }

            @Override
            public void onBookUpdate(Bitmap res, ResStatus status) {
                //Toast.makeText(context,"download img finished...status " + status.toString(),Toast.LENGTH_SHORT).show();
                if(status == ResStatus.SUCCESS){
                    if(res!=null) {
                        if(bookImageView!=null) {
                            bookImageView.setImageBitmap(res);
                        }
                        book.setImage(res);
                        MyInfoManager.getInstance().updateBookImage(book);
                    }
                }

            }
        });


        return v;
    }
}