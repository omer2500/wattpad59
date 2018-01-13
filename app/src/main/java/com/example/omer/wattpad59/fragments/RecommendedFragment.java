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
    List<BookInfo> recommandedBooks;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancecState){
        View view=inflater.inflate(R.layout.recommended_fragment,container,false);
        recommandedBooks=new ArrayList<>();

        // book categories array
        recommandedBooks.add(new BookInfo(convertToBitmap(R.drawable.actionbook1), "Breaker", "this is book1"));
        recommandedBooks.add(new BookInfo(convertToBitmap(R.drawable.fantacybook3),"Harry Potter","this is book3"));
        recommandedBooks.add(new BookInfo(convertToBitmap(R.drawable.horrorbook2),"The Ritual","this is book2"));
        recommandedBooks.add(new BookInfo(convertToBitmap(R.drawable.comedybook3), "The Best Intentions", "this is book3"));
        recommandedBooks.add(new BookInfo(convertToBitmap(R.drawable.kidsbook3),"The Nose Book","this is book3"));
        recommandedBooks.add(new BookInfo(convertToBitmap(R.drawable.romancebook4),"Time To Forgive","this is book4"));
        recommandedBooks.add(new BookInfo(convertToBitmap(R.drawable.warbook3),"World War Z","this is book3"));


        //attach the listView to the view on xml
        ListView listView = (ListView) view.findViewById(R.id.recommandedBooks);

        //making array strings show in listView
        ArrayAdapter<BookInfo> ListViewAdapter=new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                recommandedBooks
        );

        //setting the adapter
        customAdapter2 adapter=new customAdapter2(this.getActivity(),recommandedBooks);
        listView.setAdapter(adapter);

        return view;
    }

    private Bitmap convertToBitmap(int image) {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources() , image);

        return bitmap;
    }
}
