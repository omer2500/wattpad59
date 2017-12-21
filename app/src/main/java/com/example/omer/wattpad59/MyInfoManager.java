package com.example.omer.wattpad59;

import android.content.Context;

import java.util.List;

/**
 * Created by Yarden-PC on 11-Dec-17.
 * This is the singleton
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

    //Open the database
    public void openDatabase(Context context){
        databaseHelper = new DatabaseHelper(context);
        databaseHelper.open();
    }

    //Close database if open
    public void closeDatabase(){
        if(databaseHelper!=null){
            databaseHelper.close();
        }
    }

    //Add new book. Calling the addNewBook function in DatabaseHelper activity
    public boolean addNewBook(BookInfo book){
        return databaseHelper.addNewBookInfo(book);
    }

    //Get all books in DB. Calling the getAllBooks function in DatabaseHelper activity
    public List<BookInfo> getAllBooks(){
        return databaseHelper.getAllBooks();
    }

    //Delete a book. Calling the delete function in DatabaseHelper activity
    public boolean deleteBook(String id){
        return databaseHelper.delete(id);
    }
}
