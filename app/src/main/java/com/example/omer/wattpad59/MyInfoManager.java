package com.example.omer.wattpad59;

import android.content.Context;

import java.util.List;

/**
 * Created by Yarden-PC on 11-Dec-17.
 * This is the SINGLE TONE
 */

public class MyInfoManager {

    private static MyInfoManager instance;

    private DatabaseHelper databaseHelper;

    public static MyInfoManager getInstance(){

        if(instance == null){
            instance = new MyInfoManager();
        }
        return instance;
    }

    private MyInfoManager(){

    }

    public void openDatabase(Context context){
        databaseHelper = new DatabaseHelper(context);
        databaseHelper.open();
    }

    public void closeDatabase(){
        if(databaseHelper!=null){
            databaseHelper.close();
        }
    }

    public boolean addNewBook(BookInfo book){
        return databaseHelper.addNewBookInfo(book);
    }

    public List<BookInfo> getAllBooks(){
        return databaseHelper.getAllBooks();
    }
}
